package com.metaprofile.api.commondata.controllers;

import com.metaprofile.api.commondata.models.CommonData;
import com.metaprofile.api.commondata.services.CommonDataService;
import com.metaprofile.api.core.ControllerResponse;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Tag(name = "Common Data", description = "Поиск и добавление вспомогательных ресурсов")
@RestController
@RequestMapping("/v1/common")
public class CommonDataController {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class CommonDataAddPayload{
        private String value;
    }

    private final CommonDataService commonDataService;

    public CommonDataController(CommonDataService commonDataService) {
        this.commonDataService = commonDataService;
    }

    @Operation(
            summary = "Выполняет поиск вспомогательных ресурсов для MPF",
            description = "Поиск по фильмам, книгам, вкусам, цветам и др"
    )
    @GetMapping("/search/{mpfId:.+}")
    public ControllerResponse<List<CommonData>> search(
            @Parameter(
                    description = "MPF рабочего ресурса. Например, если поиск ресурсов идет по цветам, " +
                            "необходимо указать идентификатор сигнатуры 'любимый цвет'"
            )
            @PathVariable(name = "mpfId", required = true)
                    Long mpfId,
            @Parameter(description = "Поисковой запрос")
            @RequestParam(name = "q", required = true)
                    String q
    ) {
        return ControllerResponse.ok(commonDataService.search(q, mpfId));
    }

    @Operation(
            summary = "Добавляет ресурс для поиска"
    )
    @PostMapping("/add/{mpfId:.+}")
    public ControllerResponse<CommonData> add(
            @Parameter(
                    description = "MPF рабочего ресурса. Например, если добавление ресурсов идет по цветам, " +
                            "необходимо указать идентификатор сигнатуры 'любимый цвет'"
            )
            @PathVariable(name = "mpfId", required = true)
                    Long mpfId,
            @Parameter(description = "Добавляемое значение")
            @Valid @RequestBody CommonDataAddPayload payload
    ) {
        return ControllerResponse.ok(commonDataService.add(payload.getValue(), mpfId, 1L));
    }

}
