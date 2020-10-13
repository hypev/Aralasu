package servlet.auth;

import db.AuthToken;
import db.DBManager;
import db.User;
import db.VKToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "/";
        try {
            request.setCharacterEncoding("UTF-8");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            boolean remember = "checked".equals(request.getParameter("remember"));
            if (password.equals("")) redirect ="/login?error";
            User u = DBManager.getUser(email);
            if (u != null) {
                if (u.getPassword().equals(password)) {
                    request.getSession().setAttribute("uid", u);
                    if (remember)
                        AuthToken.generateToken(request, response, u.getId());
                } else redirect = "/login?password-incorrect";
            } else redirect = "/login?no-user";
        } catch (Exception e) {
            e.printStackTrace();
            redirect = "/login?error";
        }
        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User)request.getSession().getAttribute("uid");
            if (user == null) {
                if (request.getParameter("password-incorrect") != null)
                    request.setAttribute("alertErrorMsg", "Password is not correct!");
                if (request.getParameter("no-user") != null)
                    request.setAttribute("alertErrorMsg", "Email is not correct!");
                if (request.getParameter("error") != null)
                    request.setAttribute("alertErrorMsg", "Something went wrong. Try it again!");
                if (request.getParameter("vk-error") != null)
                    request.setAttribute("alertErrorMsg", "VK Authorization is failed!");
                request.setAttribute("theme", AuthToken.getCookie(request, "theme"));
                request.setAttribute("vkoauth", VKToken.oauthLink());
                request.getRequestDispatcher("/user/login.jsp").forward(request, response);
            } else response.sendRedirect("/");
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }
}
