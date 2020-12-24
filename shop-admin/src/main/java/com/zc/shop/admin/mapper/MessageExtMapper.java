package com.zc.shop.admin.mapper;

import com.zc.shop.admin.vo.MessageVo;
import com.zc.shop.mbg.mapper.MessageMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageExtMapper  extends MessageMapper {

    List<MessageVo> selectMessageVoList();
}
