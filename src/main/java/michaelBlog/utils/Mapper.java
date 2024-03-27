package michaelBlog.utils;

import michaelBlog.data.model.User;
import michaelBlog.dtos.request.RegisterRequest;

public class Mapper {
    public static User map(RegisterRequest registerRequest) {
        User user = new User();
        user.setFirstname(registerRequest.getFirstName());
        user.setLastname(registerRequest.getLastName());
        user.setUserName(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        return user;
    }
}
