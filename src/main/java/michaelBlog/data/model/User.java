package michaelBlog.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
@Data
public class User {
    @Id
    private String firstname;
    private String lastname;
    private String password;
    private String id;
    private String userName;
    private List<Post> postList;
}
