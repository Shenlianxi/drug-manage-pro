package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.dto.DrugDTO;
import com.ytdsuda.management.entity.DrugDetail;
import com.ytdsuda.management.entity.DrugMaster;
import com.ytdsuda.management.repository.DrugDetailRepository;
import com.ytdsuda.management.repository.DrugMasterRepository;
import com.ytdsuda.management.service.DrugMasterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DrugServiceImplTest {
    @Autowired
    private DrugMasterServiceImpl drugMasterService;
    @Autowired
    private DrugMasterRepository drugMasterRepository;
    @Autowired
    private DrugServiceImpl drugService;
    @Autowired
    private DrugDetailRepository detailRepository;

    @Test
    public void insertDrug() {
        DrugDTO drugDTO = new DrugDTO("9999999911", "阿莫西林", "冲剂", new BigDecimal(19.9), 56, "江南制造局", "治疗头痛", "没有图标");
        DrugMaster drugMaster = new DrugMaster();
        DrugDetail drugDetail = new DrugDetail();
        BeanUtils.copyProperties(drugDTO, drugDetail);
//        DrugMaster result = drugMasterRepository.save(drugMaster);
//        System.out.println(drugMaster);
//        DrugMaster result = drugMasterService.save(drugMaster);
        System.out.println(drugDetail);
    }

    @Test
    public void updateDrug() {
    }

    @Test
    public void queryDetail() {
        String id = "1542898673105254550";
        DrugDetail detail = drugService.queryDetail(id);
        System.out.println(detail);
    }

    @Test
    public void deleteDrug() {
    }

    @Test
    public void findAll() {
        List<DrugMaster> drugMasterList = drugMasterRepository.findAll();
        System.out.println(drugMasterList);
    }
    @Test
    public void deleteMore() {
        List<String> idsList = new ArrayList<>();
        idsList.add("1543042328322127");
        idsList.add("1543042331843629");
        List<DrugDetail> detailList = detailRepository.findByDrugIdIn(idsList);
        System.out.println(detailList);
//        if (detailList.size() > 0) {
//
//        }
        List<Integer> detailIdList = new ArrayList<>();
        for (DrugDetail element: detailList
             ) {
            detailIdList.add(element.getDetailId());
            detailRepository.deleteById(element.getDetailId());
            drugMasterRepository.deleteById(element.getDrugId());
        }
//        System.out.println(detailIdList);
    }
}