import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 25233;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);

        while (true) {
            try (Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                String line;
                long number;
                while ((line = in.readLine()) != null) {
                    if ("end".equals(line)) break;
                    number = Long.parseLong(line);
                    if (number == 0) {
                        out.println(number + " член ряда Фибоначчи равен 0 (Нумерация начинается с 0)");
                    } else if (number == 1) {
                        out.println(number + " член ряда Фибоначчи равен 1 (Нумерация начинается с 0)");
                    } else {
                        long a = 0;
                        long b = 1;
                        for (long i = 2; i <= number; i++) {
                            long next = a + b;
                            a = b;
                            b = next;
                        }
                        out.println(number + " член ряда Фибоначчи равен " + b + " (Нумерация начинается с 0)");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
    }
}
