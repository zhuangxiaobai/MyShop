package com.zc.shop.admin.util;

import java.util.ArrayList;
import java.util.List;

public class ListPageUtil {

    public static List page(List list, int currentPage, int pageSize ) {
        int totalCount = list.size();
        int pageCount = 1;
        List subList = new ArrayList<>();
        int m = pageCount % pageSize;
        if (m > 0) {
            pageCount = totalCount / pageSize + 1;
        } else {
            pageCount = totalCount / pageSize;
        }
        if(((currentPage - 1) * pageSize) >= totalCount){
            return subList;
        }
        if (m == 0) {
            subList = list.subList((currentPage - 1) * pageSize, pageSize * (currentPage)>totalCount?totalCount-1:pageSize * (currentPage));
        } else {
            if (currentPage == pageCount) {
                subList = list.subList((currentPage - 1) * pageSize, totalCount);
            } else {
                subList = list.subList((currentPage - 1) * pageSize, pageSize * (currentPage)>totalCount?totalCount-1:pageSize * (currentPage));
            }
        }
        return subList;
    }
}
