package michaelBlog.services;

import michaelBlog.data.model.Post;
import michaelBlog.data.model.User;
import michaelBlog.dtos.request.*;
import michaelBlog.dtos.responses.DeletePostResponse;
import michaelBlog.dtos.responses.EditPostResponse;
import michaelBlog.dtos.responses.ViewPostResponse;

import java.util.List;

public interface PostServices {
    Post createPost(CreatePostRequest createPostRequest);
    EditPostResponse editPostWith(EditPostRequest editPostRequest, Post authorPost);
    ViewPostResponse addViewWith(ViewPostRequest viewPostRequest, User viewer);
    DeletePostResponse deletePostWith(DeleteRequest deletePostRequest, Post authorPost);
    long numberOfPost();

    void deleteComment(DeleteCommentRequest deleteCommentRequest);
}
