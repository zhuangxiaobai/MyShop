package com.zc.shop.admin.mapper;

import com.zc.shop.admin.dto.PositionSelectParam;
import com.zc.shop.mbg.mapper.PositionMapper;
import com.zc.shop.mbg.po.Position;

import java.util.List;

public interface PositionExtMapper extends PositionMapper {
    List<Position> selectPositionList(PositionSelectParam positionSelectParam);

    int selectPositionListNum(PositionSelectParam positionSelectParam);
}
