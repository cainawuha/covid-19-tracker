package com.visualization.epidemic.Service.impl;

import com.visualization.epidemic.Mapper.HotMapper;
import com.visualization.epidemic.Service.HotService;
import com.visualization.epidemic.pojo.Hot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotServiceImpl implements HotService {

    @Autowired
    private HotMapper hotMapper;

    @Override
    public void saveHot(Hot hot) {
        this.hotMapper.saveHot(hot);
    }

    @Override
    public List<Hot> findTopHot30() {
        return this.hotMapper.findTopHot30();
    }


}
