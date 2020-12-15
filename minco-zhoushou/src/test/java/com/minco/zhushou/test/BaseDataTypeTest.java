package com.minco.zhushou.test;

import com.google.common.base.Stopwatch;
import com.minco.zhushou.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * 统计局数据
 * http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/index.html
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/20 14:29
 */
@Slf4j
public class BaseDataTypeTest {

    @Test
    public void test1() throws Exception {

        Stopwatch stopWatch = Stopwatch.createStarted();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("当前"+stopWatch.elapsed());
        stopWatch.reset();
        System.out.println("当前"+stopWatch.elapsed());
        TimeUnit.MILLISECONDS.sleep(500);
        //国家|区域|省份|城市|ISP_
        System.out.println(IpUtils.getIpAddress("56.23.52.41"));
        System.out.println("当前"+stopWatch.elapsed());


        Class<BaseDataTypeTest> baseDataTypeTestClass = BaseDataTypeTest.class;
        Class<IpUtils> IpUtilsaaa = IpUtils.class;


        String path = baseDataTypeTestClass.getClassLoader().getResource("").getPath();
        System.out.println(path);
        log.debug(path);

        String path1 = IpUtilsaaa.getClassLoader().getResource("").getPath();
        System.out.println(path1);
        log.info(path1);

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader.getResource("application.yml").getPath());

        // 获取工程路径
        String sp1 = System.getProperty("user.dir");
        System.out.println("sp1 = " + sp1);


    }
}
