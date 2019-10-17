package com.knowably;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebcrawlerApplication {

	public static void main(String[] args) {

		SpringApplication.run(WebcrawlerApplication.class, args);
/*		WebCrawler webCrawler=new WebCrawler();
			webCrawler.getPagePara("https://en.wikipedia.org/wiki/Paracetamol");*/
//			webCrawler.getPageTitle("https://en.wikipedia.org/wiki/Paracetamol");


	}

}
