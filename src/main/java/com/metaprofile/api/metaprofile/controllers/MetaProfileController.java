package com.metaprofile.api.metaprofile.controllers;

import com.metaprofile.api.core.ControllerResponse;
import com.metaprofile.api.core.LangType;
import com.metaprofile.api.metaprofile.models.MetaProfileCategory;
import com.metaprofile.api.metaprofile.models.MetaProfileField;
import com.metaprofile.api.metaprofile.services.MetaProfileCategoriesService;
import com.metaprofile.api.metaprofile.services.MetaProfileFieldsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/v1/metaprofile")
public class MetaProfileController {

    private final MetaProfileCategoriesService metaProfileCategoriesService;
    private final MetaProfileFieldsService metaProfileFieldsService;

    public MetaProfileController(MetaProfileCategoriesService metaProfileCategoriesService, MetaProfileFieldsService metaProfileFieldsService) {
        this.metaProfileCategoriesService = metaProfileCategoriesService;
        this.metaProfileFieldsService = metaProfileFieldsService;
    }

    @GetMapping("/categories")
    public ResponseEntity<ControllerResponse<List<MetaProfileCategory>>> getCategories(
            @RequestParam(name = "q", required = false) String q,
            @RequestParam(name = "lang", defaultValue = "ru") String lang
    ) {
        LangType langType = LangType.raw(lang);

        if (q != null)
            return ControllerResponse.ok(metaProfileCategoriesService.findByTitleQuery(q, langType))
                    .response();
        return ControllerResponse.ok(metaProfileCategoriesService.getAll(langType))
                .response();
    }

    @GetMapping("/fields")
    public ResponseEntity<ControllerResponse<List<MetaProfileField>>> getFields(
            @RequestParam(name = "q", required = false) String q,
            @RequestParam(name = "mpcId", required = true) Long mpcId,
            @RequestParam(name = "lang", defaultValue = "ru") String lang
    ) {
        LangType langType = LangType.raw(lang);

        if (q != null)
            return ControllerResponse.ok(metaProfileFieldsService.findByTitleQuery(q, mpcId, langType))
                    .response();
        return ControllerResponse.ok(metaProfileFieldsService.getByMpcId(mpcId, langType))
                .response();
    }

}
