package michaelBlog.data.repository;

import michaelBlog.data.model.Comment;
import michaelBlog.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUserName(String username);
    User findByUserName(String username);
}
