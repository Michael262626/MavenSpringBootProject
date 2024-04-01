package michaelBlog.services;

import michaelBlog.dtos.request.*;


public interface UserServices {
    RegisterUserResponse registerUser(RegisterRequest registerRequest);
    void login(LoginRequest loginRequest);
    void logout(String username);
    void updateProfile(UpdateRequest updateRequest);
    void deleteAccount(DeleteUserRequest deleteRequest);
    long numberOfUsers();
    CreatePostResponse createPost(CreatePostRequest createPostRequest);
    long numberOfPost();
    DeletePostResponse deletePost(DeleteRequest deleteRequestRequest);
}
