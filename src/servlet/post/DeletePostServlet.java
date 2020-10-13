package servlet.post;

import db.AuthToken;
import db.DBManager;
import db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/post-delete")
public class DeletePostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AuthToken.checkSession(request, response);
            User user = (User)request.getSession().getAttribute("uid");
            if (user != null) {
                Long id = Long.parseLong(request.getParameter("id"));
                if (DBManager.getPost(id).getUser().getId().equals(user.getId())) {
                    if (DBManager.deletePost(id)) {
                        response.sendRedirect("/?success");
                    }
                }
            } else response.sendRedirect("/login");
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/");
    }
}
