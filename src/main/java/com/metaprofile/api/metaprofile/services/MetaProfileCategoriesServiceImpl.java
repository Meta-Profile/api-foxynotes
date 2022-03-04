package com.metaprofile.api.metaprofile.services;

import com.metaprofile.api.core.LangType;
import com.metaprofile.api.metaprofile.exceptions.MetaProfileCategoryNotFoundException;
import com.metaprofile.api.metaprofile.models.MetaProfileCategory;
import com.metaprofile.api.metaprofile.repositories.MetaProfileCategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MetaProfileCategoriesServiceImpl implements MetaProfileCategoriesService {

    private final MetaProfileCategoriesRepository metaProfileCategoriesRepository;

    public MetaProfileCategoriesServiceImpl(MetaProfileCategoriesRepository metaProfileCategoriesRepository) {
        this.metaProfileCategoriesRepository = metaProfileCategoriesRepository;
    }

    @Override
    public List<MetaProfileCategory> getAll(LangType lang) {
        return metaProfileCategoriesRepository.findAll()
                .stream()
                .map((it) -> (MetaProfileCategory)it.setLangType(lang))
                .collect(Collectors.toList());
    }

    @Override
    public MetaProfileCategory getByMpcId(Long mpcId) throws MetaProfileCategoryNotFoundException {
        return metaProfileCategoriesRepository.findById(mpcId).orElseThrow(MetaProfileCategoryNotFoundException::new);
    }

    @Override
    public List<MetaProfileCategory> findByTitleQuery(String query, LangType lang) {
        return metaProfileCategoriesRepository.findByQuery(query, lang.getRaw())
                .stream()
                .map((it) -> (MetaProfileCategory)it.setLangType(lang))
                .collect(Collectors.toList());
    }
}
