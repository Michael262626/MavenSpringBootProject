package michaelBlog.services;

import michaelBlog.data.model.Comment;
import michaelBlog.data.model.Post;
import michaelBlog.dtos.request.*;
import michaelBlog.dtos.responses.*;

import java.util.Optional;


public interface UserServices {
    RegisterUserResponse registerUser(RegisterRequest registerRequest);
    void login(LoginRequest loginRequest);
    void logout(LogoutRequest logoutRequest);
    void deleteAccount(DeleteUserRequest deleteRequest);
    long numberOfUsers();
    EditPostResponse editPostWith(EditPostRequest editPostRequest);
    CommentResponse createComment(CreateCommentRequest createCommentRequest);
    void deleteComment(DeleteCommentRequest deleteCommentRequest);
    CreatePostResponse createPost(CreatePostRequest createPostRequest);
    long numberOfPost();
    ViewPostResponse viewPost(ViewPostRequest viewPostRequest);
    DeletePostResponse deletePost(DeleteRequest deleteRequestRequest);
    GetUserPostsResponse getUserPosts(GetUserPostsRequest getUserPostsRequest);
    long numberOfComments();

    long numberOfViews();
}
