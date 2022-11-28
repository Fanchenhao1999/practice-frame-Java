package com.fan.utils;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class HttpClientUtilsDemo {
    /**
     * get 请求
     *
     * @param url
     * @param headers
     * @return
     */
    public static String doGet(String url, String headers) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        HttpEntity entity = null;
        String result = null;
        try {
            if (headers != null) {
                String[] headArray = headers.split(";");
                if (headArray.length > 1) {
                    for (String head : headArray) {
                        get.setHeader(head.split("=")[0], head.split("=")[1]);
                    }
                }
            }
            CloseableHttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                entity = response.getEntity();
                result = EntityUtils.toString(entity, "utf-8");
            } else {
                result = "接口请求错误";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            EntityUtils.consume(entity);
        }
        return result;
    }

    /**
     * post 请求,form表单
     *
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static String doPost(String url, Map<String,Object> params, Map<String,Object> headers){
        CloseableHttpClient client = HttpClients.createDefault();
        HttpEntity entity = null;
        HttpPost post = new HttpPost(url);
        String result = null;
        headers.forEach((key,value)->post.setHeader(key, String.valueOf(value)));
        try {
            if (params !=null) {
                List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
                params.forEach((k, v) -> parameters.add(new BasicNameValuePair(k, v.toString())));
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, "utf-8");
                post.setEntity(formEntity);
            }
            CloseableHttpResponse reponse = null;
            reponse = client.execute(post);
            if (reponse.getStatusLine().getStatusCode() == 200) {
                entity = reponse.getEntity();
                result= EntityUtils.toString(reponse.getEntity(), "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                EntityUtils.consume(entity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public static String doPost(String url, String params, String headers){
        return HttpClientUtilsDemo.doPost(url, StringToUtils.convert(params,"&"),StringToUtils.convert(headers,";"));
    }

    /**
     * post 请求,json请求
     *
     * @param url
     * @param json
     * @param headers
     * @return
     */
    public static String doPostJson(String url, String json,Map<String,Object> headers){
        String result =null;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpEntity entity = null;
        HttpPost post = new HttpPost(url);
        headers.forEach((key,value)->post.setHeader(key, String.valueOf(value)));
        post.addHeader("Content-type", "application/json;charset=utf-8");
        try{
            if(StringUtils.isNotBlank(json)){
                StringEntity stringEntity=new StringEntity(json, "utf-8");
                post.setEntity(stringEntity);
            }
            CloseableHttpResponse httpResponse =client.execute(post);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                entity = httpResponse.getEntity();
                result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                EntityUtils.consume(entity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    public static String doPostJson(String url, String json, String headers) {
        return doPostJson(url, json, StringToUtils.convert(headers, ";"));
    }
}



