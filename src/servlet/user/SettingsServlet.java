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

@WebServlet(value = "/settings")
public class SettingsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AuthToken.checkSession(request, response);
            request.setCharacterEncoding("UTF-8");
            User user = (User)request.getSession().getAttribute("uid");
            if (user != null) {
                request.setAttribute("user", user);
                request.setAttribute("user-info", user);
                request.setAttribute("usersBirthday", DBManager.getLastUsersByBirthDateOrder(5));
                if (request.getParameter("error-profile") != null)
                    request.setAttribute("alertErrorMsg", "Something went wrong while editing profile!");
                if (request.getParameter("success-profile") != null)
                    request.setAttribute("alertSuccessMsg", "Profile data changes saved!");
                if (request.getParameter("error-picture") != null)
                    request.setAttribute("alertErrorMsg", "Something went wrong while editing picture!");
                if (request.getParameter("success-picture") != null)
                    request.setAttribute("alertSuccessMsg", "Picture saved!");
                if (request.getParameter("error-password") != null)
                    request.setAttribute("alertErrorMsg", "Something went wrong while editing password!");
                if (request.getParameter("incorrect-re") != null)
                    request.setAttribute("alertErrorMsg", "New Password and Re-New Password not match!");
                if (request.getParameter("incorrect-old") != null)
                    request.setAttribute("alertErrorMsg", "Old password not match!");
                if (request.getParameter("success-password") != null)
                    request.setAttribute("alertSuccessMsg", "Password saved!");
                request.setAttribute("theme", AuthToken.getCookie(request, "theme"));
                request.getRequestDispatcher("/user/settings.jsp").forward(request, response);
            } else response.sendRedirect("/login");
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/404.jsp");
        }
    }
}
