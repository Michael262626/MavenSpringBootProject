package michaelBlog.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Comment {
    @Id
    private String id;
    private User commenter;
    private String comment;
}
