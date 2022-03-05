package com.metaprofile.api.metaprofile.controllers;

import com.metaprofile.api.core.ControllerResponse;
import com.metaprofile.api.core.LangType;
import com.metaprofile.api.metaprofile.models.MetaProfile;
import com.metaprofile.api.metaprofile.models.MetaProfileCategory;
import com.metaprofile.api.metaprofile.models.MetaProfileField;
import com.metaprofile.api.metaprofile.services.MetaProfileCategoriesService;
import com.metaprofile.api.metaprofile.services.MetaProfileFieldsService;
import com.metaprofile.api.metaprofile.services.MetaProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Meta Profile", description = "Создание и редактирование мета профилей, а также получения необходимых данных")
@RestController
@RequestMapping(value = "/v1/mp")
public class MetaProfileController {

    private final MetaProfileCategoriesService metaProfileCategoriesService;
    private final MetaProfileFieldsService metaProfileFieldsService;
    private final MetaProfileService metaProfileService;

    public MetaProfileController(MetaProfileCategoriesService metaProfileCategoriesService, MetaProfileFieldsService metaProfileFieldsService, MetaProfileService metaProfileService) {
        this.metaProfileCategoriesService = metaProfileCategoriesService;
        this.metaProfileFieldsService = metaProfileFieldsService;
        this.metaProfileService = metaProfileService;
    }

    @Operation(
            summary = "Возвращает список категорий мета профиля или производит поиск по ним",
            description = "Поиск производится при передаче опционального параметра 'q'"
    )
    @GetMapping("/categories")
    public ResponseEntity<ControllerResponse<List<MetaProfileCategory>>> getCategories(
            @RequestParam(name = "q", required = false)
            @Parameter(required = false, description = "Поисковой запрос - данное поле не обязательное")
                    String q,
            @RequestHeader(name = "Lang", defaultValue = "ru")
            @Parameter(required = false)
            @Schema(allowableValues = {"ru", "en"})
                    String lang
    ) {
        LangType langType = LangType.raw(lang);

        if (q != null)
            return ControllerResponse.ok(metaProfileCategoriesService.findByTitleQuery(q, langType))
                    .response();
        return ControllerResponse.ok(metaProfileCategoriesService.getAll(langType))
                .response();
    }

    @Operation(
            summary = "Возвращает список сигнатур полей мета профиля или производит поиск по ним",
            description = "Поиск производится при передаче опционального параметра 'q'"
    )
    @GetMapping("/fields")
    public ResponseEntity<ControllerResponse<List<MetaProfileField>>> getFields(
            @RequestParam(name = "q", required = false)
            @Parameter(required = false, description = "Поисковой запрос - данное поле не обязательное")
                    String q,
            @RequestParam(name = "mpcId", required = true)
            @Parameter(required = true, description = "Идентификатор категории по которой пойдет поиск")
                    Long mpcId,
            @RequestHeader(name = "Lang", defaultValue = "ru")
            @Parameter(required = false)
            @Schema(allowableValues = {"ru", "en"})
                    String lang
    ) {
        LangType langType = LangType.raw(lang);

        if (q != null)
            return ControllerResponse.ok(metaProfileFieldsService.findByTitleQuery(q, mpcId, langType))
                    .response();
        return ControllerResponse.ok(metaProfileFieldsService.getByMpcId(mpcId, langType))
                .response();
    }

    /**
     * Возвращает мета профиль и его композицию
     */
    @GetMapping("/get/{id:.+}")
    @Operation(summary = "Возвращает мета профиль")
    public ResponseEntity<ControllerResponse<MetaProfile>> getProfile(
            @PathVariable(name = "id") Long id,
            @RequestHeader(name = "Lang", defaultValue = "ru") String lang
    ) {
        LangType langType = LangType.raw(lang);
        return ControllerResponse.ok(metaProfileService.getProfileById(id, langType)).response();
    }

}
