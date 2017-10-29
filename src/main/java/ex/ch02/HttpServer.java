package ex.ch02;

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
            Socket client = null;
            InputStream input = null;
            OutputStream output = null;
            try {
                client = server.accept();
                System.err.println("Receive a connection: " + client);
                input = client.getInputStream();
                output = client.getOutputStream();
                System.err.println("Process Request");
                Request request = new Request(input);
                request.parse();
                if (request.getUri() == null){
                    throw new Exception("Invalid Connection");
                }

                System.err.println("Process Response");
                Response response = new Response(output);
                response.setRequest(request);

                System.err.println("proccess request: " + request.getUri());
                if (request.getUri().startsWith("/servlet/")){
                    new ServletProcessor().process(request, response);
                }
                else{
                    new StaticResourceProcessor().process(request, response);
                }

                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("IOException:" + e.getMessage());
                System.exit(1);
            } catch (Exception e) {
                System.err.println("Exception:" + e.getMessage());
                e.printStackTrace();
            }catch(Throwable t){
                System.err.println("throwable ....");
            }

            finally {
                if (client != null){
                    try {
                        System.err.println("Close connection: " + client);
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
