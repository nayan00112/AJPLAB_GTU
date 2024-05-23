import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.cj.protocol.Resultset;

class myjava {
    public static void main(String args[]) throws Exception {
        System.out.println("Hello World!");

        String url = "jdbc:mysql://localhost:3306/demojava";
        String uname = "root";
        String password = "root";

        String query = "select * from student";

        // Class.forName("com.mysql.jdbc.Driver");
        Class.forName("com.mysql.cj.jdbc.Driver.");

        Connection con = DriverManager.getConnection(url, uname, password);

        Statement st = (Statement) con.createStatement();

        Resultset rs = st.executeQuery(query);

        while (rs.next())
            System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
        con.close();
    }
    // String name = rs.getString("name");

}
