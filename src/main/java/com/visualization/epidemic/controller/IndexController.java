package com.visualization.epidemic.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.visualization.epidemic.Service.DetailsService;
import com.visualization.epidemic.Service.HistoryService;
import com.visualization.epidemic.Service.HotService;
import com.visualization.epidemic.pojo.Details;
import com.visualization.epidemic.pojo.History;
import com.visualization.epidemic.pojo.Hot;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Controller
public class IndexController {
    @Autowired
    HistoryService historyService;
    @Autowired
    HotService hotService;
    @Autowired
    DetailsService detailsService;


    @RequestMapping("/")
    public String index() throws IOException {
        return "index";
    }

    @RequestMapping("/time")
    @ResponseBody
    public String getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d,yyyy, HH:mm:ss aa");
        Date d = new Date();
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/Toronto"));
        String date = simpleDateFormat.format(d);
        return date;
    }

    @RequestMapping("/timeUpdate")
    @ResponseBody
    public String getTimeUpdate() {
        Date d = new Date();
        d.setDate(d.getDate() + 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d,yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/Toronto"));
        String date = simpleDateFormat.format(d);
        return "next update: " + date;
    }

    @RequestMapping("/c1")
    @ResponseBody
    public JSONObject getDateC1() throws JSONException {
        History today = this.historyService.findonlyToday();
        JSONObject json = new JSONObject();
        json.put("cases_today", today.getCumulative_cases());
        json.put("recovered_today", today.getCumulative_recovered());
        json.put("deaths_today", today.getCumulative_deaths());
        return json;
    }

    @RequestMapping("/r2")
    @ResponseBody
    public JSONArray r2() throws JSONException {
        //获取hot前20
        int i = 0;
        List<Hot> topHot30 = this.hotService.findTopHot30();
        //jieba分词
        //JiebaSegmenter segmenter = new JiebaSegmenter();
        JSONArray list = new JSONArray();
        for (Hot hot : topHot30) {
            i++;
            String content = hot.getContent();
            //获取数字
            String num = content.replaceAll("[\\D]", "");
            //获取中文
            String msg = content.replaceAll("[0-9]", "");
            //分词i
            StringTokenizer st = new StringTokenizer(msg, " ,?.!:\"'\n#");
            List<String> wordList = new ArrayList<>();
            while (st.hasMoreElements()) {
                wordList.add(st.nextToken().toLowerCase());
            }
            //List<String> strings = segmenter.sentenceProcess(msg);
            for (String string : wordList) {
                Pattern pattern = Pattern.compile("[0-9]*");
                if (!pattern.matcher(string).matches())//不为数字
                {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", string);
                    jsonObject.put("value", i);
                    list.add(jsonObject);
                }
            }
        }
        return list;
    }


    @RequestMapping("/c2")
    @ResponseBody
    public JSONArray c2() throws JSONException {
        JSONArray list = new JSONArray();
        List<String> province = this.detailsService.findProvince();
        List<Integer> provinceValue = this.detailsService.findProvinceValue();
        for (int i = 0; i < province.size(); i++) {
            JSONObject js = new JSONObject();
            js.put("name", province.get(i));
            js.put("value", provinceValue.get(i));
            list.add(js);
        }
        return list;

    }

    @RequestMapping("/l1")
    @ResponseBody
    public JSONObject l1() throws JSONException {
        List<History> eachDayTotal = this.historyService.findToday();
        JSONObject json = new JSONObject();
        List<String> Dslist = new ArrayList<>();
        List<Long> Cumulative_caseslist = new ArrayList<>();
        List<Long> Cumulative_recoveredlist = new ArrayList<>();
        List<Long> Cumulative_deathslist = new ArrayList<>();

        for (History history : eachDayTotal) {
            Dslist.add(history.getUpdate_time());
            Cumulative_caseslist.add(history.getCumulative_cases());
            Cumulative_recoveredlist.add(history.getCumulative_recovered());
            Cumulative_deathslist.add(history.getCumulative_deaths());
        }
        json.put("update_time", Dslist);
        json.put("cases", Cumulative_caseslist);
        json.put("recovered", Cumulative_recoveredlist);
        json.put("deaths", Cumulative_deathslist);
        return json;
    }


    @RequestMapping("/l2")
    @ResponseBody
    public JSONObject l2() throws JSONException {
        List<Details> eachDayAdd = detailsService.findEachDayAdd();
        JSONObject json = new JSONObject();
        List<String> Dslist = new ArrayList<>();
        List<Long> Active_cases_changelist = new ArrayList<>();
        List<Long> Recoveredlist = new ArrayList<>();
        List<Long> Dead_addlist = new ArrayList<>();

        for (Details details : eachDayAdd) {
            Dslist.add(details.getDs());
            Active_cases_changelist.add(details.getActive_cases_change());
            Dead_addlist.add(details.getDead_add());
            Recoveredlist.add(details.getRecovered_add());
        }

        json.put("day", Dslist);
        json.put("Active_cases_change", Active_cases_changelist);
        json.put("Recovered_change", Recoveredlist);
        json.put("Dead_add", Dead_addlist);
        return json;
    }

//    @RequestMapping("/r1")
//    @ResponseBody
//    public JSONObject r1() throws JSONException {
//        JSONObject json = new JSONObject();
//        List<String> city = this.detailsService.findCity();
//        List<Long> cityValue = this.detailsService.findCityValue();
//        json.put("city",city);
//        json.put("cityValue",cityValue);
//        return json;
//    }

}


















