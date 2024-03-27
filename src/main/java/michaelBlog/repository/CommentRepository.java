package michaelBlog.repository;

import michaelBlog.data.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
