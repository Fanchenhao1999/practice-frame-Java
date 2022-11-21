package com.fan.demo;



import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;


/**
 * @className: CloseableHttpClientDemo01
 * @author: ChenHao
 * @date: 2022/11/17
 **/

public class CloseableHttpClientDemo01 {
    // 编码格式。发送编码格式统一用UTF-8
    private static final String ENCODING = "UTF-8";
    //请求超时时间,这个时间定义了socket读数据的超时时间，也就是连接到服务器之后到从服务器获取响应数据需要等待的时间,发生超时，会抛出SocketTimeoutException异常。
    private static final int SOCKET_TIME_OUT = 60000;
    //连接超时时间,这个时间定义了通过网络与服务器建立连接的超时时间，也就是取得了连接池中的某个连接之后到接通目标url的连接等待时间。发生超时，会抛出ConnectionTimeoutException异常
    private static final int CONNECT_TIME_OUT = 60000;

    /**
     * 发送HttpGet请求,不带请求头和请求参数
     * @param url
     * @return
     */
    public static String doGet(String url) throws Exception {

        return doGet(url,null);
    }

    /**
     * 发送HttpGet请求，带请求头和请求参数
     * @param url
     * @param token
     * @return
     */
    public static String doGet(String url,String token) throws Exception {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = null;
        try {
            //1.创建httpClient对象
            httpClient = HttpClients.createDefault();
            //2.创建httpGet对象
            HttpGet httpget = new HttpGet(url);
            //2.1设置请求头
            httpget.setHeader("Authorization",token);
            //2.2对象设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIME_OUT).setConnectTimeout(CONNECT_TIME_OUT).build();
            httpget.setConfig(requestConfig);
            //3.使用httpClient发起请求并响应获取repsonse
            httpResponse = httpClient.execute(httpget);
            // 打印响应状态
            System.out.println(httpResponse.getStatusLine());
            //4.解析响应，获取数据
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
                // 打印响应内容长度
                System.out.println("Response content length: " + entity.getContentLength());
                // 打印响应内容
                System.out.println("Response content: " + result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //5.释放资源
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    //log.error(e.getMessage(), e);
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    //log.error(e.getMessage(), e);
                }
            }
        }
        return result;
    }


}
