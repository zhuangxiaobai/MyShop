package com.zc.shop.admin.service.impl;

import com.zc.shop.admin.dto.MessageListSelectParam;
import com.zc.shop.admin.mapper.MessageExtMapper;
import com.zc.shop.admin.mapper.MessageInfoExtMapper;
import com.zc.shop.admin.service.MessageService;
import com.zc.shop.admin.vo.MessageVo;
import com.zc.shop.mbg.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    private MessageExtMapper messageExtMapper;


    @Autowired
    private MessageInfoExtMapper messageInfoExtMapper;


    @Override
    public List<MessageVo> myMessageList(MessageListSelectParam messageListSelectParam) {


        List<MessageVo>  messageVos = messageExtMapper.selectMessageVoList();




        return messageVos;
    }
}
