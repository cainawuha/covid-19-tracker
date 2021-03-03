package com.visualization.epidemic.Mapper;


import com.visualization.epidemic.pojo.Hot;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface HotMapper {

    //保存
    void saveHot(Hot hot);

    List<Hot> findTopHot30();

}
