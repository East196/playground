package com.github.east196.cloud.sidecar.controller;

import com.alibaba.fastjson.JSON;
import com.gitee.sop.servercommon.annotation.BizCode;
import com.gitee.sop.servercommon.annotation.Open;
import com.github.east196.cloud.sidecar.vo.StoryParam;
import com.github.east196.cloud.sidecar.vo.StoryResult;
import com.github.east196.cloud.sidecar.sign.AlipaySignature;
import com.github.east196.cloud.sidecar.util.HttpTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 演示参数验证
 *
 * @author tanghc
 */
@RestController
@RequestMapping("test")
@Slf4j
@Api(tags = "故事接口")
public class TestController {

    // http://localhost:3333/story/get
    // 原生的接口，可正常调用
    @RequestMapping("/get")
    public String get() {
        String result = "海底小纵队(原生)";
        return result;
    }

    // http://localhost:3333/test/mget
    // 原生的接口，可正常调用
    @SneakyThrows
    @RequestMapping("/mget")
    public String mget() {
        String url = "http://localhost:8081";
        String appId = "2019032617262200001";
        // 私钥
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCXJv1pQFqWNA/++OYEV7WYXwexZK/J8LY1OWlP9X0T6wHFOvxNKRvMkJ5544SbgsJpVcvRDPrcxmhPbi/sAhdO4x2PiPKIz9Yni2OtYCCeaiE056B+e1O2jXoLeXbfi9fPivJZkxH/tb4xfLkH3bA8ZAQnQsoXA0SguykMRZntF0TndUfvDrLqwhlR8r5iRdZLB6F8o8qXH6UPDfNEnf/K8wX5T4EB1b8x8QJ7Ua4GcIUqeUxGHdQpzNbJdaQvoi06lgccmL+PHzminkFYON7alj1CjDN833j7QMHdPtS9l7B67fOU/p2LAAkPMtoVBfxQt9aFj7B8rEhGCz02iJIBAgMBAAECggEARqOuIpY0v6WtJBfmR3lGIOOokLrhfJrGTLF8CiZMQha+SRJ7/wOLPlsH9SbjPlopyViTXCuYwbzn2tdABigkBHYXxpDV6CJZjzmRZ+FY3S/0POlTFElGojYUJ3CooWiVfyUMhdg5vSuOq0oCny53woFrf32zPHYGiKdvU5Djku1onbDU0Lw8w+5tguuEZ76kZ/lUcccGy5978FFmYpzY/65RHCpvLiLqYyWTtaNT1aQ/9pw4jX9HO9NfdJ9gYFK8r/2f36ZE4hxluAfeOXQfRC/WhPmiw/ReUhxPznG/WgKaa/OaRtAx3inbQ+JuCND7uuKeRe4osP2jLPHPP6AUwQKBgQDUNu3BkLoKaimjGOjCTAwtp71g1oo+k5/uEInAo7lyEwpV0EuUMwLA/HCqUgR4K9pyYV+Oyb8d6f0+Hz0BMD92I2pqlXrD7xV2WzDvyXM3s63NvorRooKcyfd9i6ccMjAyTR2qfLkxv0hlbBbsPHz4BbU63xhTJp3Ghi0/ey/1HQKBgQC2VsgqC6ykfSidZUNLmQZe3J0p/Qf9VLkfrQ+xaHapOs6AzDU2H2osuysqXTLJHsGfrwVaTs00ER2z8ljTJPBUtNtOLrwNRlvgdnzyVAKHfOgDBGwJgiwpeE9voB1oAV/mXqSaUWNnuwlOIhvQEBwekqNyWvhLqC7nCAIhj3yvNQKBgQCqYbeec56LAhWP903Zwcj9VvG7sESqXUhIkUqoOkuIBTWFFIm54QLTA1tJxDQGb98heoCIWf5x/A3xNI98RsqNBX5JON6qNWjb7/dobitti3t99v/ptDp9u8JTMC7penoryLKK0Ty3bkan95Kn9SC42YxaSghzqkt+uvfVQgiNGQKBgGxU6P2aDAt6VNwWosHSe+d2WWXt8IZBhO9d6dn0f7ORvcjmCqNKTNGgrkewMZEuVcliueJquR47IROdY8qmwqcBAN7Vg2K7r7CPlTKAWTRYMJxCT1Hi5gwJb+CZF3+IeYqsJk2NF2s0w5WJTE70k1BSvQsfIzAIDz2yE1oPHvwVAoGAA6e+xQkVH4fMEph55RJIZ5goI4Y76BSvt2N5OKZKd4HtaV+eIhM3SDsVYRLIm9ZquJHMiZQGyUGnsvrKL6AAVNK7eQZCRDk9KQz+0GKOGqku0nOZjUbAu6A2/vtXAaAuFSFx1rUQVVjFulLexkXR3KcztL1Qu2k5pB6Si0K/uwQ=";
        // 公共请求参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        // 这里对应@Open.value属性
        params.put("method", "sidecar.test.get");
        params.put("format", "json");
        params.put("charset", "utf-8");
        params.put("sign_type", "RSA2");
        params.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        // 这里对应@Open.version属性
        params.put("version", "1.0");

        // 业务参数
        Map<String, Object> bizContent = new HashMap<>();
        bizContent.put("id", "1");
        bizContent.put("name", "葫芦娃");

        params.put("biz_content", JSON.toJSONString(bizContent));

        System.out.println("----------- 请求信息 -----------");
        System.out.println("请求参数：" + buildParamQuery(params));
        System.out.println("商户秘钥：" + privateKey);
        String content = AlipaySignature.getSignContent(params);
        System.out.println("待签名内容：" + content);
        String sign = AlipaySignature.rsa256Sign(content, privateKey, "utf-8");
        System.out.println("签名(sign)：" + sign);

        params.put("sign", sign);

        System.out.println("----------- 返回结果 -----------");
        String responseData = post(url, params);// 发送请求
        System.out.println(responseData);
        return responseData;
    }

