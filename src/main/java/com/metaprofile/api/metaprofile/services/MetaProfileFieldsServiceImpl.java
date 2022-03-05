package com.metaprofile.api.metaprofile.services;

import com.metaprofile.api.core.LangType;
import com.metaprofile.api.metaprofile.exceptions.MetaProfileFieldNotFoundException;
import com.metaprofile.api.metaprofile.models.MetaProfileField;
import com.metaprofile.api.metaprofile.repositories.MetaProfileFieldsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MetaProfileFieldsServiceImpl implements MetaProfileFieldsService {

    private final MetaProfileFieldsRepository metaProfileFieldsRepository;

    public MetaProfileFieldsServiceImpl(MetaProfileFieldsRepository metaProfileFieldsRepository) {
        this.metaProfileFieldsRepository = metaProfileFieldsRepository;
    }


    @Override
    public List<MetaProfileField> getAll(LangType lang) {
        return metaProfileFieldsRepository.findAll()
                .stream()
                .map((it) -> (MetaProfileField)it.setLangType(lang))
                .collect(Collectors.toList());
    }

    @Override
    public List<MetaProfileField> getByMpcId(Long mpcId, LangType lang) {
        return metaProfileFieldsRepository.findAllByMpcId(mpcId);
    }

    @Override
    public MetaProfileField getByMpfId(Long mpfId, LangType langType) throws MetaProfileFieldNotFoundException {
        return metaProfileFieldsRepository.findById(mpfId).orElseThrow(MetaProfileFieldNotFoundException::new);
    }

    @Override
    public List<MetaProfileField> findByTitleQuery(String query, Long mpcId, LangType lang) {
        return metaProfileFieldsRepository.findByQuery(query, mpcId, lang.getRaw())
                .stream()
                .map((it) -> (MetaProfileField)it.setLangType(lang))
                .collect(Collectors.toList());
    }
}
