import ex.ch02.Utils;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by cava on 2017/10/8.
 */
public class PrimitiveServlet implements Servlet{
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("from servlet service");

        StringBuilder sb = new StringBuilder();
        sb.append("Hello. Rose are red.").append("\r\n");
        sb.append("Violets are blue");

        String header = Utils.buildHttpResponseHeader(sb.length());
        PrintWriter out = servletResponse.getWriter();
        out.write(header);
        out.write(sb.toString());
        out.flush();
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
