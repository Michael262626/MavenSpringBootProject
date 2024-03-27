package michaelBlog.data.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
public class Post {
    private String title;
    private LocalDateTime localDateTime = LocalDateTime.now();
    private List<Comment> comments = new ArrayList<>();
    private String id;
    private List<View> views = new ArrayList<>();
    private String content;
}
