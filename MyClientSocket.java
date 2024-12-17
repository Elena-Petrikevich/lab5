import java.io.*;
import java.net.*;

public class MyClientSocket {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 8030);
            BufferedReader dis = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line;
            while ((line = dis.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("ошибка : " + e);
        }
    }
}