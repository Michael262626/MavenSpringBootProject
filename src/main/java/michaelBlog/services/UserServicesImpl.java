package michaelBlog.services;

import michaelBlog.data.model.User;
import michaelBlog.data.repository.UserRepository;
import michaelBlog.dtos.request.*;
import michaelBlog.exceptions.InvalidPasswordException;
import michaelBlog.exceptions.NullValueException;
import michaelBlog.exceptions.UserNameExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        userRepository.save(user);
    }
    private void validate(String username) {
        boolean userExist = userRepository.existsByUserName(username);
        if(userExist) throw new UserNameExistException("username already exist");
    }

    @Override
    public void login(LoginRequest loginRequest) {
        User user = findById(loginRequest.getUsername());
        if(isPasswordIncorrect(user, loginRequest.getPassword())) throw new InvalidPasswordException("Invalid password");
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
        Optional<User> foundUser = userRepository.findById(username.toLowerCase());
        if(foundUser.isEmpty()) throw new NullValueException("Username field cannot be empty");

        return foundUser.get();
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
}
