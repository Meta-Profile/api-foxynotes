package com.metaprofile.api.metaprofile.services;

import com.metaprofile.api.core.LangType;
import com.metaprofile.api.metaprofile.exceptions.MetaProfileNotFoundException;
import com.metaprofile.api.metaprofile.models.MetaProfile;
import com.metaprofile.api.metaprofile.repositories.MetaProfilesRepository;
import org.springframework.stereotype.Service;

@Service
public class MetaProfileServiceImpl implements MetaProfileService {
    private final MetaProfilesRepository metaProfilesRepository;

    public MetaProfileServiceImpl(MetaProfilesRepository metaProfilesRepository) {
        this.metaProfilesRepository = metaProfilesRepository;
    }

    @Override
    public MetaProfile getProfileById(Long id, LangType lang) throws MetaProfileNotFoundException {
        return metaProfilesRepository.findById(id).map(it -> (MetaProfile) it.setLangType(lang)).orElseThrow(() -> new MetaProfileNotFoundException(id));
    }

}
