package michaelBlog.data.repository;

import michaelBlog.data.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    Comment findCommentBy(String author);

    List<Comment> findByCommenter(String author);
}
