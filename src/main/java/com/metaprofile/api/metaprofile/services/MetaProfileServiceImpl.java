package com.metaprofile.api.metaprofile.services;

import com.metaprofile.api.core.LangType;
import com.metaprofile.api.metaprofile.exceptions.MetaProfileFieldNotFoundException;
import com.metaprofile.api.metaprofile.models.MetaProfile;
import com.metaprofile.api.metaprofile.models.MetaProfileCategory;
import com.metaprofile.api.metaprofile.models.MetaProfileData;
import com.metaprofile.api.metaprofile.models.MetaProfileField;
import com.metaprofile.api.metaprofile.repositories.MetaProfileDataRepository;
import com.metaprofile.api.metaprofile.repositories.MetaProfilesRepository;
import org.springframework.stereotype.Service;

@Service
public class MetaProfileServiceImpl implements MetaProfileService {
    private final MetaProfilesRepository metaProfilesRepository;
    private final MetaProfileDataRepository metaProfileDataRepository;

    public MetaProfileServiceImpl(MetaProfilesRepository metaProfilesRepository, MetaProfileDataRepository metaProfileDataRepository) {
        this.metaProfilesRepository = metaProfilesRepository;
        this.metaProfileDataRepository = metaProfileDataRepository;
    }

    @Override
    public MetaProfile getProfileById(Long id, LangType lang) throws MetaProfileFieldNotFoundException {
        return metaProfilesRepository.findById(id).map(it -> (MetaProfile) it.setLangType(lang)).orElseThrow(MetaProfileFieldNotFoundException::new);
    }

    @Override
    public MetaProfileData addMetaData(Long mpId, MetaProfileCategory category, MetaProfileField field, Object data, Long authorId) {
        MetaProfileData metaProfileData = new MetaProfileData();
        metaProfileData.setMpId(mpId);
        metaProfileData.setCategory(category);
        metaProfileData.setField(field);
        metaProfileData.setData(data);
        metaProfileData.setAuthorId(authorId);
        return metaProfileDataRepository.save(metaProfileData);
    }

    @Override
    public Boolean hasAdded(Long mpId, Long mpfId) {
        return metaProfileDataRepository.findAllByMpId(mpId, mpfId).size() > 0;
    }
}
