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
    public static boolean deleteAuthToken(String selector) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "DELETE FROM auth_tokens WHERE selector=?");
            preparedStatement.setString(1, selector);
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
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
}
