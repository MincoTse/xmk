package com.minco.zhushou;

import com.google.common.base.Stopwatch;
import com.minco.zhushou.entity.DbKeyWord;
import com.minco.zhushou.service.DbKeyWordService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
class MincoZhuShouApplicationTests {

	@Resource
	private DbKeyWordService dbKeyWordService;

	@Test
	public void test1() throws IOException {
		Stopwatch w = Stopwatch.createStarted();

		Document doc = Jsoup.connect("https://dev.mysql.com/doc/refman/8.0/en/keywords.html").get();
		Elements newsHeadlines = doc.select(".simplesect .itemizedlist .listitem");


		List<DbKeyWord> result = new ArrayList<>();
		for (Element headline : newsHeadlines) {
			String nodeText = headline.text();
			DbKeyWord dbKeyWord = new DbKeyWord().setKeyWord(nodeText).setLowKeyWord(nodeText.toLowerCase());
			result.add(dbKeyWord);
		}
		dbKeyWordService.saveBatch(result);

		log.info("执行耗时时间"+w.stop());
		log.info("执行耗时时间"+w.elapsed());



	}

	/**
	 * 推证框架是否有缓存
	 */
	@Test
	public void  test2(){
		Stopwatch started = Stopwatch.createStarted();
		List<DbKeyWord> list = dbKeyWordService.listByIds(Arrays.asList(1333658876804063234L));
		started.stop();
		System.out.println("第一次执行耗时 "+started.toString());
		started.reset().start();
		List<DbKeyWord> list2 = dbKeyWordService.listByIds(Arrays.asList(1333658880960618497L));
		System.out.println("第二次执行耗时 "+started.stop());
		started.reset().start();
		List<DbKeyWord> list3 = dbKeyWordService.listByIds(Arrays.asList(1333658880960618497L));
		System.out.println("第三次执行耗时 "+started.stop() + list3);
		started.reset().start();
	}




}
