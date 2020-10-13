package db;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Chat {
    private Long id;
    private User user;
    private User opponent;
    private Timestamp createdDate;
    private String latestMsgText;
    private Timestamp latestMsgTime;
    private ArrayList<Message> messages;

    public Chat() { }
    public Chat(Long id, User user, User opponent, Timestamp createdDate, String latestMsgText, Timestamp latestMsgTime, ArrayList<Message> messages) {
        this.id = id;
        this.user = user;
        this.opponent = opponent;
        this.createdDate = createdDate;
        this.latestMsgText = latestMsgText;
        this.latestMsgTime = latestMsgTime;
        this.messages = messages;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public User getOpponent() { return opponent; }
    public void setOpponent(User opponent) { this.opponent = opponent; }
    public Timestamp getCreatedDate() { return createdDate; }
    public void setCreatedDate(Timestamp createdDate) { this.createdDate = createdDate; }
    public String getLatestMsgText() { return latestMsgText; }
    public void setLatestMsgText(String latestMsgText) { this.latestMsgText = latestMsgText; }
    public Timestamp getLatestMsgTime() { return latestMsgTime; }
    public void setLatestMsgTime(Timestamp latestMsgTime) { this.latestMsgTime = latestMsgTime; }
    public ArrayList<Message> getMessages() { return messages; }
    public void setMessages(ArrayList<Message> messages) { this.messages = messages; }

    public int getUnreadMsgCount(User user) {
        int unread = 0;
        for (Message m : messages)
            if (!m.getSender().getId().equals(user.getId()))
                if (!m.isRead())
                    unread++;
        return unread;
    }
    public User viewPoint(User user) {
        if (user.getId().equals(opponent.getId()))
            return this.user;
        return opponent;
    }
    public String getTime() {
        return new SimpleDateFormat("HH:mm dd/MM/yyyy").format(new Date(latestMsgTime.getTime()));
    }
    public String lastSender(User u) {
        String sender = "You: ";
        for (Message m : messages) {
            if (latestMsgText.equals(m.getMessage())) {
                if (!m.getSender().getId().equals(u.getId())) {
                    sender = "";
                    break;
                }
            }
        }
        return sender;
    }
}
