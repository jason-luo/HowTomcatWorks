package ex.ch02;

/**
 * Created by cava on 2017/10/29.
 */
public class Utils {
    public static String buildHttpResponseHeader(long contentLength){
        return "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + contentLength  + "\r\n" +
                "\r\n";
    }
}
