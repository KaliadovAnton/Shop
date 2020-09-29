package anton;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String name;
    private boolean check;

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public boolean isValid() throws SQLException {
        System.out.println(this.name);
        ResultSet set = ConnectionHelper.createStatement().executeQuery("select login from user where login='"+ this.name +"';");
        return set.next();
    }

    public boolean isChecked(String check) {
        return check != null;
    }
    public void checked(){
        this.check = true;
    }
}
