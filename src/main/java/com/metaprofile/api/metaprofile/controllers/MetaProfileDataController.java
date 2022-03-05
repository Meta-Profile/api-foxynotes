package com.metaprofile.api.metaprofile.controllers;

import com.metaprofile.api.core.ControllerResponse;
import com.metaprofile.api.metaprofile.models.MetaProfileData;
import com.metaprofile.api.metaprofile.services.MetaProfileDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Контроллер по управлению данными мета профилей
 */
@RestController
@RequestMapping(value = "/v1/mpd")
@Tag(name = "Meta Profile Data", description = "Создание, редактирование и удаление данных мета-профилей")
public class MetaProfileDataController {

    /**
     * Создание экземпляра
     */
    @Tag(name = "Create Payload", description = "Данные для создания мета поля в мета-профиле")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class CreatePayload {
        @Schema(description = "Идентификатор мета-профиля", required = true)
        private Long mpId;

        @Schema(description = "Идентификатор категории мета поля", required = true)
        private Long mpcId;

        @Schema(description = "Идентификатор сигнатуры мета поля", required = true)
        private Long mpfId;

        @Schema(description = "Данные мета поля", required = true)
        private Object data;
    }

    /**
     * Обновление экземпляра
     */
    @Tag(name = "Update Payload", description = "Данные для обновления мета поля в мета-профиле")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class UpdatePayload {
        @Schema(description = "Идентификатор мета-профиля", required = true)
        private Long mpId;

        @Schema(description = "Данные мета поля", required = true)
        private Object data;
    }

    /**
     * Удаление экземпляра
     */
    @Tag(name = "Remove Payload", description = "Данные для удаления мета поля в мета-профиле")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class RemovePayload {
        @Schema(description = "Идентификатор мета-профиля", required = true)
        private Long mpId;
    }

    /**
     * Перемещение экземпляра
     */
    @Tag(name = "Move Payload", description = "Данные для перемещения поля по y-pos ")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class MovePayload {
        @Schema(description = "Идентификатор мета-профиля", required = true)
        private Long mpId;
        private int position;
    }

    private final MetaProfileDataService metaProfileDataService;

    public MetaProfileDataController(MetaProfileDataService metaProfileDataService) {
        this.metaProfileDataService = metaProfileDataService;
    }


    @PostMapping("/add")
    @Operation(summary = "Создает единицу данных по сигнатуре мета поля в мета-профиль")
    public ControllerResponse<MetaProfileData> add(
            @Valid @RequestBody CreatePayload payload
    ) {
        return ControllerResponse.ok(metaProfileDataService.add(
                payload.mpId,
                payload.mpcId,
                payload.mpfId,
                payload.data,
                1L
        ));
    }

    @Operation(summary = "Обновляет мета данные")
    @PostMapping("/update/{id:.+}")
    public ControllerResponse<MetaProfileData> update(
            @PathVariable(name = "id", required = true) Long mpdId,
            @Valid @RequestBody UpdatePayload payload
    ) {
        return ControllerResponse.ok(metaProfileDataService.update(
                payload.mpId,
                mpdId,
                payload.data,
                1L
        ));
    }


    @Operation(summary = "Извращает мета данные")
    @GetMapping("/get/{id:.+}")
    public ControllerResponse<MetaProfileData> get(
            @PathVariable(name = "id", required = true) Long mpdId
    ) {
        return ControllerResponse.ok(metaProfileDataService.get(mpdId));
    }

    @Operation(summary = "Удаляет мета данные")
    @PostMapping("/remove/{id:.+}")
    public ControllerResponse<Boolean> remove(
            @PathVariable(name = "id", required = true) Long mpdId,
            @Valid @RequestBody RemovePayload payload
    ) {
        return ControllerResponse.ok(metaProfileDataService.remove(payload.mpId, mpdId, 1L));
    }


    @Operation(summary = "Устанавливает y-pos позиционирование мета данных в мета-профиле")
    @PostMapping("/move/{id:.+}")
    public ControllerResponse<MetaProfileData> move(
            @PathVariable(name = "id", required = true) Long mpdId,
            @Valid @RequestBody MovePayload payload
    ) {
        return ControllerResponse.ok(metaProfileDataService.move(
                payload.mpId,
                mpdId,
                payload.position,
                1L)
        );
    }

}
