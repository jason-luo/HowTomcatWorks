package ex.ch01;

import java.io.*;


public class Response {
    private OutputStream output;
    private Request request;

    public Response(OutputStream output){
        this.output = output;
    }

    public void setRequest(Request request){
        this.request = request;
    }

    public void sendStaticResource() {

        File file = new File(HttpServer.WEB_ROOT, request.getUri());
        if (file.exists() && file.isFile()){
            byte[] buffer = new byte[2048];
            String header = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: " + file.length()  + "\r\n" +
                    "\r\n";

            try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file))) {
                output.write(header.getBytes());
                int readLen;
                while((readLen = reader.read(buffer)) != -1) {
                    output.write(buffer, 0, readLen);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";
            try {
                output.write(errorMessage.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
