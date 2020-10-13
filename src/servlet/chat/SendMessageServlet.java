package servlet.chat;

import db.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/send-message")
public class SendMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AuthToken.checkSession(request, response);
            request.setCharacterEncoding("UTF-8");
            User user = (User)request.getSession().getAttribute("uid");
            if (user != null) {
                Long userId = Long.parseLong(request.getParameter("user"));
                Long senderId = Long.parseLong(request.getParameter("sender"));
                String message = request.getParameter("message");
                if (!message.equals("")) {
                    Message m = new Message(
                            null,
                            DBManager.getUser(userId),
                            DBManager.getUser(senderId),
                            message,
                            false,
                            null
                    );
                    if (DBManager.addMessage(m)) {
                        Chat chat = DBManager.getChatBetween(userId, senderId);
                        response.sendRedirect("/chat?id=" + chat.getId());
                    }
                } else response.sendRedirect(response.getHeader("referer"));
            } else response.sendRedirect("/login");
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/404.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/");
    }
}
