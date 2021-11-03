package com.javatest.autorization;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.javatest.util.PropertySourceResolver;
import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;

//получение сида
@Component
@Slf4j
public class SidService {
    //создаем экземпляры классов
    private PropertySourceResolver property = new PropertySourceResolver();
    private ObjectMapper objectMapper = new ObjectMapper();
    private XmlMapper xmlMapper = new XmlMapper();

    public String getSid() throws JsonProcessingException {
        //формируем тело запроса
        User user = new User(property.getIamSessionLogin(), property.getIamSessionAuthn(), property.getIamSessionPassword());
        String body = objectMapper.writeValueAsString(new AuthorizationSid(user));
        try {
            //сам запрос
            HttpResponse resp = Request.Post(property.getIamSecurityBaseUrl())
                    .bodyString(body, APPLICATION_JSON)
                    .execute()
                    .returnResponse();
            String text = EntityUtils.toString(resp.getEntity(), "UTF-8");
            //достаем то что нам нужно
            JsonNode root = xmlMapper.readTree(text);
            return root.get("value").asText();
        } catch (Exception e) {
            log.info("Упс " + e);
        }
        return null;
    }
}
