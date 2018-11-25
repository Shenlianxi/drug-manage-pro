package com.ytdsuda.management.service.impl;

import com.ytdsuda.management.entity.DrugMaster;
import com.ytdsuda.management.repository.DrugMasterRepository;
import com.ytdsuda.management.service.DrugMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrugMasterServiceImpl implements DrugMasterService {
    @Autowired
    private DrugMasterRepository repository;
    @Override
    public DrugMaster save(DrugMaster drugMaster) {
        return repository.save(drugMaster);
    }
}
