package com.zc.shop.admin.dto;

import lombok.Data;

@Data
public class EmployeesSelectParam {


    private  String name;

    private Integer c_id;

    private PageParam pageParam;

}
