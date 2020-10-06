package db;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VKToken {
    private static Long VK_ID = 7619096l;
    private static String VK_SECRET = "fGFXt8ZQVGz76dCDy7lY";
    private static String VK_URL = "http://localhost:1337/vk-oauth";
    private static ObjectMapper mapper = new ObjectMapper();

    private String access_token;
    private Long expires_in;
    private Long user_id;
    private String email;

    public VKToken() { };
    public VKToken(String access_toke, Long expires_in, Long user_id, String email) {
        this.access_token = access_toke;
        this.expires_in = expires_in;
        this.user_id = user_id;
        this.email = email;
    }
    public String getAccess_token() { return access_token; }
    public void setAccess_toke(String access_toke) { this.access_token = access_token; }
    public Long getExpires_in() { return expires_in; }
    public void setExpires_in(Long expires_in) { this.expires_in = expires_in; }
    public Long getUser_id() { return user_id; }
    public void setUser_id(Long user_id) { this.user_id = user_id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public static String oauthLink() {
        return  "https://oauth.vk.com/authorize?" +
                "client_id=" + VKToken.VK_ID +
                "&display=page&" +
                "redirect_uri=" + VKToken.VK_URL +
                "&scope=email" +
                "&response_type=code&v=5.52";
    }
    public static String fileToString(String path) {
        URL u;
        StringBuilder builder = new StringBuilder();
        try {
            u = new URL(path);
            try {
                BufferedReader theHTML = new BufferedReader(new InputStreamReader(u.openStream(), "UTF-8"));
                String thisLine;
                while ((thisLine = theHTML.readLine()) != null) {
                    builder.append(thisLine).append("\n");
                }
            }
            catch (Exception e) {
                System.err.println(e);
            }
        } catch (MalformedURLException e) {
            System.err.println(path + " is not a parseable URL");
            System.err.println(e);
        }
        return builder.toString();
    }
    public static VKToken getToken(String code) {
        try {
            String tokenJson = VKToken.fileToString("https://oauth.vk.com/access_token?" +
                    "client_id=" + VKToken.VK_ID +
                    "&redirect_uri=" + VKToken.VK_URL +
                    "&client_secret=" + VKToken.VK_SECRET +
                    "&code=" + code
            );
            return mapper.readValue(tokenJson, VKToken.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static User getUser(String code) {
        User user = null;
        try {
            VKToken token = getToken(code);
            String dataJson = VKToken.fileToString("https://api.vk.com/method/users.get?" +
                    "user_id=" + token.getUser_id() +
                    "&access_token=" + token.getAccess_token() +
                    "&fields=id,email,first_name,last_name,bdate,photo_big" +
                    "&v=5.52");
            JsonNode root = mapper.readTree(dataJson);
            String id = token.getUser_id().toString();
            String email = token.getEmail();
            String fullName = root.at("/response/0/first_name").asText() + " " + root.at("/response/0/last_name").asText();
            String pictureUrl = root.at("/response/0/photo_big").asText();
            String bdate = VKToken.correctDate(root.at("/response/0/bdate").asText());
            Date birthdate = new SimpleDateFormat("dd.MM.yyyy").parse(bdate);
            user = new User(null, email, "", fullName, birthdate, pictureUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public static String correctDate(String date) {
        if (date.charAt(1) == '.') date = "0" + date;
        if (date.length() == 9 && date.charAt(4) == '.') date = date.substring(0, 3) + "0" + date.substring(3);
        if (date.length() == 4) date = date.substring(0, 3) + "0" + date.charAt(3);
        if (date.length() == 5) date += ".2000";
        return date;
    }
}
