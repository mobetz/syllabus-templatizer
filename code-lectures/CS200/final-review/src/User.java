import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class User implements PropertyChangeListener {

    private String account_name;

    private List<Post> posts;


    private PropertyChangeSupport subscribers;


    private List<Post> timeline;


    public User(String username) {
        this.account_name = username;
        this.posts = new ArrayList<>();
        this.timeline = new ArrayList<>();

        this.subscribers = new PropertyChangeSupport(this);
    }

    public String getName() {
        return this.account_name;
    }


    public void add_follower(User follower) {
        this.subscribers.addPropertyChangeListener("new_post", follower);
    }


    public void create_post(String message) {
        Post new_post = new Post(this, message);
        posts.add(new_post);

        this.subscribers.firePropertyChange("new_post", null, new_post);

    }


    public String getTimeline() {
        String timeline_text = "";

        for ( Post post : this.timeline) {
            timeline_text = timeline_text + "\n " + post;
        }

        return timeline_text;
    }


    @Override
    public void propertyChange(PropertyChangeEvent update) {
        if ( update.getPropertyName().equals("new_post")) {
            this.timeline.add((Post) update.getNewValue());
        }
    }
}