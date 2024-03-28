package michaelBlog.services;

import michaelBlog.data.model.View;

import java.util.List;

public interface ViewServices {
    List<View> viewers();
    long numberOfViews();
}
