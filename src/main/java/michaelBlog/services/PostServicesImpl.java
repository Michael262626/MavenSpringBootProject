package michaelBlog.services;

import michaelBlog.data.model.Comment;
import michaelBlog.data.model.Post;
import michaelBlog.data.model.User;
import michaelBlog.data.model.View;
import michaelBlog.data.repository.PostRepository;
import michaelBlog.dtos.request.*;
import michaelBlog.dtos.responses.DeletePostResponse;
import michaelBlog.dtos.responses.EditPostResponse;
import michaelBlog.dtos.responses.ViewPostResponse;
import michaelBlog.exceptions.PostNotFoundException;
import michaelBlog.utils.Mapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static michaelBlog.utils.Mapper2.*;

@Service
public class PostServicesImpl implements PostServices{
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ViewServicesImpl viewServicesImpl;
    @Autowired
    private CommentServicesImpl commentServicesImpl;

    @Override
    public Post createPost(CreatePostRequest createPostRequest) {
        Post newPost = Mapper2.map(createPostRequest);
        return postRepository.save(newPost);
    }

    @Override
    public EditPostResponse editPostWith(EditPostRequest editPostRequest, Post authorPost) {
        Post editedPost = map(editPostRequest, authorPost);
        postRepository.save(editedPost);
        return mapEditPostResponseWith(editedPost);
    }


    @Override
        public DeletePostResponse deletePostWith(DeleteRequest deletePostRequest, Post authorPost) {
        postRepository.delete(authorPost);
        return mapDeletePostResponseWith(authorPost);
    }
    @Override
    public ViewPostResponse addViewWith(ViewPostRequest viewPostRequest, User viewer) {
        Post post = findPostBy(viewPostRequest.getPostId());
        View newView = viewServicesImpl.saveViewOf(viewer);
        post.getViews().add(newView);
        Post savedPost = postRepository.save(post);
        return mapViewPostResponseWith(newView, savedPost);
    }
    private Post findPostBy(String id) {
        Optional<Post> foundPost = postRepository.findById(id);
        if (foundPost.isEmpty()) throw new PostNotFoundException("Post not found");
        return foundPost.get();
    }
    @Override
    public long numberOfPost() {
        return postRepository.findAll().size();
    }

    @Override
    public void deleteComment(DeleteCommentRequest deleteCommentRequest) {
        Post post = findPostByTitleAndAuthor(deleteCommentRequest.getAuthor());
        Comment comment = commentServicesImpl.removeComment(deleteCommentRequest);
        post.getComments().remove(comment);
        postRepository.save(post);
    }

    private Post findPostByTitleAndAuthor(String username) {
        Post post = postRepository.findBy(username);
        if (post == null) throw new PostNotFoundException("Post not found");
        return post;
    }
}
