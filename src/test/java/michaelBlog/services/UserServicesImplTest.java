package michaelBlog.services;

import michaelBlog.data.repository.UserRepository;
import michaelBlog.dtos.request.RegisterRequest;
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


}