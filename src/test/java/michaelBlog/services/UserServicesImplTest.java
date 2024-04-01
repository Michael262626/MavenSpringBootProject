package michaelBlog.services;

import michaelBlog.data.repository.UserRepository;
import michaelBlog.dtos.request.*;
import michaelBlog.exceptions.InvalidPasswordException;
import michaelBlog.exceptions.UserNameExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServicesImplTest {

    @Autowired
    private UserServices userServices;
    @Autowired
    private UserRepository userRepository;
    @BeforeEach
    public void  setUp(){
        userRepository.deleteAll();
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
        deleteRequest.setAuthor(registerRequest.getUsername());
        var foundUser = userRepository.findByUserName(registerRequest.getUsername().toLowerCase());
        var savedPost = foundUser.getPosts().getFirst();
        deleteRequest.setPostId(savedPost.getId());
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

}