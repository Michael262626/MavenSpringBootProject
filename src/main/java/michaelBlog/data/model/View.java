package michaelBlog.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Document
public class View {
    private LocalDateTime timeOfView = LocalDateTime.now();
    private User viewer;
    private String id;
}
