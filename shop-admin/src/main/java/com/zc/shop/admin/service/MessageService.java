package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.MessageListSelectParam;
import com.zc.shop.admin.vo.MessageVo;

import java.util.List;

public interface MessageService {
    List<MessageVo> myMessageList(MessageListSelectParam messageListSelectParam);

    int updateStatus(List<Integer> readingHaved);

    int updateDelete(List<Integer> needDelete);
}
