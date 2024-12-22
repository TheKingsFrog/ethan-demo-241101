package com.ethan.businesscore.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/ai")
public class AiController {

    @GetMapping("/send-msg")
    public String sendMsg() {

        RestTemplate restTemplate = new RestTemplate();

        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer" + "sk-28e1cfe222ed4e29b410faeaa497518c");

        // 创建请求体
        String jsonBody = "{\n" +
                "    \"model\": \"qwen-plus\", \n" +
                "    \"messages\": [\n" +
                "        {\n" +
                "            \"role\": \"system\",\n" +
                "            \"content\": \"You are a helpful assistant.\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"role\": \"user\", \n" +
                "            \"content\": \"你是谁？\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        // 包装请求体和请求头
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        // 发送 POST 请求
        ResponseEntity<String> response = restTemplate.exchange(
                "https://dashscope.aliyuncs.com/compatible-mode/v1/chat/completions",
                HttpMethod.POST,
                entity,
                String.class);

        // 输出响应内容
        System.out.println("Response: " + response.getBody());

        return response.getBody();

    }

}
