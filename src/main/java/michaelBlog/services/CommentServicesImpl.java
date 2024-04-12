package michaelBlog.services;

import michaelBlog.data.model.Comment;
import michaelBlog.data.repository.CommentRepository;
import michaelBlog.dtos.request.*;
import michaelBlog.dtos.responses.CommentResponse;
import michaelBlog.dtos.responses.DeleteCommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static michaelBlog.utils.Mapper2.*;

@Service
public class CommentServicesImpl implements CommentServices{
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public CommentResponse createComment(CreateCommentRequest createCommentRequest) {
        Comment comment = mapCommentToPost(createCommentRequest);
        CommentResponse result = mapResponse(comment);
        commentRepository.save(comment);
        return result;
    }

    @Override
    public DeleteCommentResponse deleteCommentResponse(DeleteCommentRequest deleteCommentRequest, Comment authorComment) {
        commentRepository.delete(authorComment);
        return mapToDeleteComment(authorComment);
    }
    @Override
    public long numberOfComments() {
        return commentRepository.findAll().size();
    }
}
