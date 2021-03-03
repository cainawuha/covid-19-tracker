package com.visualization.epidemic.pojo;

import org.springframework.stereotype.Component;

@Component
public class History {
    private Long id;
    private String update_time; //日期
    private Long cumulative_cases;//累计确诊
    private Long cumulative_recovered;//累计治愈
    private Long cumulative_deaths;//累计死亡

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public Long getCumulative_cases() {
        return cumulative_cases;
    }

    public void setCumulative_cases(Long cumulative_cases) {
        this.cumulative_cases = cumulative_cases;
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

    public History(Long id, String update_time, Long cumulative_cases, Long cumulative_recovered, Long cumulative_deaths) {
        this.id = id;
        this.update_time = update_time;
        this.cumulative_cases = cumulative_cases;
        this.cumulative_recovered = cumulative_recovered;
        this.cumulative_deaths = cumulative_deaths;
    }

    public History() {
    }
}
