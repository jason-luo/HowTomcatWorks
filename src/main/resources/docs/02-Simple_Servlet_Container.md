
# 什么是Servlet容器
第一章实现了一个简单的Web服务器，只能访问静态页面，静态页面对应服务器端的某个文件，当你访问它的时候，服务器直接读取发送到客户端，不做任何的处理。这个在很多情况下的是够用的，比如说你写了一篇博客，放在服务端目录，读者看的时候，直接从服务端发送到客户端，不需要做任何处理。假如你的读者想给你反馈，可能需要找到你的联系方式发邮件或者其他的方式给你留言，而我们看到很多的博客下面是支持直接留言的。这时就需要服务端处理了。

Servlet是Java提出的服务端接口规范，全称Java Server Applet，即服务端小程序。
Web服务一般支持很多的功能，每个功能都对应一个或多个Servlet,这些Servlet由Servlet容器统一管理。


# 如何实现Servlet
只要实现如下接口的类都可以称为Servlet。

```
public interface Servlet {
    void init(ServletConfig var1) throws ServletException;

    ServletConfig getServletConfig();

    void service(ServletRequest var1, ServletResponse var2) throws ServletException, IOException;

    String getServletInfo();

    void destroy();
}

```

# 如何实现Servlet容器
这一章没有实现完整的Servlet容器，只加载了Servlet类并调用service方法。通过反射类名获取Class对象，调用构造函数生成Servlet对象，再调用service接口完成业务逻辑。


