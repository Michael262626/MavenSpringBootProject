package michaelBlog.services;

import michaelBlog.data.model.Post;
import michaelBlog.dtos.request.CreatePostRequest;
import michaelBlog.dtos.request.DeleteRequest;
import michaelBlog.dtos.request.EditPostRequest;
import michaelBlog.dtos.request.RetrievePost;

import java.util.List;

public class PostServicesImpl implements PostServices{
    @Override
    public void createPost(CreatePostRequest createPostRequest) {

    }

    @Override
    public void editPost(EditPostRequest editPostRequest) {

    }

    @Override
    public void deletePost(DeleteRequest deleteRequest) {

    }

    @Override
    public Post retrievePost(RetrievePost retrieveRequest) {
        return null;
    }

    @Override
    public List<Post> getPost(String title) {
        return null;
    }

    @Override
    public long numberOfPost() {
        return 0;
    }
}
