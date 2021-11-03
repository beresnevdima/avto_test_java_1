package com.javatest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javatest.autorization.SidService;
import com.javatest.dto.NirvanaClient;
import com.javatest.http.NirvanaHttp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class NirvanaTestController {

    private SidService sidService;
    private NirvanaHttp http;

    //создаем экземпляры классов
    @BeforeEach
    public void init() {
        sidService = new SidService();
        http = new NirvanaHttp();
    }

    @Test
    void clientEdit() throws JsonProcessingException {
        String clientMNameSource = "Барбаросовичвввввв";
        String сomName = "Barara25";
        Long clientIdSource = 1100000283L;

        String sid = sidService.getSid();
        // формируем параметры которые хотим изменить у клиента
        NirvanaClient editClient = new NirvanaClient();
        editClient.setClientId(clientIdSource);
        editClient.setComName(сomName);

        //сделали запрос должны получить обновленного клиента
//        nirvanaClientBeforeEdit это ответ , проверяем его
        NirvanaClient nirvanaClientBeforeEdit = http.clientEdit(editClient, sid);
        Assert.isNull(nirvanaClientBeforeEdit.getError());
        Assert.isTrue(сomName.equals(nirvanaClientBeforeEdit.getComName()));

    }


}
