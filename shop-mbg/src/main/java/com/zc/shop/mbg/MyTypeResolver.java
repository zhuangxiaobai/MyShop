package com.zc.shop.mbg;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;
/*
   解决tinvint长度为1时候生成为boolean的问题
 */
public class MyTypeResolver extends JavaTypeResolverDefaultImpl {

    public MyTypeResolver() {
        super();
        typeMap.put(Types.TINYINT, new JavaTypeResolverDefaultImpl.JdbcTypeInformation("TINYINT", //$NON-NLS-1$
                new FullyQualifiedJavaType(Integer.class.getName())));
    }
}