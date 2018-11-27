package com.ytdsuda.management.controller;

import com.ytdsuda.management.VO.ResultVO;
import com.ytdsuda.management.entity.DrugCategory;
import com.ytdsuda.management.repository.DrugCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private DrugCategoryRepository categoryRepository;
    @GetMapping("")
    public ResultVO getCategory() {
        ResultVO resultVO = new ResultVO();
        List<DrugCategory> drugCategoryList = categoryRepository.findAll();
        if (drugCategoryList.size() > 0) {
            resultVO.setData(drugCategoryList);
        } else {
            resultVO.setSuccess(false);
            resultVO.setErrorMsg("暂无数据");
        }
        return resultVO;
    }
    @PostMapping("add")
    public ResultVO addCategory(@RequestParam(value = "categoryName") String categoryName,
                                @RequestParam(value = "categoryDesc",required  = false) String categoryDesc) {
        ResultVO resultVO = new ResultVO();
        DrugCategory drugCategory = new DrugCategory();
        drugCategory.setCategoryName(categoryName);
        drugCategory.setCategoryDesc(categoryDesc);
        DrugCategory category = categoryRepository.save(drugCategory);
        if (category != null) {
            resultVO.setData(category);
        } else {
            resultVO.setErrorMsg("添加失败");
            resultVO.setSuccess(false);
        }
        return resultVO;
    }
}
