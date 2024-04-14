package michaelBlog.utils;

import michaelBlog.data.model.User;
import michaelBlog.dtos.request.RegisterRequest;
import michaelBlog.dtos.responses.RegisterUserResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mapper {
    public static User map(RegisterRequest registerRequest) {
        User user = new User();
        validateRegisterRequest(registerRequest);
        user.setFirstname(registerRequest.getFirstName());
        user.setLastname(registerRequest.getLastName());
        user.setUserName(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        return user;
    }
    public static void validateRegisterRequest(RegisterRequest registerRequest) {
        if (registerRequest == null) {
            throw new IllegalArgumentException("RegisterRequest must not be null.");
        }
        if (registerRequest.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name field is required.");
        }
        if (registerRequest.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name field is required.");
        }
        if (registerRequest.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username field is required.");
        }
        if (registerRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password field is required.");
        }
    }
    public static RegisterUserResponse map(User user){
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setId(user.getId());
        if (user.getUserName() == null) {
            throw new IllegalArgumentException("Username field is mandatory.");
        }
        registerUserResponse.setUsername(user.getUserName());
        registerUserResponse.setDate(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now()));
        return registerUserResponse;
    }
}
