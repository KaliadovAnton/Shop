package anton.services;

import anton.ConnectionHelper;
import anton.Good;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBService {
    public static boolean isOld(String login) throws SQLException {
        String sql = "select login from user where login='" +login+"';";
        ResultSet set = ConnectionHelper.createStatement().executeQuery(sql);
        return set.next();
    }

    public static void addNew(String login) throws SQLException {
        String sql = "insert into user (login, password) values ('"+login+"');";
        ConnectionHelper.prepareStatement(sql).execute();
    }

    public static void addOrder(int userId, int totalPrice) throws SQLException {
        PreparedStatement preparedStatement = ConnectionHelper.prepareStatement("insert into `order` (user_id, total_price) values (?, ?);");
        preparedStatement.setInt(1,userId);
        preparedStatement.setInt(2,totalPrice);
        preparedStatement.execute();
    }

    public static void addGoodOrder(int goodId, int orderId) throws SQLException {
        PreparedStatement preparedStatement = ConnectionHelper.prepareStatement("insert into order_good (order_id, good_id) values (?, ?);");
        preparedStatement.setInt(1, orderId);
        preparedStatement.setInt(2, goodId);
        preparedStatement.execute();
    }

    public static int getTotalPrice(int orderId) throws SQLException {
        int sum = 0;
        PreparedStatement statement = ConnectionHelper.prepareStatement("select good.price as price from good inner join order_good on good.id=good_id where order_good.order_id=?;");
        statement.setInt(1, orderId);
        ResultSet priceSet = statement.executeQuery();
        while(priceSet.next()){
            sum+=priceSet.getInt("price");
        }
        return sum;
    }
    public static int getNumberOfOrder() throws SQLException {
        int orderId = 0;
        ResultSet set = ConnectionHelper.createStatement().executeQuery("select count(*) as count from `order`;");
        while (set.next()){
            orderId = set.getInt("count");
        }
        return orderId;
    }

    public static ArrayList<Good> showMapOfGoods() throws SQLException {
        ResultSet set = ConnectionHelper.createStatement().executeQuery("select title, price from good");
        ArrayList<Good> goods = new ArrayList<>();
        while (set.next()){
            goods.add(new Good(set.getString("title"),set.getInt("price")));
        }
        return goods;
    }

    public static int getGoodId(String title) throws SQLException {
        int goodId = 0;
        ResultSet set = ConnectionHelper.prepareStatement("select id from good where title='"+title+"';").executeQuery();
        while(set.next()){
            goodId = set.getInt("id");
        }
        return goodId;
    }

    public static ArrayList<String> getListOfPreorderedGoods(int orderId) throws SQLException {
        ArrayList<String> listOfPreorderedGoods = new ArrayList<>();
        ResultSet set = ConnectionHelper.prepareStatement("select good.title, good.price from good inner join order_good on good.id=order_good.good_id where order_good.order_id='"+ orderId +"'").executeQuery();
        while(set.next()){
            listOfPreorderedGoods.add(set.getString("title") +" "+ set.getInt("price"));
        }
        return listOfPreorderedGoods;
    }

    public static int getUserId(String name) throws SQLException {
        int userId = 0;
        ResultSet set = ConnectionHelper.prepareStatement("select id as id from user where login='"+ name +"';").executeQuery();
        while (set.next()){
            userId = set.getInt("id");
        }
        return userId;
    }

    public static String getUserName(int id) throws SQLException {
        String name = "";
        ResultSet set = ConnectionHelper.prepareStatement("select login from user where id='"+ id +"';").executeQuery();
        while (set.next()){
            name = set.getString("login");
        }
        return name;
    }

    public static void flushOrders() throws SQLException {
        ConnectionHelper.createStatement().execute("delete from order_good;");
    }
}
