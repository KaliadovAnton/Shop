package anton.listener;

import anton.ConnectionHelper;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;
import java.sql.Statement;

public class ConListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Statement statement = ConnectionHelper.createStatement();
            statement.execute("create table order_good (id int primary key auto_increment, order_id int, good_id int);");
            statement.execute("create table user (id int primary key auto_increment, login varchar(50), password varchar(50));" +
                    "insert into user values ('1', 'test', 'test');");
            statement.execute("create table good (id int primary key auto_increment, title varchar(50), price int);" +
                    "insert into good (title, price) values ('book', '10');" +
                    "insert into good (title, price) values ('newspaper', '1');" +
                    "insert into good (title, price) values ('postcard', '5');" +
                    "insert into good (title, price) values ('brochure', '2');");
            statement.execute("create table `order` (id int primary key auto_increment, user_id int, total_price int);");
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            Statement statement = ConnectionHelper.createStatement();
            statement.execute("drop table user;");
            statement.execute("drop table good;");
            statement.execute("drop table order_good;");
            statement.execute("drop table `order`;");
            ConnectionHelper.getConnection().close();
           // ConnectionHelper.closePreparedStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
