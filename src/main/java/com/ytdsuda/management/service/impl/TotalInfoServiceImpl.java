package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.entity.TotalInfo;
import com.ytdsuda.management.repository.TotalRepository;
import com.ytdsuda.management.service.TotalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TotalInfoServiceImpl implements TotalInfoService {
    @Autowired
    private TotalRepository repository;
    @Override
    public List<TotalInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public TotalInfo updateTotalInfo(String type, Integer count) {
        List<TotalInfo> temp = repository.findAll();
        TotalInfo info = temp.get(0);
        Integer in;
        if (type == "instock") {
            in = info.getTotalIncome() + count;
            info.setTotalIncome(in);
        }
        if (type == "outstock") {
            in = info.getTotalOutstock() + count;
            info.setTotalOutstock(in);
        }
        if (type == "sale") {
            in = info.getTotalSales() + count;
            info.setTotalSales(in);
        }
        Integer opCount = info.getTotalOperation() + 1;
        info.setTotalOperation(opCount);
        return repository.save(info);
    }

}
