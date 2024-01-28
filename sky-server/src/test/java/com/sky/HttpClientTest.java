package com.sky;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@SpringBootTest
public class HttpClientTest {

    /**
     * 测试HttpClient发送GET方式请求
     */
    @Test
    public void testGET() throws IOException {
        // 1.创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 2.创建请求对象
        HttpGet httpGet = new HttpGet("http://localhost:8080/user/shop/status");

        // 3.发送请求,接收响应结果
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 4.获取服务端返回的状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("服务端返回的状态码为：" + statusCode);

        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        System.out.println("服务器返回的数据为：" + body);

        // 5.关闭资源
        response.close();
        httpClient.close();
    }


    /**
     * 测试HttpClient发送POST方式请求
     */
    @Test
    public void testPOST() throws Exception {
        // 1.创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 2.创建请求对象
        HttpPost httpPost = new HttpPost("http://localhost:8080/admin/employee/login");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","admin");
        jsonObject.put("password","123456");

        StringEntity entity = new StringEntity(jsonObject.toString());
        // 指定请求编码方式
        entity.setContentEncoding("utf-8");
        // 数据格式 （Json格式）
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        // 3.发送请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 4.解析返回结果
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("响应状态码为：" + statusCode);
        // 具体响应数据
        HttpEntity entity1 = response.getEntity();
        String body = EntityUtils.toString(entity1);
        System.out.println("响应数据为：" + body);

        // 5.关闭资源
        response.close();
        httpClient.close();
    }

}
