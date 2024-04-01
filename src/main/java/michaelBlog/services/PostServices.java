package michaelBlog.services;

import michaelBlog.data.model.Post;
import michaelBlog.dtos.request.*;

import java.util.List;

public interface PostServices {
    Post createPost(CreatePostRequest createPostRequest);
    void editPost(EditPostRequest editPostRequest);

    DeletePostResponse deletePostWith(DeleteRequest deletePostRequest, Post authorPost);

    Post retrievePost(RetrievePost retrieveRequest);
    List<Post> getPost(String title);
    long numberOfPost();
}
