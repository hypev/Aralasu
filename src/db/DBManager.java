package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class DBManager {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/task5?useUnicode=true&serverTimezone=UTC",
                    "root",
                    "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User getUser(Long id) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM users " +
                    "WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        new java.util.Date(resultSet.getDate("birthdate").getTime()),
                        resultSet.getString("picture_url")
                );
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public static User getUser(String email) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM users " +
                    "WHERE email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        new java.util.Date(resultSet.getDate("birthdate").getTime()),
                        resultSet.getString("picture_url")
                );
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public static Post getPost(Long id) {
        Post post = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM posts " +
                    "WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                post = new Post(
                        resultSet.getLong("id"),
                        getUser(resultSet.getLong("author_id")),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("pub_date")
                );
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }
    public static Chat getChat(Long id) {
        Chat chat = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM chats " +
                    "WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                chat = new Chat(
                        resultSet.getLong("id"),
                        getUser(resultSet.getLong("user_id")),
                        getUser(resultSet.getLong("opponent_user_id")),
                        resultSet.getTimestamp("created_date"),
                        resultSet.getString("latest_message_text"),
                        resultSet.getTimestamp("latest_message_time"),
                        getMessagesByChatId(resultSet.getLong("id"))
                );
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chat;
    }
    public static Chat getChatBetween(Long user_id, Long opponent_id) {
        Chat chat = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM chats " +
                    "WHERE user_id=? AND opponent_user_id=? OR " +
                    "user_id=? AND opponent_user_id=?");
            preparedStatement.setLong(1, user_id);
            preparedStatement.setLong(2, opponent_id);
            preparedStatement.setLong(3, opponent_id);
            preparedStatement.setLong(4, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                chat = new Chat(
                        resultSet.getLong("id"),
                        getUser(resultSet.getLong("user_id")),
                        getUser(resultSet.getLong("opponent_user_id")),
                        resultSet.getTimestamp("created_date"),
                        resultSet.getString("latest_message_text"),
                        resultSet.getTimestamp("latest_message_time"),
                        getMessagesByChatId(resultSet.getLong("id"))
                );
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chat;
    }
    public static AuthToken getAuthToken(String selector) {
        AuthToken authToken = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM auth_tokens " +
                    "WHERE selector=?");
            preparedStatement.setString(1, selector);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                authToken = new AuthToken(
                        resultSet.getLong("id"),
                        resultSet.getString("selector"),
                        resultSet.getString("validator"),
                        getUser(resultSet.getLong("user_id"))
                );
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authToken;
    }
    public static AuthToken getAuthToken(Long user_id) {
        AuthToken authToken = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM auth_tokens " +
                    "WHERE user_id=?");
            preparedStatement.setLong(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                authToken = new AuthToken(
                        resultSet.getLong("id"),
                        resultSet.getString("selector"),
                        resultSet.getString("validator"),
                        getUser(resultSet.getLong("user_id"))
                );
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authToken;
    }
    public static String getStatusBetween(User from, User to) {
        String status = "none";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM friends " +
                    "WHERE user_id=? AND friend_id=? OR " +
                    "user_id=? AND friend_id=?");
            preparedStatement.setLong(1, from.getId());
            preparedStatement.setLong(2, to.getId());
            preparedStatement.setLong(3, to.getId());
            preparedStatement.setLong(4, from.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                status = "friends";
            } else {
                preparedStatement = connection.prepareStatement("" +
                        "SELECT * " +
                        "FROM friend_request " +
                        "WHERE user_id=? AND request_sender_id=?");
                preparedStatement.setLong(1, from.getId());
                preparedStatement.setLong(2, to.getId());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    status = "request";
                } else {
                    preparedStatement = connection.prepareStatement("" +
                            "SELECT * " +
                            "FROM friend_request " +
                            "WHERE user_id=? AND request_sender_id=?");
                    preparedStatement.setLong(1, to.getId());
                    preparedStatement.setLong(2, from.getId());
                    resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        status = "sent";
                    }
                }
            }
            preparedStatement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static boolean addUser(User user) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "INSERT INTO users (email, password, full_name, birthdate, picture_url) " +
                    "VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setDate(4, new java.sql.Date(user.getBirthdate().getTime()));
            preparedStatement.setString(5, user.getPictureUrl());
            rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }
    public static boolean addFriend(User user, Long addingId) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "INSERT INTO friend_request (user_id, request_sender_id, sent_time) " +
                    "VALUES (?, ?, NOW())"
            );
            preparedStatement.setLong(1, addingId);
            preparedStatement.setLong(2, user.getId());
            rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }
    public static boolean addMessage(Message message) {
        int rows = 0;
        try {
            Chat chat = getChatBetween(message.getUser().getId(), message.getSender().getId());
            if (chat != null) {
                chat.setLatestMsgText(message.getMessage());
                DBManager.editChat(chat);
            } else {
                DBManager.addChat(new Chat(null, message.getUser(), message.getSender(), null, message.getMessage(), null, null));
            }
            chat = getChatBetween(message.getUser().getId(), message.getSender().getId());
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "INSERT INTO messages (chat_id, user_id, sender_id, message_text, read_by_receive, sent_date) " +
                    "VALUES (?, ?, ?, ?, false, CURRENT_TIMESTAMP())"
            );
            preparedStatement.setLong(1, chat.getId());
            preparedStatement.setLong(2, message.getUser().getId());
            preparedStatement.setLong(3, message.getSender().getId());
            preparedStatement.setString(4, message.getMessage());
            rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }
    public static void addChat(Chat chat) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "INSERT INTO chats (user_id, opponent_user_id, created_date, latest_message_text, latest_message_time) " +
                    "VALUES (?, ?, CURRENT_TIMESTAMP(), ?, CURRENT_TIMESTAMP())");
            preparedStatement.setLong(1, chat.getUser().getId());
            preparedStatement.setLong(2, chat.getOpponent().getId());
            preparedStatement.setString(3, chat.getLatestMsgText());
            rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean acceptFriend(User user, Long requesterId) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "INSERT INTO friends (user_id, friend_id, added_time) " +
                    "VALUES (?, ?, NOW())"
            );
            preparedStatement.setLong(1, requesterId);
            preparedStatement.setLong(2, user.getId());
            rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }
    public static boolean addPost(Post post) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "INSERT INTO posts (author_id, title, short_content, content) " +
                    "VALUES (?, ?, ?, ?)");
            preparedStatement.setLong(1, post.getUser().getId());
            preparedStatement.setString(2, post.getTitle());
            preparedStatement.setString(3, post.getShortContent());
            preparedStatement.setString(4, post.getContent());
            rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }
    public static boolean addAuthToken(AuthToken authToken) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement;
            if (getAuthToken(authToken.getUser().getId()) == null) {
                preparedStatement = connection.prepareStatement("" +
                        "INSERT INTO auth_tokens (selector, validator, user_id) " +
                        "VALUES (?, ?, ?)");
                preparedStatement.setString(1, authToken.getSelector());
                preparedStatement.setString(2, authToken.getValidator());
                preparedStatement.setLong(3, authToken.getUser().getId());

            } else {
                preparedStatement = connection.prepareStatement("" +
                        "UPDATE auth_tokens " +
                        "SET selector=?, validator=? " +
                        "WHERE user_id=?");
                preparedStatement.setString(1, authToken.getSelector());
                preparedStatement.setString(2, authToken.getValidator());
                preparedStatement.setLong(3, authToken.getUser().getId());
            }
            rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static boolean editUser(User user) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "UPDATE users " +
                    "SET email=?, password=?, full_name=?, birthdate=?, picture_url=? " +
                    "WHERE id=?");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setDate(4, new java.sql.Date(user.getBirthdate().getTime()));
            preparedStatement.setString(5, user.getPictureUrl());
            preparedStatement.setLong(6, user.getId());
            rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }
    public static boolean editPost(Post post) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "UPDATE posts " +
                    "SET title=?, short_content=?, content=? " +
                    "WHERE id=?");
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getShortContent());
            preparedStatement.setString(3, post.getContent());
            preparedStatement.setLong(4, post.getId());
            rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }
    public static boolean editAuthToken(AuthToken authToken) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "UPDATE auth_tokens " +
                    "SET selector=?, validator=?, user_id=? " +
                    "WHERE id=?");
            preparedStatement.setString(1, authToken.getSelector());
            preparedStatement.setString(2, authToken.getValidator());
            preparedStatement.setLong(3, authToken.getUser().getId());
            preparedStatement.setLong(4, authToken.getId());
            rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }
    public static void editChat(Chat chat) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "UPDATE chats " +
                    "SET latest_message_text=?, latest_message_time=CURRENT_TIMESTAMP() " +
                    "WHERE id=?");
            preparedStatement.setString(1, chat.getLatestMsgText());
            preparedStatement.setLong(2, chat.getId());
            rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean editMessage(Message message) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "UPDATE messages " +
                    "SET read_by_receive=true, sent_date=?" +
                    "WHERE id=?");
            preparedStatement.setTimestamp(1, message.getDate());
            preparedStatement.setLong(2, message.getId());
            rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static boolean deletePost(Long id) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "DELETE FROM posts WHERE id=?");
            preparedStatement.setLong(1, id);
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }
    public static boolean deleteFriend(Long user_id, Long friendId) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "DELETE FROM friends WHERE user_id=? AND friend_id=?"
            );
            preparedStatement.setLong(1, user_id);
            preparedStatement.setLong(2, friendId);
            rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }
    public static boolean deleteFriendRequest(User user, Long requesterId) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "DELETE FROM friend_request WHERE user_id=? AND request_sender_id=?"
            );
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setLong(2, requesterId);
            rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }
    public static void deleteAuthToken(String selector) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "DELETE FROM auth_tokens WHERE selector=?");
            preparedStatement.setString(1, selector);
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> getAllUserByFullname(String fullname) {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM users " +
                    "WHERE full_name LIKE '%" + fullname + "%' " +
                    "ORDER BY full_name");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        new java.util.Date(resultSet.getDate("birthdate").getTime()),
                        resultSet.getString("picture_url")
                ));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static ArrayList<Post> getAllPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM posts " +
                    "ORDER BY pub_date DESC ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                posts.add(new Post(
                        resultSet.getLong("id"),
                        getUser(resultSet.getLong("author_id")),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("pub_date")
                ));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }
    public static ArrayList<Post> getAllPostsByUserId(Long author_id) {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM posts " +
                    "WHERE author_id=? " +
                    "ORDER BY pub_date DESC ");
            preparedStatement.setLong(1, author_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                posts.add(new Post(
                        resultSet.getLong("id"),
                        getUser(resultSet.getLong("author_id")),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("pub_date")
                ));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }
    public static ArrayList<User> getLastUsersByBirthDateOrder(int count) {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM users " +
                    "ORDER BY MONTH(birthdate) DESC, DAY(birthdate) DESC");
            ResultSet resultSet = preparedStatement.executeQuery();
            int i = 0;
            while (resultSet.next() && i < count) {
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        new Date(resultSet.getDate("birthdate").getTime()),
                        resultSet.getString("picture_url")
                ));
                i++;
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static ArrayList<User> getAllFriendsByUserId(Long user_id) {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM friends " +
                    "WHERE user_id=? OR friend_id=? " +
                    "ORDER BY added_time DESC "
            );
            preparedStatement.setLong(1, user_id);
            preparedStatement.setLong(2, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("friend_id");
                if (id.equals(user_id))
                    id = resultSet.getLong("user_id");
                users.add(
                        getUser(id)
                );
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    public static ArrayList<User> getAllFriendRequestByUserId(Long user_id) {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM friend_request " +
                    "WHERE user_id=? " +
                    "ORDER BY sent_time DESC"
            );
            preparedStatement.setLong(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(
                        getUser(resultSet.getLong("request_sender_id"))
                );
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static ArrayList<Chat> getChatsByUserId(Long user_id) {
        ArrayList<Chat> chats = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM chats " +
                    "WHERE user_id=? OR opponent_user_id=? " +
                    "ORDER BY latest_message_time DESC "
            );
            preparedStatement.setLong(1, user_id);
            preparedStatement.setLong(2, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                chats.add(new Chat(
                        resultSet.getLong("id"),
                        getUser(resultSet.getLong("user_id")),
                        getUser(resultSet.getLong("opponent_user_id")),
                        resultSet.getTimestamp("created_date"),
                        resultSet.getString("latest_message_text"),
                        resultSet.getTimestamp("latest_message_time"),
                        getMessagesByChatId(resultSet.getLong("id"))
                ));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chats;
    }
    public static ArrayList<Message> getMessagesByChatId(Long chat_id) {
        ArrayList<Message> messages = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM messages " +
                    "WHERE chat_id=? " +
                    "ORDER BY sent_date "
            );
            preparedStatement.setLong(1, chat_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                messages.add( new Message(
                        resultSet.getLong("id"),
                        getUser(resultSet.getLong("user_id")),
                        getUser(resultSet.getLong("sender_id")),
                        resultSet.getString("message_text"),
                        resultSet.getBoolean("read_by_receive"),
                        resultSet.getTimestamp("sent_date")
                ));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }
    public static ArrayList<Message> getMessagesByText(String text, Long user_id) {
        ArrayList<Message> messages = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM messages " +
                    "WHERE message_text LIKE '%" + text + "%' " +
                    "AND sender_id=? OR user_id=? " +
                    "ORDER BY sent_date DESC "
            );
            preparedStatement.setLong(1, user_id);
            preparedStatement.setLong(2, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                messages.add( new Message(
                        resultSet.getLong("id"),
                        getUser(resultSet.getLong("user_id")),
                        getUser(resultSet.getLong("sender_id")),
                        resultSet.getString("message_text"),
                        resultSet.getBoolean("read_by_receive"),
                        resultSet.getTimestamp("sent_date")
                ));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }
}
