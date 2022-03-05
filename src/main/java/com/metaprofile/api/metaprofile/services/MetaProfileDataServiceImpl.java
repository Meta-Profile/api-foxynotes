package com.metaprofile.api.metaprofile.services;

import com.metaprofile.api.metaprofile.exceptions.*;
import com.metaprofile.api.metaprofile.models.MetaProfile;
import com.metaprofile.api.metaprofile.models.MetaProfileCategory;
import com.metaprofile.api.metaprofile.models.MetaProfileData;
import com.metaprofile.api.metaprofile.models.MetaProfileField;
import com.metaprofile.api.metaprofile.repositories.MetaProfileCategoriesRepository;
import com.metaprofile.api.metaprofile.repositories.MetaProfileDataRepository;
import com.metaprofile.api.metaprofile.repositories.MetaProfileFieldsRepository;
import com.metaprofile.api.metaprofile.repositories.MetaProfilesRepository;
import org.springframework.stereotype.Service;

@Service
public class MetaProfileDataServiceImpl implements MetaProfileDataService {
    private final MetaProfilesRepository metaProfilesRepository;
    private final MetaProfileCategoriesRepository metaProfileCategoriesRepository;
    private final MetaProfileFieldsRepository metaProfileFieldsRepository;
    private final MetaProfileDataRepository metaProfileDataRepository;

    public MetaProfileDataServiceImpl(MetaProfilesRepository metaProfilesRepository,
                                      MetaProfileCategoriesRepository metaProfileCategoriesRepository,
                                      MetaProfileFieldsRepository metaProfileFieldsRepository,
                                      MetaProfileDataRepository metaProfileDataRepository) {
        this.metaProfilesRepository = metaProfilesRepository;
        this.metaProfileCategoriesRepository = metaProfileCategoriesRepository;
        this.metaProfileFieldsRepository = metaProfileFieldsRepository;
        this.metaProfileDataRepository = metaProfileDataRepository;
    }

    /**
     * Возвращает данные
     *
     * @param mpdId
     * @return
     * @throws MetaProfileNotFoundException.DataException
     */
    @Override
    public MetaProfileData get(Long mpdId) throws MetaProfileNotFoundException.DataException {
        MetaProfileData metaProfileData = metaProfileDataRepository.findById(mpdId)
                .orElseThrow(() -> new MetaProfileNotFoundException.DataException(mpdId));
        if(metaProfileData.getStatus() < 1) throw new MetaProfileNotFoundException.DataException(mpdId);
        return metaProfileData;
    }

    /**
     * Удаление мета данных
     *
     * @param mpId
     * @param mpdId
     * @param authorId
     * @throws MetaProfileNotFoundException
     */
    @Override
    public Boolean remove(Long mpId, Long mpdId, Long authorId) throws MetaProfileNotFoundException {
        // Проверяем наличие профиля
        MetaProfile profile = metaProfilesRepository.findByMpId(mpId)
                .orElseThrow(() -> new MetaProfileNotFoundException(mpId));

        // Проверяем наличие данных в профиле
        if (!profile.hasData(mpdId)) throw new MetaProfileNotFoundException.DataException(mpdId);

        // Подучаем данные из репозитория
        MetaProfileData metaProfileData = this.get(mpdId);

        // Устанавливаем выход
        metaProfileData.setStatus(0);
        metaProfileDataRepository.save(metaProfileData);
        return true;
    }

    /**
     * Добавление мета данных
     *
     * @param mpId
     * @param category
     * @param field
     * @param data
     * @param authorId
     * @return
     * @throws MetaProfileFieldHasAlreadyInProfileException
     * @throws MetaProfileFieldNotInCategoryException
     */
    @Override
    public MetaProfileData add(Long mpId, MetaProfileCategory category, MetaProfileField field, Object data, Long authorId)
            throws MetaProfileFieldHasAlreadyInProfileException, MetaProfileFieldNotInCategoryException {
        // Проверяем наличие профиля
        MetaProfile profile = metaProfilesRepository.findByMpId(mpId)
                .orElseThrow(() -> new MetaProfileNotFoundException(mpId));

        // Проверяем соответствие поля и категории
        if (!field.getMpcId().equals(category.getMpcId()))
            throw new MetaProfileFieldNotInCategoryException(field.getMpfId(), category.getMpcId());

        // Проверяем наличие данных в профиле
        if (profile.hasField(field)) throw new MetaProfileFieldHasAlreadyInProfileException(field.getMpfId(), mpId);

        // Построение нового поля
        MetaProfileData metaProfileData = new MetaProfileData();
        metaProfileData.setData(data);
        metaProfileData.setMpId(mpId);
        metaProfileData.setCategory(category);
        metaProfileData.setField(field);
        metaProfileData.setAuthorId(authorId);

        return metaProfileDataRepository.save(metaProfileData);
    }

    @Override
    public MetaProfileData add(Long mpId, Long mpcId, Long mpfId, Object data, Long authorId)
            throws MetaProfileFieldHasAlreadyInProfileException, MetaProfileFieldNotInCategoryException,
            MetaProfileNotFoundException.FieldException, MetaProfileNotFoundException.CategoryException {
        // Поиск данных
        MetaProfileCategory category = metaProfileCategoriesRepository.findById(mpcId).orElseThrow(() -> new MetaProfileNotFoundException.CategoryException(mpcId));
        MetaProfileField filed = metaProfileFieldsRepository.findById(mpfId).orElseThrow(() -> new MetaProfileNotFoundException.FieldException(mpcId));

        // Выполнение логики кода
        return add(mpId, category, filed, data, authorId);
    }

    @Override
    public MetaProfileData update(Long mpId, Long mpdId, Object data, Long authorId)
            throws MetaProfileNotFoundException.DataException, MetaProfileDataChangeForbiddenException {
        // Проверяем наличие профиля
        MetaProfile profile = metaProfilesRepository.findByMpId(mpId)
                .orElseThrow(() -> new MetaProfileNotFoundException(mpId));

        // Проверяем наличие данных в профиле
        if (!profile.hasData(mpdId)) throw new MetaProfileNotFoundException.DataException(mpdId);

        // Подучаем данные из репозитория
        MetaProfileData metaProfileData = this.get(mpdId);

        // Выполнение обновления
        metaProfileData.setData(data);
        return metaProfileDataRepository.save(metaProfileData);
    }

    @Override
    public MetaProfileData move(Long mpId, Long mpdId, Integer position, Long authorId)
            throws MetaProfileNotFoundException.DataException, MetaProfileDataChangeForbiddenException {
        // Проверяем наличие профиля
        MetaProfile profile = metaProfilesRepository.findByMpId(mpId)
                .orElseThrow(() -> new MetaProfileNotFoundException(mpId));

        // Проверяем наличие данных в профиле
        if (!profile.hasData(mpdId)) throw new MetaProfileNotFoundException.DataException(mpdId);

        // Подучаем данные из репозитория
        MetaProfileData metaProfileData = this.get(mpdId);

        // Выполнение обновления
        metaProfileData.setPosition(position);
        return metaProfileDataRepository.save(metaProfileData);
    }
}
