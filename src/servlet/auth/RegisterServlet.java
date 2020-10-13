package servlet.auth;

import db.AuthToken;
import db.DBManager;
import db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "/";
        try {
            request.setCharacterEncoding("UTF-8");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String rePassword = request.getParameter("rePassword");
            String fullName = request.getParameter("fullName");
            Date birthdate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthdate"));
            boolean remember = "true".equals(request.getParameter("remember"));

            if (password.equals(rePassword)) {
                if (DBManager.getUser(email) == null) {
                    if (DBManager.addUser(new User(null, email, password, fullName, birthdate, ""))) {
                        User user = DBManager.getUser(email);
                        request.getSession().setAttribute("uid", user);
                        if (remember)
                            AuthToken.generateToken(request, response, user.getId());
                    } else redirect = "/register?error";
                } else redirect = "/register?user-exist";
            } else redirect = "/register?password-not-match";
        } catch (Exception e) {
            e.printStackTrace();
            redirect = "/register?error";
        }
        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            User user = (User)request.getSession().getAttribute("uid");
            if (user == null) {
                if (request.getParameter("user-exist") != null)
                    request.setAttribute("alertErrorMsg", "User with such email is exists!");
                if (request.getParameter("password-not-match") != null)
                    request.setAttribute("alertErrorMsg", "Passwords is not same!");
                if (request.getParameter("error") != null)
                    request.setAttribute("alertErrorMsg", "Something went wrong. Try it again!");
                request.setAttribute("theme", AuthToken.getCookie(request, "theme"));
                request.getRequestDispatcher("/user/register.jsp").forward(request, response);
            } else response.sendRedirect("/");
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/404.jsp");
        }
    }
}
