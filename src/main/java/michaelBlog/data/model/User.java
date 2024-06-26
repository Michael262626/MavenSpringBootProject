package michaelBlog.data.model;

import lombok.Data;
import michaelBlog.data.model.Post;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Data
@Document
public class User {
    private String firstname;
    private String lastname;
    private String password;
    private boolean isLocked = true;
    @Id
    private String id;
    private String userName;
    @DBRef
    private List<Post> posts = new ArrayList<>();

    public void addPost(Post post) {
            if (posts == null) {
                posts = new ArrayList<>();
            }
            posts.add(post);
            post.setUser(this);
    }
}
