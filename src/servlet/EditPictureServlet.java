package servlet;

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

@WebServlet(value = "/editPicture")
public class EditPictureServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "/settings?success-picture";
        try {
            AuthToken.checkSession(request, response);
            request.setCharacterEncoding("UTF-8");
            String pictureUrl = request.getParameter("pictureUrl");
            User user = (User)request.getSession().getAttribute("uid");
            user.setPictureUrl(pictureUrl);
            if (DBManager.editUser(user)) {
                request.getSession().setAttribute("uid", user);
            } else throw new Exception("Update Picture Error");
        } catch (Exception e) {
            e.printStackTrace();
            redirect = "/settings?error-picture";
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
