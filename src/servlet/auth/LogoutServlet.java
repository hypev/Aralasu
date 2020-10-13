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

@WebServlet(value = "/logout")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String selector = AuthToken.getCookie(request, "auth_token_s");
            if (!selector.equals("")) {
                DBManager.deleteAuthToken(selector);
                AuthToken.deleteCookie(response);
            }
            User user = (User)request.getSession().getAttribute("uid");
            if (user != null) {
                request.getSession().removeAttribute("uid");
            }
            response.sendRedirect("/login");
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/404.jsp");
        }
    }
}
