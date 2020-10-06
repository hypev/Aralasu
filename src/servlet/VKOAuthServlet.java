package servlet;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(value = "/vk-oauth")
public class VKOAuthServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "/";
        try {
            request.setCharacterEncoding("UTF-8");
            String code = request.getParameter("code");

            if (code != null) {
                User u = VKToken.getUser(code);
                if (u != null) {
                    User dbUser = DBManager.getUser(u.getEmail());
                    if (dbUser != null) {
                        u = dbUser;
                    } else {
                        if (DBManager.addUser(u)) {
                            u = DBManager.getUser(u.getEmail());
                            AuthToken.generateToken(request, response, u.getId());
                        } else throw new Exception("VK OAuth Error");
                    }
                    request.getSession().setAttribute("uid", u);
                    AuthToken.generateToken(request, response, u.getId());
                } else throw new Exception("VK OAuth Error");
            } else throw new Exception("VK OAuth Error");

        } catch (Exception e) {
            e.printStackTrace();
            redirect = "/login?vk-error";
        }
        response.sendRedirect(redirect);
    }
}
