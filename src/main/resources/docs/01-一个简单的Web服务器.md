
## HTTP (version HTTP/1.1)
#### HTTP请求
- 请求方法&nbsp;统一资源标识符(URI)&nbsp;协议版本
- 请求首部
- 请求实体

以下样例来自Tomcat自带应用(http://IP:Port/examples/servlets/servlet/RequestParamExample)
```
POST /examples/servlets/servlet/RequestParamExample HTTP/1.1
Host: 192.168.236.132:8080
Connection: keep-alive
Content-Length: 27
Cache-Control: max-age=0
Origin: http://192.168.236.132:8080
Upgrade-Insecure-Requests: 1
Content-Type: application/x-www-form-urlencoded
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
Referer: http://192.168.236.132:8080/examples/servlets/servlet/RequestParamExample
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.8
Cookie: JSESSIONID=ECAF822F868642ED905C21F36731EF17

firstname=Luo&lastname=Jian
```


#### HTTP响应
- 协议 状态码 
- 响应首部
- 响应实体


```
HTTP/1.1 200
Content-Type: text/html;charset=UTF-8
Content-Length: 682
Date: Sun, 01 Oct 2017 08:42:20 GMT

<!DOCTYPE html><html>
<head>
<meta charset="UTF-8" />
<title>Request Parameters Example</title>
</head>
<body bgcolor="white">
<a href="../reqparams.html">
<img src="../images/code.gif" height=24 width=24 align=right border=0 alt="view code"></a>
<a href="../index.html">
<img src="../images/return.gif" height=24 width=24 align=right border=0 alt="return"></a>
<h3>Request Parameters Example</h3>
Parameters in this request:<br>
First Name:
 = Luo<br>
Last Name:
 = Jian
<P>
<form action="RequestParamExample" method=POST>
First Name:
<input type=text size=20 name=firstname>
<br>
Last Name:
<input type=text size=20 name=lastname>
<br>
<input type=submit>
</form>
</body>
</html>
```

**阅读《HTTP权威指南》前四章可以学习首部字段的意思。**

