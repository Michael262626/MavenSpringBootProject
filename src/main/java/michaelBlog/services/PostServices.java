package michaelBlog.services;

import michaelBlog.data.model.Post;
import michaelBlog.dtos.request.CreatePostRequest;
import michaelBlog.dtos.request.DeleteRequest;
import michaelBlog.dtos.request.EditPostRequest;
import michaelBlog.dtos.request.RetrievePost;

import java.util.List;

public interface PostServices {
    void createPost(CreatePostRequest createPostRequest);
    void editPost(EditPostRequest editPostRequest);
    void deletePost(DeleteRequest deleteRequest);
    Post retrievePost(RetrievePost retrieveRequest);
    List<Post> getPost(String title);
    long numberOfPost();
}
