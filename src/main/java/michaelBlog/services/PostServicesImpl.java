package michaelBlog.services;

import michaelBlog.data.model.Post;
import michaelBlog.data.repository.PostRepository;
import michaelBlog.dtos.request.*;
import michaelBlog.utils.Mapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static michaelBlog.utils.Mapper2.mapDeletePostResponseWith;
@Service
public class PostServicesImpl implements PostServices{
    @Autowired
    private PostRepository postRepository;
    @Override
    public Post createPost(CreatePostRequest createPostRequest) {
        Post newPost = Mapper2.map(createPostRequest);
        return postRepository.save(newPost);
    }

    @Override
    public void editPost(EditPostRequest editPostRequest) {

    }

    @Override
        public DeletePostResponse deletePostWith(DeleteRequest deletePostRequest, Post authorPost) {
        postRepository.delete(authorPost);
        return mapDeletePostResponseWith(authorPost);
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
