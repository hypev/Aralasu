package servlet.chat;

import db.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/chat")
public class ChatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AuthToken.checkSession(request, response);
            request.setCharacterEncoding("UTF-8");
            User user = (User)request.getSession().getAttribute("uid");
            if (user != null) {
                Long id = Long.parseLong(request.getParameter("id"));
                Chat chat = DBManager.getChat(id);
                if (chat.getUser().getId().equals(user.getId()) || chat.getOpponent().getId().equals(user.getId())) {
                    request.setAttribute("user", user);
                    request.setAttribute("user-info", user);
                    request.setAttribute("chat", chat);
                    request.setAttribute("usersBirthday", DBManager.getLastUsersByBirthDateOrder(5));
                    request.setAttribute("theme", AuthToken.getCookie(request, "theme"));
                    request.getRequestDispatcher("/chats/chat.jsp").forward(request, response);
                    if (chat.getUnreadMsgCount(user) != 0) {
                        for (int i = chat.getMessages().size() - 1; i >= chat.getMessages().size() - chat.getUnreadMsgCount(user); i--) {
                            if (!chat.getMessages().get(i).getSender().getId().equals(user.getId())) {
                                DBManager.editMessage(chat.getMessages().get(i));
                            }
                        }
                    }
                } else response.sendRedirect("/chats");
            } else response.sendRedirect("/login");
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/404.jsp");
        }
    }
}
