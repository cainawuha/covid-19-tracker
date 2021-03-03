package com.visualization.epidemic.Service.impl;

import com.visualization.epidemic.Mapper.DetailsMapper;
import com.visualization.epidemic.Service.DetailsService;
import com.visualization.epidemic.pojo.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class DetailsServiceImpl implements DetailsService {

    @Autowired
    private DetailsMapper detailsMapper;

    //查找并更新
    @Override
    public void saveDetails(Details details) {
        List<Details> list = this.findDetails(details);

        if (list.size() == 0) {
//            //没查到,新增
            this.detailsMapper.saveDetails(details);
        } else {
////            //查到了,修改
            this.detailsMapper.updateDetails(details);
        }
    }

    @Override
    public void updateDetails(Details details) {
        this.updateDetails(details);
    }

    @Override
    public Details findToday() {
        return this.detailsMapper.findToday();
    }

    @Override
    public List<Details> findDetails(Details details) {
        List<Details> list = this.detailsMapper.findDetails(details);
        return list;
    }

    @Override
    public List<String> findProvince() {
        List<String> list = this.detailsMapper.findProvince();
        return list;
    }

    @Override
    public List<Integer> findProvinceValue() {
        List<Integer> list = this.detailsMapper.findProvinceValue();
        return list;
    }

    public List<Details> findEachDayAdd() {
        List<Details> list = detailsMapper.findEachDayAdd();
        return list;
    }


}
