package michaelBlog.controller;

import michaelBlog.dtos.request.*;
import michaelBlog.dtos.responses.ApiResponse;
import michaelBlog.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/blog")
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            var result = userServices.registerUser(registerRequest);
                return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
            }
            catch (Exception e) {
                return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
            }
        }

    @PatchMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            userServices.login(loginRequest);
            return new ResponseEntity<>(new ApiResponse(true, NO_CONTENT), OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("/logout")
    public ResponseEntity<?> logout( @RequestBody LogoutRequest logoutRequest) {
        try {
            userServices.logout(logoutRequest);
            return new ResponseEntity<>(new ApiResponse(true, NO_CONTENT), OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> createPost( @RequestBody CreatePostRequest createPostRequest) {
        try {
            var result = userServices.createPost(createPostRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PutMapping("/edit-post")
    public ResponseEntity<?> editPost(@RequestBody EditPostRequest editPostRequest) {
        try {
            var result = userServices.editPostWith(editPostRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-post")
    public ResponseEntity<?> deletePost(@RequestBody DeleteRequest deletePostRequest) {
        try {
            var result = userServices.deletePost(deletePostRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete-comment")
    public ResponseEntity<?> deleteComment(@RequestBody DeleteCommentRequest deleteCommentRequest) {
        try {
            userServices.deleteComment(deleteCommentRequest);
            return new ResponseEntity<>(new ApiResponse(true, NO_CONTENT), OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteAccount(@RequestBody DeleteUserRequest deleteUserRequest) {
        try {
            userServices.deleteAccount(deleteUserRequest);
            return new ResponseEntity<>(new ApiResponse(true, "Account Deleted"), OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("/view-post")
    public ResponseEntity<?> viewPost(@RequestBody ViewPostRequest viewPostRequest) {
        try {
            var result = userServices.viewPost(viewPostRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("/comment")
    public ResponseEntity<?> addComment(@RequestBody CreateCommentRequest commentRequest) {
        try {
            var result = userServices.createComment(commentRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @GetMapping("/view-posts")
    public ResponseEntity<?> getUserPosts(@RequestBody GetUserPostsRequest getUserPostsRequest) {
        try {
            var result = userServices.getUserPosts(getUserPostsRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
}
