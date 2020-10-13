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

@WebServlet(value = "/editPassword")
public class EditPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "/settings?success-password";
        try {
            AuthToken.checkSession(request, response);
            request.setCharacterEncoding("UTF-8");
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            String reNewPassword = request.getParameter("reNewPassword");
            User user = (User)request.getSession().getAttribute("uid");
            if (oldPassword.equals(user.getPassword()) || user.getPassword().equals("")) {
                if (newPassword.equals(reNewPassword)) {
                    user.setPassword(newPassword);
                    if (DBManager.editUser(user)) {
                        request.getSession().setAttribute("uid", user);
                    } else throw new Exception("Update Password Error");
                } else redirect = "/settings?incorrect-re";
            } else redirect = "/settings?incorrect-old";
        } catch (Exception e) {
            e.printStackTrace();
            redirect = "/settings?error-password";
        }
        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AuthToken.checkSession(request, response);
            User user = (User)request.getSession().getAttribute("uid");
            if (user != null) {
                response.sendRedirect("/settings");
            } else response.sendRedirect("/login");
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/404.jsp");
        }
    }
}
