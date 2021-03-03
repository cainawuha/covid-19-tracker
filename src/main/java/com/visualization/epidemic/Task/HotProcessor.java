package com.visualization.epidemic.Task;


import com.visualization.epidemic.controller.HttpClientDownloader;
import com.visualization.epidemic.pojo.Hot;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.util.List;
import java.util.UUID;

@Component
public class HotProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        List<String> commentList = page.getHtml().xpath("//*[@id=\"trends\"]/table[1]/tbody//td[1]/a/text()").all();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (String comment : commentList) {
            int i = 0;
            Hot hot = new Hot();
            //Html commentHtml = Html.create(comment);
            hot.setId((long) i);
            hot.setContent(comment);
            i++;

            UUID uuid = UUID.randomUUID();
            page.putField("hot" + uuid, hot);
        }
        String nextPageUrl = page.getHtml().xpath("//*[@id=\"trends\"]/div/a").links().get();
        if (StringUtils.isNotBlank(nextPageUrl)) {
            // 将下一页地址存入 page 这样才会继续爬取
            page.addTargetRequest(nextPageUrl);
        }
    }

    private Site site = Site.me()
            .setCharset("utf-8")//设置编码
            .setTimeOut(10 * 1000)//超时时间
            .setRetrySleepTime(3000)//重试的间隔时间
            .setRetryTimes(3);//重试的次数

    @Override
    public Site getSite() {
        return site;
    }

    @Autowired
    private HotSpringDataPipeline newsSpringDataPipeline;

    @Scheduled(initialDelay = 1, fixedDelay = 12 * 60 * 60 * 1000)
    public void process() {
        Spider.create(new HotProcessor())
                .setDownloader(new HttpClientDownloader())
                .addUrl("https://getdaytrends.com/united-states/")
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(1000000)))
                .thread(10)
                .addPipeline(newsSpringDataPipeline)
                .run();
    }
}