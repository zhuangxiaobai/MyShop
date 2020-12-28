package com.zc.shop.admin.service.impl;

import com.zc.shop.admin.mapper.MessageExtMapper;
import com.zc.shop.admin.mapper.MessageInfoExtMapper;
import com.zc.shop.admin.service.MessageManagerService;
import com.zc.shop.common.exception.BusinessException;
import com.zc.shop.mbg.po.Message;
import com.zc.shop.mbg.po.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 通知操作的通用service
 */
@Service
public class MessageManagerServiceImpl implements MessageManagerService {


    @Autowired
    private MessageExtMapper messageExtMapper;


    @Autowired
    private MessageInfoExtMapper messageInfoExtMapper;


    /**
     *
     * @param orderCode 订单号
     * @param supplierId 卖方id
     * @param buyUserId  买方id
     * @param userId   当前操作人id
     * @param now   当前时间
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMessageByOrderCode(String orderCode,Integer supplierId,Integer buyUserId,Integer userId, LocalDateTime now ){

       //修改完事之后去添加一条代办通知
       Message message = new Message();
       MessageInfo messageInfo = new MessageInfo();
       messageInfo.setTitle("订单状态变更");
       messageInfo.setText("订单号"+orderCode+"的订单状态出现变更,请速处理");

       int i = messageInfoExtMapper.insertSelective(messageInfo);
       if(i != 1){
           throw new BusinessException("插入通知信息时异常");
       }

       message.setInfoId(messageInfo.getId());
       //1.看1下是买家还是卖家发的
       if(supplierId.equals(userId)){
           message.setCreateId(userId);
           message.setReceiveId(buyUserId);
           //卖家发的，所以是买家消息
           message.setTypeNext(0);

       }else if(buyUserId.equals(userId)){
           message.setCreateId(userId);
           message.setReceiveId(supplierId);
           //买家发的，所以是卖家消息
           message.setTypeNext(1);
       }

       message.setCreatedAt(now);
       message.setType(0);

       int k = messageExtMapper.insertSelective(message);
       if(k != 1){
           throw new BusinessException("插入通知主体时异常");
       }




   }

    /**
     *
     * @param tiCode 提单号
     * @param supplierId 卖方id
     * @param buyUserId  买方id
     * @param userId   当前操作人id
     * @param now   当前时间
     */
    @Override
    public void addMessageByTiCode(String tiCode, Integer supplierId, Integer buyUserId, Integer userId, LocalDateTime now) {

        //修改完事之后去添加一条代办通知
        Message message = new Message();
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setTitle("提单状态变更");
        messageInfo.setText("提单号"+tiCode+"的提单状态出现变更,请速处理");

        int i = messageInfoExtMapper.insertSelective(messageInfo);
        if(i != 1){
            throw new BusinessException("插入通知信息时异常");
        }

        message.setInfoId(messageInfo.getId());
        //1.看1下是买家还是卖家发的
        if(supplierId.equals(userId)){
            message.setCreateId(userId);
            message.setReceiveId(buyUserId);
            //卖家发的，所以是买家消息
            message.setTypeNext(0);

        }else if(buyUserId.equals(userId)){
            message.setCreateId(userId);
            message.setReceiveId(supplierId);
            //买家发的，所以是卖家消息
            message.setTypeNext(1);
        }

        message.setCreatedAt(now);
        message.setType(0);

        int k = messageExtMapper.insertSelective(message);
        if(k != 1){
            throw new BusinessException("插入通知主体时异常");
        }



    }


}
