package anton.filters;

import anton.services.DBService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        System.out.println("Nu yo mayo");

        try {
            if(DBService.isOld(servletRequest.getParameter("name"))||session.getAttribute("name")!=null){
                filterChain.doFilter(servletRequest, servletResponse);
            }else {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                System.out.println("hello from filter");
                response.sendRedirect("/my-app/error.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
