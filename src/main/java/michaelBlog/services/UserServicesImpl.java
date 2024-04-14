package michaelBlog.services;

import michaelBlog.data.model.Comment;
import michaelBlog.data.model.Post;
import michaelBlog.data.model.User;
import michaelBlog.data.model.View;
import michaelBlog.data.repository.CommentRepository;
import michaelBlog.data.repository.PostRepository;
import michaelBlog.data.repository.UserRepository;
import michaelBlog.data.repository.ViewRepository;
import michaelBlog.dtos.request.*;
import michaelBlog.dtos.responses.*;
import michaelBlog.exceptions.*;
import michaelBlog.utils.Mapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static michaelBlog.utils.Mapper.map;
import static michaelBlog.utils.Mapper2.*;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentServices commentServices;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostServices postServices;
    @Autowired
    private ViewRepository viewRepository;

    @Override
    public RegisterUserResponse registerUser(RegisterRequest registerRequest) {
        registerRequest.setUsername(registerRequest.getUsername().toLowerCase());
        validate(registerRequest.getUsername());
        User user = map(registerRequest);
        userRepository.save(user);
        RegisterUserResponse result = map(user);
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
    public EditPostResponse editPostWith(EditPostRequest editPostRequest) {
        User author = findById(editPostRequest.getAuthor());
        LoginStatus(author);
        Post authorPost = foundPost(editPostRequest.getId());
        return postServices.editPostWith(editPostRequest, authorPost);
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        User user = findById(logoutRequest.getUsername());
        user.setLocked(true);
        userRepository.save(user);
    }

    @Override
    public void deleteAccount(DeleteUserRequest deleteRequest) {
        User user = findById(deleteRequest.getUsername());
        userRepository.delete(user);
    }

    @Override
    public long numberOfUsers() {
        return userRepository.findAll().size();
    }

    @Override
    public CommentResponse createComment(CreateCommentRequest createCommentRequest) {
        Comment comment = mapCommentToPost(createCommentRequest);
        comment = commentRepository.save(comment);
        return mapResponse(comment);
    }

    @Override
    public GetUserPostsResponse getUserPosts(GetUserPostsRequest getUserPostsRequest) {
        User foundUser = findById(getUserPostsRequest.getUsername());
        return mapGetUserPostsResponse(foundUser);
    }

    @Override
    public void deleteComment(DeleteCommentRequest deleteCommentRequest) {
        List<Comment> comments = commentRepository.findByCommenter(deleteCommentRequest.getAuthor());
        if(comments.isEmpty()) {
            throw new CommentNotFoundException("Comment not found for author: " + deleteCommentRequest.getAuthor());
        } else {
            Comment commentToDelete = comments.getFirst();
            commentRepository.delete(commentToDelete);
        }
    }

    @Override
    public CreatePostResponse createPost(CreatePostRequest createPostRequest) {
        User foundUser = findById(createPostRequest.getUserName());
        validateLogin(foundUser);
        userRepository.save(foundUser);
        Post newPost = postServices.createPost(createPostRequest);
        foundUser.getPosts().add(newPost);
        postRepository.save(newPost);
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
        //User author = findById(deleteRequest.getAuthor());
        Post postToDelete = foundPost(deleteRequest.getPostId());
        return postServices.deletePostWith(deleteRequest, postToDelete);
    }
    @Override
    public ViewPostResponse viewPost(ViewPostRequest viewPostRequest) {
        if (viewPostRequest.getViewer() == null)
            return postServices.addViewWith(viewPostRequest, findById(null));
        User viewer = findById(viewPostRequest.getViewer());
        Post findPost  = foundPost(viewPostRequest.getPostId());
        View findViewer = Mapper2.map(viewer);
        LoginStatus(viewer);
        return mapViewPostResponseWith(findViewer, findPost);
    }
    private void LoginStatus(User user) {
        if (!user.isLocked()) throw new IllegalUserStateException("User is not logged in");
    }

    @Override
    public long numberOfComments() {
        return commentRepository.findAll().size();
    }

    @Override
    public long numberOfViews() {
        return viewRepository.findAll().size();
    }

    private Post foundPost(String id) {
        for (Post post : postRepository.findAll()) if (post.getId().equals(id)) return post;
        throw new InvalidPostException("Post  was not found");
    }
}


