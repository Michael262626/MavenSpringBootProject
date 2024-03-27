package services;

import michaelBlog.data.model.Comment;
import michaelBlog.dtos.request.CreateCommentRequest;
import michaelBlog.dtos.request.DeleteCommentRequest;
import michaelBlog.dtos.request.EditCommentRequest;
import michaelBlog.dtos.request.RetrieveCommentRequest;

import java.util.List;

public interface CommentServices {
    void createComment(CreateCommentRequest createCommentRequest);
    void editComment(EditCommentRequest editCommentRequest);
    void deleteComment(DeleteCommentRequest deleteCommentRequest);
    void retrieveComment(RetrieveCommentRequest retrieveComment);
    List<Comment> comments(String content);
    long numberOfComments();
}
