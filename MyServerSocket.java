import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.nio.file.*;

public class MyServerSocket {

     static List<String> sonnets = new ArrayList<>();
     static final String FILE = "sonnets.txt";

    public static void main(String[] args)  {
        load();

        Socket s = null;
        try {
            ServerSocket server = new ServerSocket(8030);
            s = server.accept();
            PrintStream ps = new PrintStream(s.getOutputStream());

            String randomSonnet = getRandom();
            ps.println(randomSonnet);
            ps.flush();
            s.close();
        } catch (IOException e) {
            System.out.println("ошибка : " + e);
        }
    }


     static void load() {
        try {
            Path path = Paths.get(FILE);
            List<String> lines = Files.readAllLines(path);
            StringBuilder sonnet = new StringBuilder();
            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    if (sonnet.length() > 0) {
                        sonnets.add(sonnet.toString().trim());
                        sonnet.setLength(0);
                    }
                } else {
                    sonnet.append(line).append("\n");
                }
            }

        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла с сонетами: " + e);

        }
    }

    private static String getRandom() {
        if (sonnets.isEmpty()) {
            return "Не получилось загрузить сонеты";
        }
        Random random = new Random();
        int randomIndex = random.nextInt(sonnets.size());
        return sonnets.get(randomIndex);
    }
}