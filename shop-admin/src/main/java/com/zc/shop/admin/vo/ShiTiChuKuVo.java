package com.zc.shop.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zc.shop.mbg.po.Lading;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ShiTiChuKuVo {

  //提单号
   private String ladingCode;

   //提货时间
   //@JsonFormat(pattern="yyyy-MM-dd")
   //private LocalDateTime ladingedAt;

  //公司名字
   //private String  companyName;

  //提单状态
  // private Integer status;


   private List<LadingAllVo> ladingAllVos;





}
