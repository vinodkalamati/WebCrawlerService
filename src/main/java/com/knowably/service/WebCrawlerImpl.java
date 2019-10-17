package com.knowably.service;

import com.knowably.model.Input;
import com.knowably.model.WebCrawl;
import org.hibernate.validator.constraints.EAN;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class WebCrawlerImpl implements WebCrawler {
    private HashSet<String> links;
    private List<String> paras;
    private List<WebCrawl> results;
    private int count=0;

    public WebCrawlerImpl() {
        links = new HashSet<>();
        results=new ArrayList<>();
    }
@Override
    public List<WebCrawl> getContent(Input input) {

        String[] URLs=input.getUrl();
        while(URLs.length>count){
        String URL=URLs[count];
        if ((!links.contains(URL))) {
            System.out.println(" [" + URL + "]");
            try {
                links.add(URL);
                Document document = Jsoup.connect(URL).get();
                Elements paragraphs = document.select("p");
                paras = new ArrayList<>();
                for (Element para : paragraphs) {
                    System.out.println(para);
                    paras.add(para.toString());
                }
                count++;
                System.out.println(paras.size());
                WebCrawl webCrawl =new WebCrawl();
                webCrawl.setId(input.getId());
                webCrawl.setConcept(input.getConcept());
                webCrawl.setDomain(input.getDomain());
                webCrawl.setUrl(URL);
                webCrawl.setPayload(paras);
                results.add(webCrawl);
                System.out.println(results.toString());
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
        }

        return results;
    }


}



/*    public void getPageTitle(String URL) {
        if ((!links.contains(URL))) {
            System.out.println(" [" + URL + "]");
            try {
                links.add(URL);
                Document document = Jsoup.connect(URL).get();
                Elements titles = document.select("title");

                for (Element title : titles) {
                    System.out.println(title);
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }*/
