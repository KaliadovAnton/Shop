package anton.servlet;

import anton.services.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet()
public class MainServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("cart", DBService.getListOfPreorderedGoods(DBService.getNumberOfOrder()));
            req.setAttribute("total", DBService.getTotalPrice(DBService.getNumberOfOrder()));
            DBService.addOrder(DBService.getUserId(req.getParameter("name")),DBService.getTotalPrice(DBService.getNumberOfOrder()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/cart.jsp").forward(req, resp);
    }
}
