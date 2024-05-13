public class Post {
    

    private User author;
    private String body;


    public Post(User given_author, String given_body) {
        this.author = given_author;
        this.body = given_body;
    }


    public String getPostContent() {
        return body;
    }


    @Override
    public String toString() {
        return "@" + this.author.getName() + ": " + this.body;
    }
}
