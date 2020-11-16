package com.zc.shop.admin.vo;

import com.zc.shop.mbg.po.Pages;
import lombok.Data;

import java.util.List;

@Data
public class PagesVo {

   private String label;

   private Pages pages;

   private List<PagesVo> pagesVoList;
}
