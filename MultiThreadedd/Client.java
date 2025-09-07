public class Client {

    public Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    int port = 8010;
                    java.net.InetAddress address = java.net.InetAddress.getByName("localhost");
                    java.net.Socket socket = new java.net.Socket(address, port);
                    java.io.PrintWriter toSocket = new java.io.PrintWriter(socket.getOutputStream(), true);
                    java.io.BufferedReader fromSocket = new java.io.BufferedReader(
                            new java.io.InputStreamReader(socket.getInputStream()));
                    toSocket.println("Hello from the client");
                    String line = fromSocket.readLine();
                    System.out.println("Response from the socket is : " + line);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public static void main(String[] args) {
        Client client = new Client();

        for (int i = 0; i < 100; i++) {
            try {
                Thread thread = new Thread(client.getRunnable());
                thread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
