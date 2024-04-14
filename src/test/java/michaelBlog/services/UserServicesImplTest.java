package michaelBlog.services;

import michaelBlog.data.repository.CommentRepository;
import michaelBlog.data.repository.PostRepository;
import michaelBlog.data.repository.UserRepository;
import michaelBlog.dtos.request.*;
import michaelBlog.dtos.responses.CommentResponse;
import michaelBlog.dtos.responses.CreatePostResponse;
import michaelBlog.exceptions.InvalidPasswordException;
import michaelBlog.exceptions.UserNameExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServicesImplTest {
    @Autowired
    private UserServices userServices;
    @Autowired
    private CommentServices commentServices;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @BeforeEach
    public void  setUp(){
        userRepository.deleteAll();
        commentRepository.deleteAll();
        postRepository.deleteAll();
    }
    @Test
    public void registerUser(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("firstname");
        registerRequest.setLastName("lastname");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        userServices.registerUser(registerRequest);
        assertEquals(1, userServices.numberOfUsers());
    }
    @Test
    public void register_nonUser(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("firstname");
        registerRequest.setLastName("lastname");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        userServices.registerUser(registerRequest);
        try{
            userServices.registerUser(registerRequest);
        }catch (UserNameExistException e){
            assertEquals(e.getMessage(), "username already exist");
        }
        assertEquals(1, userServices.numberOfUsers());
    }
    @Test
    public void testToCreatePost(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("firstname");
        registerRequest.setLastName("lastname");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        userServices.registerUser(registerRequest);
        CreatePostRequest createPostRequest = new CreatePostRequest();
        createPostRequest.setTitle("My daily life");
        createPostRequest.setContent("Life of a programmer");
        createPostRequest.setUserName(registerRequest.getUsername());
        userServices.createPost(createPostRequest);
        assertEquals(1, userServices.numberOfPost());
    }
    @Test
    public void testToDeletePost() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("firstname");
        registerRequest.setLastName("lastname");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        userServices.registerUser(registerRequest);

        CreatePostRequest createPostRequest = new CreatePostRequest();
        createPostRequest.setTitle("My daily life");
        createPostRequest.setContent("Life of a programmer");
        createPostRequest.setUserName(registerRequest.getUsername());
        CreatePostResponse createPostResponse = userServices.createPost(createPostRequest);

        assertNotNull(createPostResponse);

        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.setAuthor(createPostRequest.getUserName());
        deleteRequest.setPostId(createPostResponse.getId());
        userServices.deletePost(deleteRequest);
        assertEquals(0, userServices.numberOfPost());
    }
    @Test
    public void testToLogin(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("firstname");
        registerRequest.setLastName("lastname");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        userServices.registerUser(registerRequest);
        assertEquals(1, userServices.numberOfUsers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(registerRequest.getUsername());
        loginRequest.setPassword(registerRequest.getPassword());
        userServices.login(loginRequest);
        assertFalse(userRepository.findByUserName(loginRequest.getUsername()).isLocked());
    }
    @Test
    public void testToLoginWithIncorrectPassword(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("firstname");
        registerRequest.setLastName("lastname");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        userServices.registerUser(registerRequest);
        assertEquals(1, userServices.numberOfUsers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(registerRequest.getUsername());
        loginRequest.setPassword("wrong password");
        try{
        userServices.login(loginRequest);
        }catch (InvalidPasswordException e){
            assertEquals(e.getMessage(), "Invalid Password");
        }
    }
    @Test
    public void testToLogout(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("firstname");
        registerRequest.setLastName("lastname");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        userServices.registerUser(registerRequest);
        assertEquals(1, userServices.numberOfUsers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(registerRequest.getUsername());
        loginRequest.setPassword(registerRequest.getPassword());
        userServices.login(loginRequest);

        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUsername(loginRequest.getUsername());
        userServices.logout(logoutRequest);
        assertTrue(userRepository.findByUserName(logoutRequest.getUsername()).isLocked());
    }
    @Test
    public void testToComment(){
        CreateCommentRequest createCommentRequest = new CreateCommentRequest();
        createCommentRequest.setContent("trash");
        createCommentRequest.setAuthor("username");
        userServices.createComment(createCommentRequest);
        assertEquals(1, userServices.numberOfComments());
    }
    @Test
    public void testToDeleteComment(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("firstname");
        registerRequest.setLastName("lastname");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        userServices.registerUser(registerRequest);

        CreatePostRequest createPostRequest = new CreatePostRequest();
        createPostRequest.setTitle("My daily life");
        createPostRequest.setContent("Life of a programmer");
        createPostRequest.setUserName(registerRequest.getUsername());
        userServices.createPost(createPostRequest);

        CreateCommentRequest createCommentRequest = new CreateCommentRequest();
        createCommentRequest.setContent("trash");
        createCommentRequest.setAuthor("username");
        CommentResponse createCommentResponse = userServices.createComment(createCommentRequest);

        assertNotNull(createCommentResponse);
        DeleteCommentRequest deleteCommentRequest = new DeleteCommentRequest();

        deleteCommentRequest.setAuthor(createCommentRequest.getAuthor());
        userServices.deleteComment(deleteCommentRequest);

        assertEquals(0, commentServices.numberOfComments());
    }
    @Test
    public void testToViewPost(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("firstname");
        registerRequest.setLastName("lastname");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        userServices.registerUser(registerRequest);
        CreatePostRequest createPostRequest = new CreatePostRequest();
        createPostRequest.setTitle("My daily life");
        createPostRequest.setContent("Life of a programmer");
        createPostRequest.setUserName(registerRequest.getUsername());
        var postId = userServices.createPost(createPostRequest);

        ViewPostRequest viewPostRequest = new ViewPostRequest();
        viewPostRequest.setPostId(postId.getId());
        viewPostRequest.setViewer(createPostRequest.getUserName());
        userServices.viewPost(viewPostRequest);
        assertEquals(1, userServices.numberOfViews());
    }
}