package db;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private Long id;
    private User user;
    private User sender;
    private String message;
    private boolean read;
    private Timestamp date;

    public Message() { }
    public Message(Long id, User user, User sender, String message, boolean read, Timestamp date) {
        this.id = id;
        this.user = user;
        this.sender = sender;
        this.message = message;
        this.read = read;
        this.date = date;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public User getSender() { return sender; }
    public void setSender(User sender) { this.sender = sender; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public boolean isRead() { return read; }
    public void setRead(boolean read) { this.read = read; }
    public Timestamp getDate() { return date; }
    public void setDate(Timestamp date) { this.date = date; }

    public User viewPoint(User user) {
        if (user.getId().equals(sender.getId()))
            return this.user;
        return sender;
    }
    public String getDatetime() {
        return new SimpleDateFormat("HH:mm dd.MM.yyyy").format(new Date(date.getTime()));
    }
    public String getDateOnly() {
        return new SimpleDateFormat("dd.MM.yyyy").format(new Date(date.getTime()));
    }
    public String getTime() {
        return new SimpleDateFormat("HH:mm").format(new Date(date.getTime()));
    }

}
