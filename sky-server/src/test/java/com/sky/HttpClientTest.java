package com.sky;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

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

        // 3.发送请求,接受响应结果
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
}
