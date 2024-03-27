package michaelBlog.services;

import michaelBlog.data.model.Comment;
import michaelBlog.dtos.request.CreateCommentRequest;
import michaelBlog.dtos.request.DeleteCommentRequest;
import michaelBlog.dtos.request.EditCommentRequest;
import michaelBlog.dtos.request.RetrieveCommentRequest;

import java.util.List;

public class CommentServicesImpl implements CommentServices{
    @Override
    public void createComment(CreateCommentRequest createCommentRequest) {

    }

    @Override
    public void editComment(EditCommentRequest editCommentRequest) {

    }

    @Override
    public void deleteComment(DeleteCommentRequest deleteCommentRequest) {

    }

    @Override
    public void retrieveComment(RetrieveCommentRequest retrieveComment) {

    }

    @Override
    public List<Comment> comments(String content) {
        return null;
    }

    @Override
    public long numberOfComments() {
        return 0;
    }
}
