package servlet;

import db.AuthToken;
import db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/theme")
public class ThemeChangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String theme = AuthToken.getCookie(request, "theme");
            if (theme == null || theme.equals("dark"))
                theme = "light";
            else
                theme = "dark";
            Cookie themeCookie = new Cookie("theme", theme);
            themeCookie.setMaxAge(3600*24*365);
            response.addCookie(themeCookie);
            response.sendRedirect(request.getHeader("referer"));
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AuthToken.checkSession(request, response);
            User user = (User)request.getSession().getAttribute("uid");
            if (user != null) {
                response.sendRedirect("/");
            } else response.sendRedirect("/login");
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/404.jsp");
        }
    }
}
