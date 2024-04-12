package michaelBlog.dtos.request;

import lombok.Data;

@Data
public class ViewPostRequest {
    private String viewer;
    private String postId;
}
