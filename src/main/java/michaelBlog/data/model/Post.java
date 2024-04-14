package michaelBlog.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@Document("post")
public class Post {
    private String title;
    private LocalDateTime localDateTime = LocalDateTime.now();
    @DBRef
    private List<Comment> comments = new ArrayList<>();
    @Id
    private String id;
    private User user;
    @DBRef
    private List<View> views = new ArrayList<>();
    private String content;
}
