package michaelBlog.services;

import michaelBlog.data.model.User;
import michaelBlog.data.repository.UserRepository;
import michaelBlog.dtos.request.*;
import michaelBlog.exceptions.UserNameExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static michaelBlog.utils.Mapper.map;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;
    @Override
    public void registerUser(RegisterRequest registerRequest) {
        registerRequest.setUsername(registerRequest.getUsername().toLowerCase());
        validate(registerRequest.getUsername());
        User user = map(registerRequest);
        User userName = userRepository.save(user);
    }
    private void validate(String username) {
        boolean userExist = userRepository.existsByUserName(username);
        if(userExist) throw new UserNameExistException("username already exist");
    }

    @Override
    public void login(LoginRequest loginRequest) {

    }

    @Override
    public void logout(String username) {

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
}
