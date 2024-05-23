
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class p1 {
    public static void main(String arg[]) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/movies";
        String username = "root";
        String password = "root";

        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            Statement statement = con.createStatement();
            while (4 > 1) {
                System.out.println(
                        "Select Choice: \n1. Add movie, \nDisplay movie name list\n3.Modify movie\n4.Delete movie.\n");
                Scanner inp = new Scanner(System.in);
                int input = Integer.parseInt(inp.nextLine());

                switch (input) {
                    case 1:
                        System.out.print("Enter movie name, language, director: ");
                        String data = inp.nextLine();
                        String d[] = data.split(" ");

                        String q = "INSERT INTO `movies`.`movie_list` (`NAME`, `LANGUAGE`, `DIRECTOR`) VALUES ('" + d[0]
                                + "', '" + d[1] + "', '" + d[2] + "');";
                        System.out.print(q);
                        boolean resultSet = statement.execute(q);
                        if (resultSet) {
                            System.out.print("Movie cannot inserted.\n");
                        } else {
                            System.out.print("Movie inserted.\n");
                        }
                        break;
                    case 2:
                        String q1 = "SELECT * FROM movie_list;";
                        ResultSet resultSet1 = statement.executeQuery(q1);

                        if (resultSet1.next()) {
                            System.out.print("ID:" + resultSet1.getString("ID") + ", Name: "
                                    + resultSet1.getString("NAME") + ", Language: " + resultSet1.getString("LANGUAGE")
                                    + ", Director: " + resultSet1.getString("DIRECTOR") + "\n");
                            while (resultSet1.next()) {
                                System.out.print(
                                        "ID:" + resultSet1.getString("ID") + ", Name: " + resultSet1.getString("NAME")
                                                + ", Language: " + resultSet1.getString("LANGUAGE") + ", Director: "
                                                + resultSet1.getString("DIRECTOR") + "\n");
                            }
                        } else {
                            System.out.print("EMPTY !\n");
                        }
                        break;
                    case 3:
                        System.out.print("Insert new data [ID, name, language, director:]");
                        String data1 = inp.nextLine();
                        String[] d1 = data1.split(" ");

                        String q2 = "UPDATE movie_list SET NAME = '" + d1[1] + "' , LANGUAGE = '" + d1[2]
                                + "', DIRECTOR = '" + d1[3] + "' WHERE ID = '" + Integer.parseInt(d1[0]) + "';";
                        int t = statement.executeUpdate(q2);
                        if (t != 0) {
                            System.out.print("\nupdated successfully \n");
                        } else {
                            System.out.print("\nupdated unsuccessful \n");
                        }
                        break;
                    case 4:
                        System.out.print("\nInter movie id for deleting purpose:\n");
                        String id = inp.nextLine();
                        String q3 = "DELETE FROM movie_list WHERE ID = '" + id + "';";
                        int t1 = statement.executeUpdate(q3);
                        if (t1 != 0) {
                            System.out.print("\nDelete Successfully.\n");
                        } else {
                            System.out.print("\nDelete Unuccessfully.\n");
                        }
                        break;
                    default:
                        System.out.print("\nPlz enter valid input.\n");
                }
            }
        } catch (Exception e) {
            System.out.print(e);
        } finally {
            con.close();
        }

    }
}