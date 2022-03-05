package com.metaprofile.api.metaprofile.services;

import com.metaprofile.api.core.LangType;
import com.metaprofile.api.metaprofile.exceptions.MetaProfileNotFoundException;
import com.metaprofile.api.metaprofile.models.MetaProfile;
import com.metaprofile.api.metaprofile.models.MetaProfileType;
import com.metaprofile.api.metaprofile.repositories.MetaProfileTypesRepository;
import com.metaprofile.api.metaprofile.repositories.MetaProfilesRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class MetaProfileServiceImpl implements MetaProfileService {
    private final MetaProfilesRepository metaProfilesRepository;
    private final MetaProfileTypesRepository metaProfileTypesRepository;

    public MetaProfileServiceImpl(MetaProfilesRepository metaProfilesRepository, MetaProfileTypesRepository metaProfileTypesRepository) {
        this.metaProfilesRepository = metaProfilesRepository;
        this.metaProfileTypesRepository = metaProfileTypesRepository;
    }

    @Override
    public MetaProfile getProfileById(Long id, LangType lang) throws MetaProfileNotFoundException {
        MetaProfile metaProfile = metaProfilesRepository.findById(id).map(it -> (MetaProfile) it.setLangType(lang)).orElseThrow(() -> new MetaProfileNotFoundException(id));
        if(metaProfile.getStatus() < 1) throw new MetaProfileNotFoundException(id);
        return metaProfile;
    }

    /**
     * Создает мета профиль
     *
     * @param title
     * @param authorId
     * @return
     */
    @Override
    public MetaProfile create(String title, Long authorId) {
        MetaProfileType metaProfileType = metaProfileTypesRepository.getById(1L);
        MetaProfile metaProfile = new MetaProfile();
        metaProfile.setTitle(title);
        metaProfile.setAuthorId(authorId);
        metaProfile.setColor("#a05636");
        metaProfile.setType(metaProfileType);
        metaProfile.setData(new HashSet<>());
        return (MetaProfile) metaProfilesRepository.save(metaProfile).setLangType(LangType.ru);
    }

    @Override
    public Boolean remove(Long mpId, Long authorId) {
        MetaProfile metaProfile = getProfileById(mpId, LangType.ru);
        metaProfile.setStatus(0);
        metaProfilesRepository.save(metaProfile);
        return true;
    }
}
