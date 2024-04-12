package michaelBlog.dtos.request;

import lombok.Data;

@Data
public class EditPostRequest {
    private String id;
    private String title;
    private String content;
    private String author;
}
