package com.fan.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.json.JSONObject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public  class HttpClientUtils {

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

        return doGet(url,null,null);
    }


    /**
     * 发送HttpGet请求，带请求头和请求参数
     * @param url
     * @param token
     * @return
     */
    public static String doGet(String url,Map<String,String> params,String token) throws Exception{

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = null;
        try {
            //1.创建httpClient对象
            httpClient = HttpClients.createDefault();
            // 创建访问的地址
            URIBuilder uriBuilder = new URIBuilder(url);
            if (params != null) {
                Set<Entry<String, String>> entrySet = params.entrySet();
                for (Entry<String, String> entry : entrySet) {
                    uriBuilder.setParameter(entry.getKey(), entry.getValue());
                }
            }



            //2.创建httpGet对象
            HttpGet httpget = new HttpGet(uriBuilder.build());
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
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        finally {
            //5.释放资源
            release(httpResponse,httpClient);
        }
        return result;
    }



    /**
     * 发送POST请求,不带Token，不带请求参数
     * @param url
     * @return
     */

    public static String doPost(String url) throws Exception {

        return doPost(url,null,null);

    }

    /**
     * 发送POST请求,不带Token，带请求参数
     * @param url
     * @param jsonstr
     * @return
     */



    public static String doPost(String url, String jsonstr) throws Exception {
        return doPost(url,jsonstr,null);
    }



    /**
     * 发送POST请求,带Token，带请求参数
     * @param url
     * @param jsonstr
     * @param token
     * @return
     */

    //public static String doPost(String url, JSONObject json,String token) {

    public static String doPost(String url, String jsonstr,String token) throws Exception {

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse=null;
        String result="";

        try {
            //1.创建httpClient对象
            httpClient = HttpClients.createDefault();
            //2.创建httpPost对象
            HttpPost httpPost = new HttpPost(url);
            //2.1对象设置请求头
            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
            httpPost.setHeader("Authorization",token);
            //2.2对象设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIME_OUT).setConnectTimeout(CONNECT_TIME_OUT).build();
            httpPost.setConfig(requestConfig);
            //2.3对象设置请求参数
            //httpPost.setEntity(new StringEntity(json.toString()));
            httpPost.setEntity(new StringEntity(jsonstr));
            //3.使用httpClient发起请求并响应获取repsonse
            httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            //4.解析响应，获取数据
            //判断状态码是否是200
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(entity,ENCODING);
                System.out.println(result);
            }

            System.out.println(httpResponse.getStatusLine());
            System.out.println(httpResponse.getStatusLine().getStatusCode());
            System.out.println(httpResponse.getProtocolVersion());
            System.out.println(HttpStatus.SC_OK);
        }

        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            //5.释放资源
            release(httpResponse,httpClient);
        }
        return result;
    }



    /**
     * 以post方式访问login获取token(http)
     * @param url
     * @param jsonstr
     * @return
     */

    public static String getToken_login(String url, String jsonstr) throws Exception {

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse=null;
        String token="";
        try {
            //1.创建httpClient对象
            httpClient = HttpClients.createDefault();
            //2.创建httpPost对象
            HttpPost httpPost = new HttpPost(url);
            //2.1对象设置请求头
            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
            //2.2对象设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIME_OUT).setConnectTimeout(CONNECT_TIME_OUT).build();
            httpPost.setConfig(requestConfig);
            //2.3对象设置请求参数
            httpPost.setEntity(new StringEntity(jsonstr));
            //3.使用httpClient发起请求并响应获取repsonse
            httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            //4.解析响应，获取数据
            //判断状态码是否是200
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(entity,ENCODING);
                JSONObject reslut_json = new JSONObject(result);
                JSONObject token_data= (JSONObject) (JSONObject)reslut_json.get("data");
                token= (String) token_data.get("accessToken");
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            //5.释放资源
            release(httpResponse,httpClient);
        }
        return token;
    }



    /**
     * Description: 封装请求参数
     *
     * @param params
     * @param httpMethod
     * @throws UnsupportedEncodingException
     */

    public static void packageParam(Map<String, String> params, HttpEntityEnclosingRequestBase httpMethod)
            throws UnsupportedEncodingException {
        // 封装请求参数
        if (params != null) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Set<Entry<String, String>> entrySet = params.entrySet();
            for (Entry<String, String> entry : entrySet) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }

            // 设置到请求的http对象中
            httpMethod.setEntity(new UrlEncodedFormEntity(nvps, ENCODING));
        }
    }



    /**
     * Description: 释放资源
     *
     * @param httpResponse
     * @param httpClient
     * @throws IOException
     */

    public static void release(CloseableHttpResponse httpResponse,CloseableHttpClient httpClient) throws IOException{
        // 释放资源
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

}