package servlet.user;

import db.AuthToken;
import db.DBManager;
import db.Post;
import db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(value = "/post-edit")
public class EditPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "/";
        try {
            request.setCharacterEncoding("UTF-8");
            Long id = Long.parseLong(request.getParameter("id"));
            User author = DBManager.getPost(id).getUser();
            String title = request.getParameter("title");
            String shortContent = request.getParameter("shortContent");
            String content = request.getParameter("content");

            User user = (User)request.getSession().getAttribute("uid");
            if (user != null && author.getId() == user.getId()) {
                if (DBManager.editPost(new Post(id, user, title, shortContent, content, new Timestamp(System.currentTimeMillis())))) {
                    redirect = "/?success";
                }
            } else redirect = "/?error";
        } catch (Exception e) {
            e.printStackTrace();
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
