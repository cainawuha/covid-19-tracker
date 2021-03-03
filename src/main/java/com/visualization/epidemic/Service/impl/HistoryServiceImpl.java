package com.visualization.epidemic.Service.impl;

import com.visualization.epidemic.Mapper.HistoryMapper;
import com.visualization.epidemic.Service.HistoryService;
import com.visualization.epidemic.pojo.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public void saveHistory(History history) {
        List<History> list = this.historyMapper.findHistory(history);
        if (list.size() == 0) {

            this.historyMapper.saveHistory(history);
        } else {
            this.historyMapper.updateHistory(history);
        }
    }

    @Override
    public void updateHistory(History history) {
        this.historyMapper.updateHistory(history);
    }

    @Override
    public List<History> findHistory(History history) {

        return this.historyMapper.findHistory(history);
    }

    @Override
    public List<History> findToday() {

        return this.historyMapper.findToday();
    }

    @Override
    public History findonlyToday() {
        return this.historyMapper.findonlyToday();
    }

}
