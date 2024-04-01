package michaelBlog.dtos.request;

import lombok.Data;

@Data
public class DeleteRequest {
    private String postId;
    private String author;
}
