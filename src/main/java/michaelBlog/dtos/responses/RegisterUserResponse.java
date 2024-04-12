package michaelBlog.dtos.responses;

import lombok.Data;

@Data
public class RegisterUserResponse {
    private String id;
    private String date;
    private String username;
}
