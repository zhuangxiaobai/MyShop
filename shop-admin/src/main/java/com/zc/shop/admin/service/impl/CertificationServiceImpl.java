package com.zc.shop.admin.service.impl;

import com.zc.shop.admin.dto.UpdateCertificationStatusParam;
import com.zc.shop.admin.mapper.CertificationExtMapper;
import com.zc.shop.admin.service.CertificationService;
import com.zc.shop.common.api.ResultCode;
import com.zc.shop.common.exception.BusinessException;
import com.zc.shop.mbg.po.Certification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CertificationServiceImpl implements CertificationService {

    @Autowired
    private CertificationExtMapper certificationExtMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(Certification certification, Integer userId) {

        certification.setUserid(userId);
        return certificationExtMapper.insertSelective(certification) ;
    }

    @Override
    public Certification myCertification(Integer userId) {

        return certificationExtMapper.selectByUserId(userId);
    }

    @Override
    public Certification selectCertificationByUserId(Integer userId) {


        return certificationExtMapper.selectByUserId(userId);
    }

    @Override
    public int updateCertificationStatus(UpdateCertificationStatusParam updateCertificationStatusParam) {


        Certification certification = certificationExtMapper.selectByPrimaryKey(updateCertificationStatusParam.getId());

        if(certification !=null && certification.getStatus() == 0){


           certification.setStatus(updateCertificationStatusParam.getStatus());


            int i = certificationExtMapper.updateByPrimaryKeySelective(certification);
            if(i !=0){

                throw  new BusinessException(ResultCode.FAILED);

            }

        }


        throw new BusinessException(ResultCode.FAILED);
    }
}
