import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class p2_client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 54321);
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter movie name: ");
            String mn = sc.nextLine();

            out.println(mn);
            BufferedReader bf = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println("Movie details: \n" + bf.readLine());

            sc.close();
            bf.close();
            out.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}