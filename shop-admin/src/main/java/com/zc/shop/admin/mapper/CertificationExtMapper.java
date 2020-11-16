package com.zc.shop.admin.mapper;

import com.zc.shop.mbg.mapper.CertificationMapper;
import com.zc.shop.mbg.po.Certification;
import org.springframework.stereotype.Repository;


public interface CertificationExtMapper extends CertificationMapper {

    Certification selectByUserId(Integer userId);

    Certification selectByCompanyName(String guohu);
}
