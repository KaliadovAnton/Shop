package anton.servlet;

import anton.services.DBService;
import anton.services.ParseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(urlPatterns = ("/temp-serv"))
public class JspServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("flush")!=null){
            try {
                DBService.flushOrders();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            req.setAttribute("goods", DBService.showMapOfGoods());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(req.getParameter("select")!=null) {
            try {//добавил в таблицу good_order пришедшее из формы first-jsp.jsp
                int goodId = DBService.getGoodId(ParseService.parseGood(req.getParameter("select")));
                int orderId = DBService.getNumberOfOrder();
                DBService.addGoodOrder(goodId, orderId);
                req.setAttribute("preorders", DBService.getListOfPreorderedGoods(orderId));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        getServletContext().getRequestDispatcher("/first-jsp.jsp").forward(req, resp);
    }
}
