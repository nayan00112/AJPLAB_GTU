import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class p2_server {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/movies";
        String username = "root";
        String password = "root";

        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            Statement statement = con.createStatement();

            ServerSocket sc = new ServerSocket(54321);
            System.out.println("Waiting for client ...");
            Socket s = sc.accept();

            BufferedReader bf = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String message = bf.readLine();
            String sql = "SELECT * FROM movie_list where NAME = '" + message + "';";
            ResultSet resultSet1 = statement.executeQuery(sql);

            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            while (resultSet1.next()) {
                String Data = "Your Movie details is : Name : " + resultSet1.getString("NAME") + ", Language : "
                        + resultSet1.getString("LANGUAGE") + ", DIRECTOR : " + resultSet1.getString("DIRECTOR") + ".";
                out.println(Data);
            }

            out.close();
            bf.close();
            sc.close();
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
    }
}