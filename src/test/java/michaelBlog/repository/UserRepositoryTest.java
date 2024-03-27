package michaelBlog.repository;

import michaelBlog.data.model.User;
import michaelBlog.data.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;
@DataMongoTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    public void testSave(){
        User user = new User();
        userRepository.save(user);
        assertEquals(1, userRepository.count());
    }

}