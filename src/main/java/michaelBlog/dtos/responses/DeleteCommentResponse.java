package michaelBlog.dtos.responses;

import lombok.Data;

@Data
public class DeleteCommentResponse {
    private String id;
    private String author;
}
