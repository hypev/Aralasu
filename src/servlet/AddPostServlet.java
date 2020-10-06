package servlet;

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
            String title = request.getParameter("title");
            String shortContent = request.getParameter("shortContent");
            String content = request.getParameter("content");

            User user = (User)request.getSession().getAttribute("uid");
            if (user != null) {
                if (DBManager.addPost(new Post(null, user, title, shortContent, content, new Timestamp(System.currentTimeMillis())))) {
                    redirect = "/?success";
                }
            } else redirect = "/?error";
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
