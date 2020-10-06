package db;

import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthToken {
    private Long id;
    private String selector;
    private String validator;
    private User user;

    public AuthToken() { }
    public AuthToken(Long id, String selector, String validator, User user) {
        this.id = id;
        this.selector = selector;
        this.validator = validator;
        this.user = user;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSelector() { return selector; }
    public void setSelector(String selector) { this.selector = selector; }
    public String getValidator() { return validator; }
    public void setValidator(String validator) { this.validator = validator; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    private static String generateSelector() {
        return RandomStringUtils.randomAlphanumeric(12);
    }
    private static String generateValidator() {
        return RandomStringUtils.randomAlphanumeric(64);
    }

    public static String getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        String cookie = "";
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(cookieName)) {
                    cookie = c.getValue();
                    break;
                }
            }
        }
        return cookie;
    }
    private static void setCookie(String selector, String validator, int age, HttpServletResponse response) {
        Cookie selectorCookie = new Cookie("auth_token_s", selector);
        selectorCookie.setMaxAge(age);
        response.addCookie(selectorCookie);

        Cookie validatorCookie = new Cookie("auth_token_v", validator);
        validatorCookie.setMaxAge(age);
        response.addCookie(validatorCookie);
    }
    public static void deleteCookie(HttpServletResponse response) { setCookie("", "", 0, response); }

    public static void generateToken(HttpServletRequest request, HttpServletResponse response, Long user_id) {
        String selector = generateSelector();
        String validator = generateValidator();
        AuthToken authToken = new AuthToken(null, selector, validator, DBManager.getUser(user_id));
        if (DBManager.addAuthToken(authToken)) {
            setCookie(selector, validator,3600 * 24 * 30, response);
        }
    }
    public static void checkSession(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getSession().getAttribute("uid") == null) {
                String selector = getCookie(request, "auth_token_s");
                String validator = getCookie(request, "auth_token_v");
                if (!selector.equals("") && !validator.equals("")) {
                    AuthToken authToken = DBManager.getAuthToken(selector);
                    if (authToken != null) {
                        if (validator.equals(authToken.getValidator())) {
                            request.getSession().setAttribute("uid", authToken.getUser());
                            String newSelector = generateSelector();
                            String newValidator = generateValidator();
                            authToken.setSelector(newSelector);
                            authToken.setValidator(newValidator);
                            if (DBManager.editAuthToken(authToken)) {
                                setCookie(newSelector, newValidator,3600 * 24 * 30, response);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
