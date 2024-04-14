package michaelBlog.dtos.responses;

import lombok.Data;

@Data
public class ViewPostResponse {
    private String post;
    private String viewerId;
    private String viewer;
    private String timeOfView;
}