    static HttpTool httpTool = new HttpTool();

    /**
     * 发送POST请求
     *
     * @param url
     * @return JSON或者字符串
     */
    public static String post(String url, Map<String, String> params) {
        try {
            return httpTool.request(url, params, Collections.emptyMap(), HttpTool.HTTPMethod.POST);
        } catch (IOException e) {
            throw new RuntimeException("网络请求异常", e);
        }
    }

    public static String postJson(String url, Map<String, String> params) {
        try {
            return httpTool.requestJson(url, JSON.toJSONString(params), Collections.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException("网络请求异常", e);
        }
    }

    /**
     * 发送get请求
     *
     * @param url
     * @return JSON或者字符串
     */
    public static String get(String url, Map<String, String> params) {
        try {
            return httpTool.request(url, params, Collections.emptyMap(), HttpTool.HTTPMethod.GET);
        } catch (IOException e) {
            throw new RuntimeException("网络请求异常", e);
        }
    }

    /**
     * 下载文件
     * @param url
     * @param params
     * @return 返回文件流
     */
    public static InputStream download(String url, Map<String, String> params) {
        try {
            return httpTool.downloadFile(url, params, null);
        } catch (IOException e) {
            throw new RuntimeException("网络请求异常", e);
        }
    }

    protected static String buildParamQuery(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        }
        return sb.toString().substring(1);
    }

    protected static String buildUrlQuery(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            try {
                sb.append("&").append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString().substring(1);
    }

    @Value("${server.port}")
    private int port;

    // 基础用法
    @ApiOperation(value = "获取故事信息（首位）", notes = "获取故事信息的详细信息", position = -100/* position默认0，值越小越靠前 */)
    @Open(value = "sidecar.test.get", bizCode = {
            // 定义业务错误码，用于文档显示
            @BizCode(code = "100001", msg = "姓名错误", solution = "填写正确的姓名"),
            @BizCode(code = "100002", msg = "备注错误", solution = "填写正确备注"),
    })
    @RequestMapping("/get/v1")
    public StoryResult get_v1(StoryParam param) {
        StoryResult story = new StoryResult();
        story.setId(1L);
        story.setName("海底小纵队(story.get1.0), " + "param:" + param + ", port:" + port);
        return story;
    }
}