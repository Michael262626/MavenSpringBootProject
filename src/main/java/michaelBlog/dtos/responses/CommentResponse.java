package michaelBlog.dtos.responses;

import lombok.Data;

@Data
public class CommentResponse {
    private String id;
    private String content;
    private String username;
}
