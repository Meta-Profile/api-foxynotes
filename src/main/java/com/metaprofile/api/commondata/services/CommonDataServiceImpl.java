package com.metaprofile.api.commondata.services;

import com.metaprofile.api.commondata.models.CommonData;
import com.metaprofile.api.commondata.repositoroes.CommonDataRepository;
import com.metaprofile.api.metaprofile.exceptions.MetaProfileNotFoundException;
import com.metaprofile.api.metaprofile.models.MetaProfileField;
import com.metaprofile.api.metaprofile.repositories.MetaProfileFieldsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonDataServiceImpl implements CommonDataService {
    private final CommonDataRepository commonDataRepository;
    private final MetaProfileFieldsRepository metaProfileFieldsRepository;

    public CommonDataServiceImpl(CommonDataRepository commonDataRepository, MetaProfileFieldsRepository metaProfileFieldsRepository) {
        this.commonDataRepository = commonDataRepository;
        this.metaProfileFieldsRepository = metaProfileFieldsRepository;
    }

    @Override
    public List<CommonData> search(String query, Long mpfId) {
        return commonDataRepository.findByQuery(query, mpfId);
    }

    @Override
    public CommonData add(String value, MetaProfileField field, Long authorId) {
        CommonData commonData = new CommonData();
        commonData.setTitle(value);
        commonData.setAuthorId(authorId);
        commonData.setField(field);
        return commonDataRepository.save(commonData);
    }

    @Override
    public CommonData add(String value, Long mpfId, Long authorId) {
        MetaProfileField metaProfileField = metaProfileFieldsRepository.findById(mpfId)
                .orElseThrow(() -> new MetaProfileNotFoundException.FieldException(mpfId));
        return add(value, metaProfileField, authorId);
    }

    @Override
    public Boolean exists(String value, Long mpfId) {
        return commonDataRepository.existsByTitle(value);
    }
}
