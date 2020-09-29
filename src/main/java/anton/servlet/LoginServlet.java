package anton.servlet;

import anton.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try
        {
            User user = new User();
            user.setName(req.getParameter("name"));
            Cookie cookie = new Cookie("name", user.getName());
            resp.addCookie(cookie);
            if (user.isValid()&&user.isChecked(req.getParameter("check")))
            {
                HttpSession session = req.getSession(true);
                session.setAttribute("name", user.getName());
                session.setAttribute("check","true");
                getServletContext().getRequestDispatcher("/temp-serv").forward(req, resp);
            }
            else resp.sendRedirect("/error");
        }
        catch (Throwable e)
        {
            System.out.println(e);
        }
    }
}

