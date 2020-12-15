package com.minco.zhushou.utils;

import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/12/14 9:14
 */
public class IpUtils {

    private static volatile DbSearcher searcher;

    private static DbSearcher getInstall() throws Exception {
        if(searcher == null){
            String path = IpUtils.class.getClassLoader().getResource("static/ip2region.db").getPath();
            synchronized (DbSearcher.class) {
                if (searcher == null) {
                    searcher = new DbSearcher(new DbConfig(), path);
                }
            }
        }
        return searcher;
    }


    public static String getIpAddress(String ip) throws Exception {
        return getInstall().btreeSearch(ip).getRegion();
    }

}
