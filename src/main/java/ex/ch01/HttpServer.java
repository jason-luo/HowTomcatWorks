package ex.ch01;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
    private boolean shutdown = false;

    public static void main(String[] args){
        HttpServer server = new HttpServer();
        System.out.println("user.dir: " + System.getProperty("user.dir"));
        server.await();
    }

    public void await(){
        ServerSocket server = null;
        int port = 8080;
        try {
            server = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while(!shutdown){
            try {
                Socket client = server.accept();
                InputStream input = client.getInputStream();
                OutputStream output = client.getOutputStream();

                Request request = new Request(input);
                request.parse();

                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                client.close();

                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
