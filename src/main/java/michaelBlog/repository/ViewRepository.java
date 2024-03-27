package michaelBlog.repository;

import michaelBlog.data.model.View;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ViewRepository extends MongoRepository<View, String> {
}
