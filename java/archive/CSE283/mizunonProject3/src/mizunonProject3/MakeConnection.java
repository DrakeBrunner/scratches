package mizunonProject3;
import java.net.Socket;

public class MakeConnection {
    public static void main(String[] args) throws Exception {
        new ChatThread("MakeConnection", new Socket("127.0.0.1", 31200));
    }
}