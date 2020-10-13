package servlet.chat;

import db.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/search-message")
public class SearchMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AuthToken.checkSession(request, response);
            request.setCharacterEncoding("UTF-8");
            User user = (User)request.getSession().getAttribute("uid");
            if (user != null) {
                String text = request.getParameter("message");
                ArrayList<Message> messages = DBManager.getMessagesByText(text, user.getId());
                request.setAttribute("user", user);
                request.setAttribute("user-info", user);
                request.setAttribute("messages", messages);
                request.setAttribute("usersBirthday", DBManager.getLastUsersByBirthDateOrder(5));
                request.setAttribute("theme", AuthToken.getCookie(request, "theme"));
                request.getRequestDispatcher("/chats/chats.jsp").forward(request, response);
            } else response.sendRedirect("/login");
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/404.jsp");
        }
    }
}
