package com.visualization.epidemic.Mapper;

import com.visualization.epidemic.pojo.History;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface HistoryMapper {

    //保存
    void saveHistory(History history);

    //更新
    void updateHistory(History history);

    //查找 日期相同的
    List<History> findHistory(History history);

    //查找今日数据
    List<History> findToday();

    History findonlyToday();


}
