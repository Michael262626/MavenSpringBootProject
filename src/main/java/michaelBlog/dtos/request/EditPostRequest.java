package michaelBlog.dtos.request;

import lombok.Data;

@Data
public class EditPostRequest {
    private String title;
    private String content;
}
