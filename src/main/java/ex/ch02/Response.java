package ex.ch02;

import ex.ch01.HttpServer;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;


public class Response implements ServletResponse{
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
            String header = Utils.buildHttpResponseHeader(file.length());

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

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(output);
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
