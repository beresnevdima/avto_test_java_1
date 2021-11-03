package com.javatest.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.javatest.dto.NirvanaClient;
import com.javatest.util.PropertySourceResolver;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;

@Slf4j
public class NirvanaHttp {

    //создаем екземпляр класса
    private PropertySourceResolver property = new PropertySourceResolver();

    //создаем екземпляр класса
    private ObjectMapper objectMapper = new ObjectMapper();

    public NirvanaClient clientEdit(NirvanaClient edit, String sid) throws JsonProcessingException {
        //формируем тело запроса
        String body = objectMapper.writeValueAsString(edit);
        try {
            //сам запрос
            HttpResponse resp = Request.Post(property.getNirvanaApiBaseUrl() + "/v1/api/client/edit")
                    .addHeader("sid", sid)
                    .addHeader("Client-Id", String.valueOf(edit.getClientId()))
                    .bodyString(body, APPLICATION_JSON)
                    .execute()
                    .returnResponse();
            String text = EntityUtils.toString(resp.getEntity(), "UTF-8");
            JsonNode rootError = objectMapper.readTree(text).get("errCode");
            if (rootError != null) {
                log.info(rootError.toString());
                com.javatest.dto.Error error = objectMapper.readValue(text, com.javatest.dto.Error.class);
                return new NirvanaClient(error);
            }
            //достаем то что нам нужно, оно лежит в result
            JsonNode root = objectMapper.readTree(text).get("result");
            return objectMapper.readValue(root.toString(), NirvanaClient.class);
        } catch (Exception e) {
            log.info("Упс " + e);
        }
        return null;
    }
}
