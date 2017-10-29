package ex.ch02;


import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.net.ContentHandler;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

public class ServletProcessor {
    public void process(Request request, Response response){
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf('/')+1);
        URLClassLoader classLoader = null;

        URL[] urls = new URL[1];
        File classPath = new File(Constants.WEB_ROOT);
        try {
            String repository = (new URL("file", null, classPath.getCanonicalPath()+File.separator)).toString();
            urls[0] = new URL(null, repository, (URLStreamHandler) null);
            classLoader = new URLClassLoader(urls);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Class myClass = null;
        try {
            myClass = classLoader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Servlet servlet = null;

        try {
            servlet = (Servlet)myClass.newInstance();
            servlet.service(request, response);
        }catch (ServletException | InstantiationException |IOException |IllegalAccessException  e) {
            e.printStackTrace();
        }

    }
}
