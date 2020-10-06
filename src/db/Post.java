package db;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Post {
    private Long id;
    private User user;
    private String title;
    private String shortContent;
    private String content;
    private Timestamp pubDate;

    public Post() { }
    public Post(Long id, User user, String title, String shortContent, String content, Timestamp pubDate) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.shortContent = shortContent;
        this.content = content;
        this.pubDate = pubDate;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getShortContent() { return shortContent; }
    public void setShortContent(String shortContent) { this.shortContent = shortContent; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Timestamp getPubDate() { return pubDate; }
    public void setPubDate(Timestamp pubDate) { this.pubDate = pubDate; }

    public String getDate() {
        SimpleDateFormat monthName = new SimpleDateFormat("MMM");
        SimpleDateFormat day = new SimpleDateFormat("dd");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        String date = monthName.format(pubDate) + " " + day.format(pubDate) + ", " + year.format(pubDate);
        return date;
    }
}
