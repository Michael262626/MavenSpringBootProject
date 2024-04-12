package michaelBlog.dtos.responses;

import lombok.Data;

@Data
public class CreatePostResponse {
    private String title;
    private String content;
    private String date;
    private String id;
}
