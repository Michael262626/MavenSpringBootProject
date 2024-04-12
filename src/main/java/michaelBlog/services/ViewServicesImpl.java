package michaelBlog.services;

import michaelBlog.data.model.User;
import michaelBlog.data.model.View;
import michaelBlog.data.repository.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static michaelBlog.utils.Mapper2.map;

@Service
public class ViewServicesImpl implements ViewServices{
    @Autowired
    private ViewRepository viewRepository;
    @Override
    public View saveViewOf(User viewer) {
        View newView = map(viewer);
        return viewRepository.save(newView);
    }
}
