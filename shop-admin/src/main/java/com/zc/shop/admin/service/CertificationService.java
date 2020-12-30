package com.zc.shop.admin.service;

import com.zc.shop.admin.dto.UpdateCertificationStatusParam;
import com.zc.shop.mbg.po.Certification;


public interface CertificationService {
    int create(Certification certification, Integer userId);

    Certification myCertification(Integer userId);

    Certification selectCertificationByUserId(Integer userId);

    int updateCertificationStatus(UpdateCertificationStatusParam updateCertificationStatusParam);
}
