package michaelBlog.dtos.request;

import lombok.Data;

@Data
public class CreateCommentRequest {
    private String postId;
    private String author;
    private String content;
}
