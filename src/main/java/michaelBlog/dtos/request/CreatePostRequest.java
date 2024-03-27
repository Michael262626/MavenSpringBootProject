package michaelBlog.dtos.request;

import lombok.Data;

@Data
public class CreatePostRequest {
    private String title;
    private String content;
    private String username;
}
