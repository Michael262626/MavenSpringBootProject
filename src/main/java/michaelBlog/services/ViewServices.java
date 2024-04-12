package michaelBlog.services;

import michaelBlog.data.model.User;
import michaelBlog.data.model.View;

import java.util.List;

public interface ViewServices {
    View saveViewOf(User viewer);
}
