package com.zc.shop.admin.service;

import java.time.LocalDateTime;

public interface MessageManagerService {




      void  addMessageByOrderCode(String orderCode,Integer supplierId,Integer buyUserId,Integer userId, LocalDateTime now );



       void  addMessageByTiCode(String tiCode,Integer supplierId,Integer buyUserId,Integer userId, LocalDateTime now );


     void addMessageBySys(Integer receiveId, Integer createdId,LocalDateTime now,String title,String text);



}
