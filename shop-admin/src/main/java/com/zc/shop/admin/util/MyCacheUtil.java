package com.zc.shop.admin.util;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.date.DateUnit;

public class MyCacheUtil {

    //默认缓存时长 单位s
    private static final Long DEFAULT_TIMEOUT = 5 * 60 * 1000L;
    //默认清理间隔时间 单位s
    //private static final Long CLEAN_TIMEOUT = 1 * 60 * 1000L;
    //缓存对象
    public static TimedCache<String, String> timedCache = CacheUtil.newTimedCache(DEFAULT_TIMEOUT);

 /*   static {
        //启动定时任务
        timedCache.schedulePrune(CLEAN_TIMEOUT);
    }*/

    public static void put(String key,String value) {
        timedCache.put(key, value);
    }

    public static void put(String key,String value,Integer expire) {
        timedCache.put(key, value, DateUnit.SECOND.getMillis() * expire);
    }

    /**
     * 禁止延迟缓存 isUpdateLastAccess = false ，true的意思就是在有效期以内被get了就再来一个过期周期
     * @param key
     * @param isUpdateLastAccess
     */
    public static String get(String key,boolean isUpdateLastAccess){
        return timedCache.get(key,isUpdateLastAccess);
    }

    public static String get(String key){
        return timedCache.get(key);
    }

    public static void remove(String key){
        timedCache.remove(key);
    }

    public static void clear(){
        timedCache.clear();
    }


}
