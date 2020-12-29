package com.zc.shop.admin.util;

import com.zc.shop.admin.dto.PageParam;

public class  MyPageUtil {


    /**
     *
     * @param pageParam 分页传输对象
     *
     * @return 对startPage进行处理，设置开始的条数，返回分页对象
     */
    public static PageParam exchangeStartPage(PageParam pageParam){


        Integer startPage = pageParam.getStartPage();
        Integer pageSize = pageParam.getPageSize();
        Integer  start = (startPage-1)*pageSize ;

        pageParam.setStartPage(start);


        return pageParam;
    }


}
