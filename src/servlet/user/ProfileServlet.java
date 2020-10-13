package servlet.user;

import db.AuthToken;
import db.DBManager;
import db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/profile")
public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AuthToken.checkSession(request, response);
            User user = (User)request.getSession().getAttribute("uid");
            if (user != null) {
                Long id = Long.parseLong(request.getParameter("id"));
                User profileUser = DBManager.getUser(id);
                request.setAttribute("user", user);
                request.setAttribute("user-info", profileUser);
                request.setAttribute("usersBirthday", DBManager.getLastUsersByBirthDateOrder(5));
                request.setAttribute("theme", AuthToken.getCookie(request, "theme"));
                request.setAttribute("posts", DBManager.getAllPostsByUserId(id));
                request.getRequestDispatcher("/post/feed.jsp").forward(request, response);
            } else response.sendRedirect("/login");
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }
}
