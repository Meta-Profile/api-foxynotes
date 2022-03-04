package com.metaprofile.api.metaprofile.controllers;

import com.metaprofile.api.core.ControllerResponse;
import com.metaprofile.api.core.LangType;
import com.metaprofile.api.metaprofile.models.MetaProfileCategory;
import com.metaprofile.api.metaprofile.services.MetaProfileCategoriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/v1/metaprofile")
public class MetaProfileController {

    private final MetaProfileCategoriesService metaProfileCategoriesService;

    public MetaProfileController(MetaProfileCategoriesService metaProfileCategoriesService) {
        this.metaProfileCategoriesService = metaProfileCategoriesService;
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

}
