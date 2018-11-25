package com.ytdsuda.management.service;

import com.ytdsuda.management.dto.DrugDTO;
import com.ytdsuda.management.entity.DrugDetail;
import com.ytdsuda.management.entity.DrugMaster;

import java.util.List;

public interface DrugService {
//    创建
    Boolean insertDrug(DrugDTO drugDTO);
//    更新,返回药品Id
    String updateDrug(DrugDTO drugDTO, Integer detailId, Integer instock, Integer outstock);
// 查询药品详细
    DrugDetail queryDetail(String drugId);
//    批量删除药品信息
List<String> deleteDrug(List<String> drugIdList);
//    查询所有的药品信息
List<DrugMaster> findAll();
//药品 入库,出库
    DrugDetail stockManage(String drugId, Integer count, Integer type);
}
