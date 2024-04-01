package michaelBlog.services;

import michaelBlog.data.model.Post;
import michaelBlog.data.model.User;
import michaelBlog.data.repository.PostRepository;
import michaelBlog.data.repository.UserRepository;
import michaelBlog.dtos.request.*;
import michaelBlog.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static michaelBlog.utils.Mapper.map;
import static michaelBlog.utils.Mapper2.post;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostServices postServices;

    @Override
    public RegisterUserResponse registerUser(RegisterRequest registerRequest) {
        registerRequest.setUsername(registerRequest.getUsername().toLowerCase());
        validate(registerRequest.getUsername());
        User user = map(registerRequest);
        RegisterUserResponse result = map(user);
        userRepository.save(user);
        return result;
    }

    private void validate(String username) {
        boolean userExist = userRepository.existsByUserName(username);
        if (userExist) throw new UserNameExistException("username already exist");
    }

    @Override
    public void login(LoginRequest loginRequest) {
        User user = findById(loginRequest.getUsername());
        if (isPasswordIncorrect(user, loginRequest.getPassword()))
            throw new InvalidPasswordException("Invalid password");
        user.setLocked(false);
        userRepository.save(user);
    }

    private boolean isPasswordIncorrect(User foundUser, String password) {
        if (foundUser == null) {
            throw new NullPointerException("Diary object is null");
        }

        if (!foundUser.getPassword().equals(password)) {
            throw new InvalidPasswordException("Invalid Password");
        }
        return false;
    }

    private User findById(String username) {
        User foundUser = userRepository.findByUserName(username);
        if (foundUser == null) throw new UsernameNotFoundException(String.format("%s not found", username));
        return foundUser;
    }


    @Override
    public void logout(String username) {
        User user = findById(username);
        user.setLocked(true);
    }

    @Override
    public void updateProfile(UpdateRequest updateRequest) {

    }

    @Override
    public void deleteAccount(DeleteUserRequest deleteRequest) {

    }

    @Override
    public long numberOfUsers() {
        return userRepository.findAll().size();
    }

    @Override
    public CreatePostResponse createPost(CreatePostRequest createPostRequest) {
        User foundUser = findById(createPostRequest.getUserName());
        validateLogin(foundUser);
        Post newPost = postServices.createPost(createPostRequest);
        foundUser.getPosts().add(newPost);
        userRepository.save(foundUser);
        return post(newPost);
    }
    private void validateLogin(User user) {
        if (!user.isLocked()) throw new IllegalUserStateException("User is not logged in");
    }

    @Override
    public long numberOfPost() {
        return postRepository.findAll().size();
    }

    @Override
    public DeletePostResponse deletePost(DeleteRequest deleteRequest) {
        User author = findById(deleteRequest.getAuthor());
        Post postToDelete = foundPost(deleteRequest.getPostId(), author);
        return postServices.deletePostWith(deleteRequest, postToDelete);
    }
    private Post foundPost(String id, User author) {
        for (Post post : author.getPosts()) if (post.getId().equals(id)) return post;
        throw new InvalidPostException("Post  was not found");
    }
}


