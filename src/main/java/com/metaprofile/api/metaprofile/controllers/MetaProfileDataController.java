package com.metaprofile.api.metaprofile.controllers;

import com.metaprofile.api.core.ControllerResponse;
import com.metaprofile.api.metaprofile.models.MetaProfileData;
import com.metaprofile.api.metaprofile.services.MetaProfileDataService;
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
public class MetaProfileDataController {

    /**
     * Создание экземпляра
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class CreatePayload {
        private Long mpId;
        private Long mpcId;
        private Long mpfId;
        private Object data;
    }

    /**
     * Обновление экземпляра
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class UpdatePayload {
        private Long mpId;
        private Object data;
    }

    /**
     * Удаление экземпляра
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class RemovePayload {
        private Long mpId;
    }

    /**
     * Перемещение экземпляра
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class MovePayload {
        private Long mpId;
        private int position;
    }

    private final MetaProfileDataService metaProfileDataService;

    public MetaProfileDataController(MetaProfileDataService metaProfileDataService) {
        this.metaProfileDataService = metaProfileDataService;
    }


    @PostMapping("/add")
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

    @GetMapping("/get/{id:.+}")
    public ControllerResponse<MetaProfileData> get(
            @PathVariable(name = "id", required = true) Long mpdId
    ) {
        return ControllerResponse.ok(metaProfileDataService.get(mpdId));
    }

    @PostMapping("/remove/{id:.+}")
    public ControllerResponse<Boolean> remove(
            @PathVariable(name = "id", required = true) Long mpdId,
            @Valid @RequestBody RemovePayload payload
    ) {
        return ControllerResponse.ok(metaProfileDataService.remove(payload.mpId, mpdId, 1L));
    }


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
