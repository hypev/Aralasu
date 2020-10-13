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
import java.util.ArrayList;

@WebServlet(value = "/my-friends")
public class MyFriendsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AuthToken.checkSession(request, response);
            request.setCharacterEncoding("UTF-8");
            User user = (User)request.getSession().getAttribute("uid");
            if (user != null) {
                ArrayList<User> friends = DBManager.getAllFriendRequestByUserId(user.getId());
                friends.addAll(DBManager.getAllFriendsByUserId(user.getId()));
                request.setAttribute("user", user);
                request.setAttribute("user-info", user);
                request.setAttribute("usersBirthday", DBManager.getLastUsersByBirthDateOrder(5));
                request.setAttribute("friends", friends);
                request.setAttribute("theme", AuthToken.getCookie(request, "theme"));
                request.getRequestDispatcher("/user/friends.jsp").forward(request, response);
            } else response.sendRedirect("/login");
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/404.jsp");
        }
    }
}
