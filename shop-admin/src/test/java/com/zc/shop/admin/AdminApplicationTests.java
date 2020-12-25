package com.zc.shop.admin;

import com.zc.shop.admin.mapper.MessageExtMapper;
import com.zc.shop.admin.mapper.MessageInfoExtMapper;
import com.zc.shop.admin.vo.MessageVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AdminApplicationTests {


    @Autowired
    private MessageExtMapper messageExtMapper;


    @Autowired
    private MessageInfoExtMapper messageInfoExtMapper;


    @Test
    void contextLoads() {

        /*List<MessageVo>  messageVos = messageExtMapper.selectMessageVoList(messageListSelectParam);

        System.out.println(messageVos.size());
        for(MessageVo messageVo:messageVos){
            System.out.println(messageVo.toString());
        }*/
    }

}
