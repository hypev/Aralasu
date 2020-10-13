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

@WebServlet(value = "/my-profile")
public class MyProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AuthToken.checkSession(request, response);
            User user = (User)request.getSession().getAttribute("uid");
            if (user != null) {
                request.setAttribute("user", user);
                request.setAttribute("user-info", user);
                request.setAttribute("usersBirthday", DBManager.getLastUsersByBirthDateOrder(5));
                request.setAttribute("theme", AuthToken.getCookie(request, "theme"));
                request.setAttribute("posts", DBManager.getAllPostsByUserId(user.getId()));
                if (request.getParameter("error") != null)
                    request.setAttribute("alertErrorMsg", "Something went wrong while adding post!");
                if (request.getParameter("success") != null)
                    request.setAttribute("alertSuccessMsg", "Added Successfully!");
                request.getRequestDispatcher("/post/feed.jsp").forward(request, response);
            } else response.sendRedirect("/login");
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }
}
