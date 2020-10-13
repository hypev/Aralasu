package servlet.friend;

import db.AuthToken;
import db.DBManager;
import db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/friend-request")
public class FriendRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AuthToken.checkSession(request, response);
            request.setCharacterEncoding("UTF-8");
            User user = (User)request.getSession().getAttribute("uid");
            if (user != null) {
                Long requesterId = Long.parseLong(request.getParameter("requesterId"));
                boolean reject = request.getParameter("Reject") != null;
                boolean confirm = request.getParameter("Confirm") != null;
                if (reject) {
                    if (DBManager.deleteFriendRequest(user, requesterId)) {
                        response.sendRedirect(request.getHeader("referer"));
                    }
                } else if (confirm) {
                    if (DBManager.acceptFriend(user, requesterId)) {
                        if (DBManager.deleteFriendRequest(user, requesterId)) {
                            response.sendRedirect(request.getHeader("referer"));
                        }
                    }
                } else {
                    response.sendRedirect(response.getHeader("referer"));
                }
            } else response.sendRedirect("/login");
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/404.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/");
    }
}
