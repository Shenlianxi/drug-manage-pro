package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.dto.DrugDTO;
import com.ytdsuda.management.entity.DrugDetail;
import com.ytdsuda.management.entity.DrugMaster;
import com.ytdsuda.management.repository.DrugDetailRepository;
import com.ytdsuda.management.repository.DrugMasterRepository;
import com.ytdsuda.management.service.DrugService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DrugServiceImpl implements DrugService {

    @Autowired
    private DrugMasterRepository drugMasterRepository;
    @Autowired
    private DrugDetailRepository drugDetailRepository;
    @Autowired
    private TotalInfoServiceImpl totalInfoService;
    @Override
    public Boolean insertDrug(DrugDTO drugDTO) {
        DrugMaster drugMaster = new DrugMaster();
        BeanUtils.copyProperties(drugDTO, drugMaster);
        DrugMaster resultMaster = drugMasterRepository.save(drugMaster);
        DrugDetail drugDetail = new DrugDetail();
        BeanUtils.copyProperties(drugDTO, drugDetail);
        drugDetail.setInstockCount(drugDTO.getDrugAmount());
        drugDetail.setOutstockCount(0);
        DrugDetail resultDetail = drugDetailRepository.save(drugDetail);
        if (resultMaster.getDrugId() != null && resultDetail.getDetailId() != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String updateDrug(DrugDTO drugDTO, Integer detailId, Integer instock, Integer outstock) {
        DrugMaster drugMaster = new DrugMaster();
        BeanUtils.copyProperties(drugDTO, drugMaster);
        DrugMaster resultMaster = drugMasterRepository.save(drugMaster);
        DrugDetail drugDetail = new DrugDetail();
        BeanUtils.copyProperties(drugDTO, drugDetail);
        drugDetail.setDetailId(detailId);
        drugDetail.setInstockCount(instock);
        drugDetail.setOutstockCount(outstock);
        DrugDetail resultDetail = drugDetailRepository.save(drugDetail);
        if (resultMaster.getDrugId() != null && resultDetail.getDetailId() != null) {
            return drugDTO.getDrugId();
        } else {
            return null;
        }
    }

    @Override
    public DrugDetail queryDetail(String drugId) {
        DrugDetail detail = drugDetailRepository.findByDrugId(drugId);
        return detail;
    }

    @Override
    public List<String> deleteDrug(List<String> drugIdList) {
        /*批量删除根据uids*/
        List<DrugDetail> detailList = drugDetailRepository.findByDrugIdIn(drugIdList);
        if (detailList.size() > 0) {
            for (DrugDetail element: detailList
                    ) {
                drugDetailRepository.deleteById(element.getDetailId());
                drugMasterRepository.deleteById(element.getDrugId());
            }
            return drugIdList;
        } else {
            return null;
        }
    }


    @Override
    public List<DrugMaster> findAll() {
        List<DrugMaster> drugMasterList = drugMasterRepository.findAll();
        return drugMasterList;
    }

    @Override
    public DrugDetail stockManage(String drugId, Integer count, Integer type) {
        DrugDetail drugDetail = drugDetailRepository.findByDrugId(drugId);
        DrugMaster drugMaster = drugMasterRepository.findByDrugId(drugId);
        Integer instock = drugDetail.getInstockCount();
        Integer amount = drugMaster.getDrugAmount();
        Integer outstock = drugDetail.getOutstockCount();
        /*1.入库; 2.出库*/
        if (type == 1) {
            drugDetail.setInstockCount(instock + count);
            drugMaster.setDrugAmount(amount + count);
            totalInfoService.updateTotalInfo("instock", count);
        } else {
            if (count > amount) {
                return null;
            } else {
                drugDetail.setOutstockCount(outstock + count);
                drugMaster.setDrugAmount(amount - count);
                totalInfoService.updateTotalInfo("outstock", count);
            }
        }
        DrugDetail resultDetail = drugDetailRepository.save(drugDetail);
        drugMasterRepository.save(drugMaster);
        return resultDetail;
    }
}
