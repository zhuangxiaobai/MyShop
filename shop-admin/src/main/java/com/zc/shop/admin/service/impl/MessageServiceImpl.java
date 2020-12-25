package com.zc.shop.admin.service.impl;

import com.zc.shop.admin.dto.MessageListSelectParam;
import com.zc.shop.admin.mapper.MessageExtMapper;
import com.zc.shop.admin.mapper.MessageInfoExtMapper;
import com.zc.shop.admin.service.MessageService;
import com.zc.shop.admin.vo.MessageVo;
import com.zc.shop.common.exception.BusinessException;
import com.zc.shop.mbg.mapper.MessageMapper;
import com.zc.shop.mbg.po.Message;
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


    //     Integer type = messageListSelectParam.getType();
        // 0为全部,1为系统,2为私信,3为代办,必要
    //        if(type == 0){
    //            messageListSelectParam.setType(null);
    //        }else if(type == 1){
    //            messageListSelectParam.setType(2);
    //        }else if(type == 2){
    //            messageListSelectParam.setType(1);
    //        }else if(type == 3){
    //            messageListSelectParam.setType(0);
    //        }

        //分页查询处理
        Integer startPage = messageListSelectParam.getPageParam().getStartPage();
        Integer pageSize =  messageListSelectParam.getPageParam().getPageSize();
        Integer  start = (startPage-1)*pageSize ;
        messageListSelectParam.getPageParam().setStartPage(start);

        List<MessageVo>  messageVos = messageExtMapper.selectMessageVoList(messageListSelectParam);




        return messageVos;
    }

    @Override
    public int updateStatus(List<Integer> readingHaved) {

        Message message = new Message();

        int success = 0;
        for(Integer id:readingHaved){

            message.setId(id);
            message.setStatus(1);
            int i = messageExtMapper.updateByPrimaryKeySelective(message);
            if(i == 1){
                success +=1;
            }
        }
        if(success == readingHaved.size()){
             return 1;
        }else {
            throw new BusinessException("修改已读时出现异常");
        }

    }

    @Override
    public int updateDelete(List<Integer> needDelete) {

        Message message = new Message();

        int success = 0;
        for(Integer id:needDelete){

            message.setId(id);
            message.setIsDelete(1);
            int i = messageExtMapper.updateByPrimaryKeySelective(message);
            if(i == 1){
                success +=1;
            }
        }
        if(success == needDelete.size()){
            return 1;
        }else {
            throw new BusinessException("删除通知时出现异常");
        }

    }
}
