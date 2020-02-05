
import java.io.*;

/**
 * HttpClient 测试类，提供get post方法实例
 * @author zdz8207
 *
 */
public class HttpClientTest {
//    private static final String URL = "http://www.163.com";
//    private static final int PORT = 80;
//
//    public static void main(String[] args) throws Exception {
//
//        //测试get方法
//        doGetMethod(URL,PORT);
//        //测试post方法
//        String posturl = "http://mail.163.com/";
//        doPostMethod(posturl,PORT);
//    }
//
//    /**
//     * http get 方法
//     * @param url
//     * @param port
//     * @throws HttpException
//     * @throws IOException
//     * @return bodystring
//     */
//    public static String doGetMethod(String url,int port) throws HttpException, IOException {
//        HttpClient client = new HttpClient();
//        // 设置代理服务器地址和端口
//        client.getHostConfiguration().setHost(url, port);
//        // 使用 GET 方法
//        GetMethod method = new GetMethod(url);
//        //执行请求
//        client.executeMethod(method);
//
//        // 打印服务器返回的状态
//        System.out.println(method.getStatusLine());
//        // 打印返回的信息
////        System.out.println(method.getResponseBodyAsString());
//        // response body of large or unknown size. Using getResponseBodyAsStream instead is recommended.
//        InputStream bodystreams = method.getResponseBodyAsStream();
//        String body = convertStreamToString(bodystreams);
//        System.out.println(body);
//        // 释放连接
//        method.releaseConnection();
//
//        return body;
//    }
//
//    /**
//     * http post 方法
//     * @param url
//     * @param port
//     * @throws HttpException
//     * @throws IOException
//     * @return bodystring
//     */
//    public static String doPostMethod(String url,int port) throws HttpException, IOException {
//        HttpClient client = new HttpClient();
//        // 设置代理服务器地址和端口
//        client.getHostConfiguration().setHost(url, port);
//        // 使用POST方法
//        PostMethod method = new PostMethod(url);
//        //设置传递参数
//        NameValuePair agent = new NameValuePair("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows 2000)");
//        NameValuePair username = new NameValuePair("email", "xxx@163.com");
//        NameValuePair password = new NameValuePair("password", "xxxxxx");
//        method.setRequestBody(new NameValuePair[] { agent, username, password });
//        //执行请求
//        client.executeMethod(method);
////        设置cookies
////        Cookie[] cookies = client.getState().getCookies();
////        client.getState().addCookies(cookies);
//
//        // 打印服务器返回的状态
//        System.out.println(method.getStatusLine());
//        // 打印返回的信息
////        System.out.println(method.getResponseBodyAsString());
//        // response body of large or unknown size. Using getResponseBodyAsStream instead is recommended.
//        InputStream bodystreams = method.getResponseBodyAsStream();
//        String body = convertStreamToString(bodystreams);
//        System.out.println(body);
//        // 释放连接
//        method.releaseConnection();
//
//        return body;
//    }
//
//    /**
//     * 把InputStream 转换成String返回
//     * @param is InputStream
//     * @return String
//     * @throws UnsupportedEncodingException
//     */
//    public static String convertStreamToString(InputStream is) throws UnsupportedEncodingException {
////        BufferedReader reader = new BufferedReader(new InputStreamReader(is));//输出的中文乱码
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf8")); //GBK
//        StringBuilder sb = new StringBuilder();
//        String line = null;
//        try {
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return sb.toString();
//    }

}