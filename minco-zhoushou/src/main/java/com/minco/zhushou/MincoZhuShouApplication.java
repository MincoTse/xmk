package com.minco.zhushou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.xmk.bsf","com.minco"})
public class MincoZhuShouApplication {

	public static void main(String[] args) {
		SpringApplication.run(MincoZhuShouApplication.class, args);
	}

}
