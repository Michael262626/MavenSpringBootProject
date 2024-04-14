package michaelBlog.dtos.request;

import lombok.Data;

@Data
public class DeleteCommentRequest {
    private String commentId;
    private String author;
    private String comment;
}
