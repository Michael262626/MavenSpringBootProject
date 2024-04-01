package michaelBlog.dtos.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterUserResponse {
    private String id;
    private String date;
    private String username;
}
