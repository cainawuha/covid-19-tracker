package com.visualization.epidemic.pojo;

import org.springframework.stereotype.Component;

@Component
public class Details {
    private Long id;
    private String ds; //日期
    private String province;
    private Long cumulative_cases;//累计确诊
    private Long active_cases_change;//今日累计确诊
    private Long recovered_add;//当日新增治愈
    private Long cumulative_recovered;//累计治愈
    private Long cumulative_deaths;//累计死亡
    private Long dead_add;//今日新增死亡

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Long getCumulative_cases() {
        return cumulative_cases;
    }

    public void setCumulative_cases(Long cumulative_cases) {
        this.cumulative_cases = cumulative_cases;
    }

    public Long getActive_cases_change() {
        return active_cases_change;
    }

    public void setActive_cases_change(Long active_cases_change) {
        this.active_cases_change = active_cases_change;
    }

    public Long getRecovered_add() {
        return recovered_add;
    }

    public void setRecovered_add(Long recovered_add) {
        this.recovered_add = recovered_add;
    }

    public Long getCumulative_recovered() {
        return cumulative_recovered;
    }

    public void setCumulative_recovered(Long cumulative_recovered) {
        this.cumulative_recovered = cumulative_recovered;
    }

    public Long getCumulative_deaths() {
        return cumulative_deaths;
    }

    public void setCumulative_deaths(Long cumulative_deaths) {
        this.cumulative_deaths = cumulative_deaths;
    }

    public Long getDead_add() {
        return dead_add;
    }

    public void setDead_add(Long dead_add) {
        this.dead_add = dead_add;
    }

    public Details() {
    }

    public Details(Long id, String ds, String province, Long cumulative_cases, Long active_cases_change, Long recovered_add, Long cumulative_recovered, Long cumulative_deaths, Long dead_add) {
        this.id = id;
        this.ds = ds;
        this.province = province;
        this.cumulative_cases = cumulative_cases;
        this.active_cases_change = active_cases_change;
        this.recovered_add = recovered_add;
        this.cumulative_recovered = cumulative_recovered;
        this.cumulative_deaths = cumulative_deaths;
        this.dead_add = dead_add;
    }
}