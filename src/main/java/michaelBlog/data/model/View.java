package michaelBlog.data.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class View {
    private LocalDateTime timeOfView = LocalDateTime.now();
    private User viewer;
    private String id;
}
