package michaelBlog.utils;

import michaelBlog.data.model.Post;
import michaelBlog.dtos.request.CreatePostRequest;
import michaelBlog.dtos.request.CreatePostResponse;
import michaelBlog.dtos.request.DeletePostResponse;
import michaelBlog.dtos.request.RegisterUserResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mapper2 {
    public static Post post(CreatePostRequest createPostRequest) {
        Post post = new Post();
        createPostRequest.setTitle(createPostRequest.getTitle());
        createPostRequest.setContent(createPostRequest.getContent());
        return post;
    }
    public static CreatePostResponse post(Post post) {
        CreatePostResponse createPostResponse = new CreatePostResponse();
        createPostResponse.setTitle(post.getTitle());
        createPostResponse.setContent(post.getContent());
        createPostResponse.setDate(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now()));
        return createPostResponse;
    }
    public static Post map(CreatePostRequest createPostRequest) {
        Post post = new Post();
        post.setTitle(createPostRequest.getTitle());
        post.setContent(createPostRequest.getContent());
        return post;
    }
    public static DeletePostResponse mapDeletePostResponseWith(Post post) {
        DeletePostResponse deletePostResponse = new DeletePostResponse();
        deletePostResponse.setId(post.getId());
        return deletePostResponse;
    }
}
