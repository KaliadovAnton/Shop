package anton;

import anton.services.PropertyReader;

import java.io.IOException;
import java.sql.*;

public class ConnectionHelper {
    private static Connection connection;
    private static Statement statement;

    static {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(PropertyReader.getProperty("jdbc.url"), PropertyReader.getProperty("jdbc.user"), PropertyReader.getProperty("jdbc.password"));
            statement = connection.createStatement();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static Connection getConnection() {
        return connection;
    }

    public static Statement createStatement() throws SQLException {
        return statement;
    }

   public static PreparedStatement prepareStatement(String sql) throws SQLException {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
       return preparedStatement;
    }

    public static void closePreparedStatement() throws SQLException {
    }
}
