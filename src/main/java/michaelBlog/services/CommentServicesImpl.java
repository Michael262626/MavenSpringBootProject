package michaelBlog.services;

import michaelBlog.data.model.Comment;
import michaelBlog.data.model.User;
import michaelBlog.data.repository.CommentRepository;
import michaelBlog.data.repository.UserRepository;
import michaelBlog.dtos.request.*;
import michaelBlog.dtos.responses.CommentResponse;
import michaelBlog.dtos.responses.DeleteCommentResponse;
import michaelBlog.exceptions.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static michaelBlog.utils.Mapper2.*;

@Service
public class CommentServicesImpl implements CommentServices{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public CommentResponse createComment(CreateCommentRequest createCommentRequest) {
        Comment comment = mapCommentToPost(createCommentRequest);
        CommentResponse result = mapResponse(comment);
        commentRepository.save(comment);
        return result;
    }

    @Override
    public Comment removeComment(DeleteCommentRequest deleteCommentRequest) {
        User user = userRepository.findByUserName(deleteCommentRequest.getAuthor());
        if(user == null) throw new UsernameNotFoundException("User is not authorized to delete a comment");
        Comment comment = commentRepository.findCommentBy(deleteCommentRequest.getComment());
        commentRepository.delete(comment);
        return comment;
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }
    @Override
    public long numberOfComments() {
        return commentRepository.findAll().size();
    }
}
