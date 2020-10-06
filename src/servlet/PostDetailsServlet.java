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

@WebServlet(value = "/post")
public class PostDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AuthToken.checkSession(request, response);
            Long id = Long.parseLong(request.getParameter("id"));
            User user = (User)request.getSession().getAttribute("uid");
            if (user != null) {
                request.setAttribute("user", user);
                request.setAttribute("usersBirthday", DBManager.getLastUsersByBirthDateOrder(5));
                request.setAttribute("theme", AuthToken.getCookie(request, "theme"));
                request.setAttribute("post", DBManager.getPost(id));
                if (request.getParameter("error") != null)
                    request.setAttribute("alertErrorMsg", "Something went wrong while adding post!");
                if (request.getParameter("success") != null)
                    request.setAttribute("alertSuccessMsg", "Added Successfully");
                request.getRequestDispatcher("/postDetails.jsp").forward(request, response);
            } else response.sendRedirect("/login");
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/404.jsp");
        }
    }
}
