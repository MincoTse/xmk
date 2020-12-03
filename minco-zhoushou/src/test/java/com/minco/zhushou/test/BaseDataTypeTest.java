package com.minco.zhushou.test;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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
        System.out.println("当前"+stopWatch.elapsed());



    }
}
