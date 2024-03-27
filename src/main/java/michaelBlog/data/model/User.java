package michaelBlog.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Document
public class User {
    private String firstname;
    private String lastname;
    private String password;
    @Id
    private String id;
    private String userName;
    private List<Post> postList;
}
