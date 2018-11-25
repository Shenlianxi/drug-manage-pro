package com.ytdsuda.management.controller;

import com.ytdsuda.management.VO.ResultVO;
import com.ytdsuda.management.VO.StockVO;
import com.ytdsuda.management.dto.DrugDTO;
import com.ytdsuda.management.entity.DrugDetail;
import com.ytdsuda.management.entity.DrugMaster;
import com.ytdsuda.management.repository.DrugDetailRepository;
import com.ytdsuda.management.repository.DrugMasterRepository;
import com.ytdsuda.management.service.impl.DrugServiceImpl;
import com.ytdsuda.management.service.impl.TotalInfoServiceImpl;
import com.ytdsuda.management.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drug")
public class DrugController {
    @Autowired
    private DrugServiceImpl drugService;
    @Autowired
    private DrugMasterRepository drugMasterRepository;
    @Autowired
    private DrugDetailRepository drugDetailRepository;
    @Autowired
    private TotalInfoServiceImpl totalInfoService;
    @PostMapping("insert")
    public ResultVO insert(@RequestParam(value = "name") String name,
                           @RequestParam(value = "category") String category,
                           @RequestParam(value = "price") Float price,
                           @RequestParam(value = "amount") Integer amount,
                           @RequestParam(value = "resource") String resource,
                           @RequestParam(value = "function") String function,
                           @RequestParam(value = "icon") String icon) {
        ResultVO resultVO = new ResultVO();
        String drugId = KeyUtil.genUniqueKey();
        Optional<DrugMaster> drugMaster = drugMasterRepository.findByDrugName(name);
        if (drugMaster.isPresent()) {
            resultVO.setSuccess(false);
            resultVO.setErrorMsg("该药品已经存在");
        } else {
            DrugDTO drugDTO = new DrugDTO(drugId, name, category, new BigDecimal(price), amount, resource, function, icon);
            Boolean resultDTO = drugService.insertDrug(drugDTO);

            if (resultDTO == true) {
                resultVO.setSuccess(true);
                totalInfoService.updateTotalInfo("instock", amount);
            } else {
                resultVO.setSuccess(false);
                resultVO.setErrorMsg("添加药品失败");
            }
        }
        return resultVO;
    }
    /*
    * 修改信息(药品详情信息修改)
    * */
    @PostMapping("update")
    public ResultVO update (@RequestParam(value = "drugId") String drugId,
                            @RequestParam(value = "category", required = false) String category,
                            @RequestParam(value = "price", required = false) Float price,
                            @RequestParam(value = "resource", required = false) String resource,
                            @RequestParam(value = "drugFunction", required = false) String drugFunction,
                            @RequestParam(value = "drugIcon", required = false) String drugIcon) {
        ResultVO resultVO = new ResultVO();
        DrugDTO drugDTO = new DrugDTO();
        DrugDetail drugDetail = drugDetailRepository.findByDrugId(drugId);
        Integer detailId = drugDetail.getDetailId();
        Integer instock = drugDetail.getInstockCount();
        Integer outstock = drugDetail.getOutstockCount();
        DrugMaster drugMaster = drugMasterRepository.findByDrugId(drugId);
        BeanUtils.copyProperties(drugDetail, drugDTO);
        BeanUtils.copyProperties(drugMaster, drugDTO);
        if (category != null) {
            drugDTO.setDrugCategory(category);
        }
        if (price != null) {
            drugDTO.setDrugPrice(new BigDecimal(price));
        }
        if (resource != null) {
            drugDTO.setDrugResource(resource);
        }
        if (drugFunction != null) {
            drugDTO.setDrugIcon(drugFunction);
        }
        if (drugIcon != null) {
            drugDTO.setDrugIcon(drugIcon);
        }
        String resultId = drugService.updateDrug(drugDTO, detailId, instock, outstock);
        if (resultId != null) {
            resultVO.setSuccess(true);
            resultVO.setData(resultId);
            totalInfoService.updateTotalInfo(null, null);
        } else {
            resultVO.setSuccess(false);
            resultVO.setErrorMsg("更新失败");
        }
        return resultVO;
    }

    @PostMapping("query")
    public ResultVO query(@RequestParam(value = "drugId") String drugId) {
        ResultVO resultVO = new ResultVO();
        DrugDetail drugDetail = drugService.queryDetail(drugId);
        if (drugDetail != null) {
            resultVO.setData(drugDetail);
            totalInfoService.updateTotalInfo(null, null);
        } else {
            resultVO.setSuccess(false);
            resultVO.setErrorMsg("未知错误,请联系管理员");
        }
        return resultVO;
    }

    @DeleteMapping("delete/{drugId}")
    public ResultVO deleteOne(@PathVariable(value = "drugId") String drugId) {
        ResultVO resultVO = new ResultVO();
//        DrugMaster drugMaster = drugMasterRepository.findByDrugId(drugId);
        DrugDetail drugDetail = drugDetailRepository.findByDrugId(drugId);
        Integer detailId = drugDetail.getDetailId();
        if (detailId != null) {
            drugMasterRepository.deleteById(drugId);
            drugDetailRepository.deleteById(detailId);
            totalInfoService.updateTotalInfo(null, null);
        } else {
            resultVO.setSuccess(false);
            resultVO.setErrorMsg("删除失败");
        }
        return resultVO;
    }
    @DeleteMapping("delete")
    public ResultVO deleteMore(@RequestParam(value = "drugIds") List<String> drugIds) {
        ResultVO resultVO = new ResultVO();
        List<String> drugIdlist = drugService.deleteDrug(drugIds);
        if (drugIdlist != null) {
            resultVO.setData(drugIdlist);
            totalInfoService.updateTotalInfo(null, null);
        } else {
            resultVO.setSuccess(false);
            resultVO.setErrorMsg("删除失败");
        }
        return resultVO;
    }
    @GetMapping("allInfo")
    public ResultVO allInfo() {
        ResultVO resultVO = new ResultVO();
        List<DrugMaster> resultList = drugService.findAll();
        if (resultList.size() > 0) {
            resultVO.setData(resultList);
        } else {
            resultVO.setSuccess(false);
            resultVO.setErrorMsg("未知错误,请联系管理员");
        }
        return resultVO;
    }
    @PostMapping("stock")
    public ResultVO stock(@RequestParam(value = "drugId") String drugId,
                          @RequestParam(value = "count") Integer count,
                          @RequestParam(value = "type") Integer type) {
        /*1.入库, 2出库*/
        ResultVO resultVO = new ResultVO();
        StockVO stockVO = new StockVO();
        DrugDetail detailresult = drugService.stockManage(drugId, count, type);
        if (detailresult != null) {
            stockVO.setDrugName(detailresult.getDrugName());
            String info = type == 1 ? ("入库" + count) : ("出库" + count);
            stockVO.setStockInfo(info);
            resultVO.setData(stockVO);
        } else {
            resultVO.setSuccess(false);
            resultVO.setErrorMsg("库存不足,出库失败");
        }
        return resultVO;
    }
}
