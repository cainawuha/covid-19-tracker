package com.visualization.epidemic.Task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.visualization.epidemic.pojo.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

import java.util.UUID;


@Component
public class DetailsProcessor implements PageProcessor {
    @Autowired
    private DetailsProcessor detailsProcessor;
    @Autowired
    private Details details;
    @Autowired
    private DetailsSpringDataPipeline detailsSpringDataPipeline;

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setCharset("UTF-8");

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {

        saveHistory(page);
    }

    private void saveHistory(Page page) {
        Json json = page.getJson();
        JSONObject jsonObject = JSON.parseObject(String.valueOf(json));
        //获取key为summary的json对象
        JSONArray summary = jsonObject.getJSONArray("summary");

        for (int ProvinceNum = 0; ProvinceNum < summary.size(); ProvinceNum++) {
            details = new Details();
            Object province;
            JSONObject ProvinceInfo = summary.getJSONObject(ProvinceNum);
            Object dateTime = ProvinceInfo.get("date");
            if (ProvinceInfo.get("province").equals("NL")) {
                province = "Newfoundland and Labrador";
            } else if (ProvinceInfo.get("province").equals("BC")) {
                province = "British Columbia";
            } else if (ProvinceInfo.get("province").equals("NWT")) {
                province = "Northwest Territories";
            } else {
                province = ProvinceInfo.get("province");
            }
            Object active_cases_change = ProvinceInfo.get("active_cases_change");//当日新增确诊
            Object recovered = ProvinceInfo.get("recovered");//当日新增治愈
            Object death_add = ProvinceInfo.get("deaths");//当日新增死亡
            Object cumulative_cases = ProvinceInfo.get("cumulative_cases");//当日累计确诊
            Object cumulative_recovered = ProvinceInfo.get("cumulative_recovered");//当日累计治愈
            Object cumulative_deaths = ProvinceInfo.get("cumulative_deaths");//当日累计死亡

            Long recovered1 = Float.valueOf(recovered.toString()).longValue();
            Long death_add1 = Float.valueOf(death_add.toString()).longValue();
            Long cumulative_recovered1 = Float.valueOf(cumulative_recovered.toString()).longValue();
            Long cumulative_deaths1 = Float.valueOf(String.valueOf(cumulative_deaths)).longValue();
            Long active_cases_change1 = Long.valueOf(String.valueOf(active_cases_change));
            Long cumulative_cases1 = Long.valueOf(String.valueOf(cumulative_cases));

            details.setActive_cases_change(active_cases_change1);
            details.setRecovered_add(recovered1);
            details.setDead_add(death_add1);
            details.setCumulative_cases(cumulative_cases1);
            details.setCumulative_recovered(cumulative_recovered1);
            details.setCumulative_deaths(cumulative_deaths1);
            details.setDs(String.valueOf(dateTime));
            details.setProvince(String.valueOf(province));

            //保存结果
            UUID uuid = UUID.randomUUID();
            page.putField("details" + uuid, details);
        }

    }

    @Scheduled(initialDelay = 1000, fixedDelay = 24 * 60 * 60 * 1000)
    public void process() {
        Spider.create(detailsProcessor)
                .addUrl("https://api.opencovid.ca/summary")
                //.setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(1000000)))
                .thread(1)
                .addPipeline(detailsSpringDataPipeline)
                .run();
    }
}