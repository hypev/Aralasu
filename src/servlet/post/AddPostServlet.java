package servlet.post;

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

@WebServlet(value = "/addPost")
public class AddPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "/";
        try {
            request.setCharacterEncoding("UTF-8");
            User user = (User)request.getSession().getAttribute("uid");
            if (user != null) {
                String title = request.getParameter("title");
                String shortContent = request.getParameter("shortContent");
                String content = request.getParameter("content");
                if (DBManager.addPost(new Post(null, user, title, shortContent, content, new Timestamp(System.currentTimeMillis())))) {
                    redirect = "/?success";
                }
            } else redirect = "/login";
        } catch (Exception e) {
            e.printStackTrace();
            redirect = "/login";
        }
        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/");
    }
}
