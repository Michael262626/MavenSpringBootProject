package michaelBlog.services;

import michaelBlog.data.model.Comment;
import michaelBlog.dtos.request.*;
import michaelBlog.dtos.responses.CommentResponse;
import michaelBlog.dtos.responses.DeleteCommentResponse;

import java.util.List;

public interface CommentServices {
    CommentResponse createComment(CreateCommentRequest createCommentRequest);
    DeleteCommentResponse deleteCommentResponse(DeleteCommentRequest deleteCommentRequest, Comment authorComment);
    long numberOfComments();
}
