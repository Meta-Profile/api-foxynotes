package com.metaprofile.api.metaprofile.services;

import com.metaprofile.api.core.LangType;
import com.metaprofile.api.metaprofile.exceptions.MetaProfileFieldNotFoundException;
import com.metaprofile.api.metaprofile.models.MetaProfile;
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
}
