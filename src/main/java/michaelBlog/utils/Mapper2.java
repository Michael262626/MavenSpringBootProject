package michaelBlog.utils;

import michaelBlog.data.model.Comment;
import michaelBlog.data.model.Post;
import michaelBlog.data.model.User;
import michaelBlog.data.model.View;
import michaelBlog.dtos.request.*;
import michaelBlog.dtos.responses.*;
import michaelBlog.exceptions.NullValueException;

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
        createPostResponse.setId(post.getId());
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
    public static Comment mapCommentToPost(CreateCommentRequest createCommentRequest){
        Comment comment = new Comment();
        validateComment(createCommentRequest);
        comment.setComment(createCommentRequest.getContent());
        comment.setCommenter(createCommentRequest.getAuthor());
        return comment;
    }
    public static void validateComment(CreateCommentRequest comment) {
        if(comment.getContent() == null || comment.getContent().isEmpty()) throw new NullValueException("Content required");
        if(comment.getAuthor() == null || comment.getAuthor().isEmpty()) throw new NullValueException("Name required");
    }
    public static CommentResponse mapResponse(Comment comment){
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setContent(comment.getComment());
        commentResponse.setId(comment.getId());
        commentResponse.setUsername(comment.getCommenter());
        return commentResponse;
    }

    public static DeleteCommentResponse mapToDeleteComment(Comment comment){
        DeleteCommentResponse deleteCommentResponse = new DeleteCommentResponse();
        deleteCommentResponse.setId(comment.getId());
        return deleteCommentResponse;
    }
    public static View map(User viewer) {
        View view = new View();
        view.setViewer(viewer);
        return view;
    }
    public static EditPostResponse mapEditPostResponseWith(Post post) {
        EditPostResponse editPostResponse = new EditPostResponse();
        editPostResponse.setPostId(post.getId());
        editPostResponse.setTitle(post.getTitle());
        editPostResponse.setContent(post.getContent());
        editPostResponse.setDateCreated(DateTimeFormatter
                .ofPattern("dd/MMM/yyyy 'at' HH:mm:ss a").format(post.getLocalDateTime()));
        return editPostResponse;
    }
    public static Post map(EditPostRequest editPostRequest, Post post) {
        post.setTitle(editPostRequest.getTitle());
        post.setContent(editPostRequest.getContent());
        return post;
    }
    public static GetUserPostsResponse mapGetUserPostsResponse(User user) {
        GetUserPostsResponse getUserPostsResponse = new GetUserPostsResponse();
        getUserPostsResponse.setUserId(user.getId());
        getUserPostsResponse.setUsername(user.getUserName());
        getUserPostsResponse.setUserPost(user.getPosts().toString());
        return getUserPostsResponse;
    }

    public static ViewPostResponse mapViewPostResponseWith(View view, Post post) {
        ViewPostResponse viewPostResponse = new ViewPostResponse();
        viewPostResponse.setPost(post.toString());
        viewPostResponse.setViewerId(view.getViewer().getId());
        viewPostResponse.setViewer(view.getViewer().getUserName());
        viewPostResponse.setTimeOfView(DateTimeFormatter
                .ofPattern("dd/MMM/yyyy 'at' hh:mm:ss a").format(view.getTimeOfView()));
        return viewPostResponse;
    }
}
