package com.metaprofile.api.core;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.jetbrains.annotations.Nullable;

/**
 * Строитель ответа контроллера
 *
 * @param <T> тип ответа
 */
public class ControllerResponse<T> {

    /**
     * Возвращает оформленный ответ
     * @param response
     * @param <T>
     * @return
     */
    public static <T> ControllerResponse<T> ok(@Nullable T response){
        return new ControllerResponse<T>(response, 200);
    }

    public static ControllerResponse<?> error(String text, HttpStatus status){
        return new ControllerResponse<>(text, status);
    }

    public static ControllerResponse<?> error(String text, Integer status){
        return new ControllerResponse<>(text, status);
    }

    /**
     * Ответ
     */
    @Nullable
    public final T response;

    /**
     * Статус ответа
     */
    public final int status;

    @Nullable
    public final String error;

    public ControllerResponse(@Nullable T response, @NotNull HttpStatus status) {
        this.response = response;
        this.status = status.value();
        this.error = null;
    }

    public ControllerResponse(@Nullable T response, Integer status) {
        this.response = response;
        this.status = status;
        this.error = null;
    }

    public ControllerResponse(@Nullable T response) {
        this.response = response;
        this.status = HttpStatus.OK.value();
        this.error = null;
    }

    public ControllerResponse(@NotNull String error, HttpStatus status) {
        this.response = null;
        this.error = error;
        this.status = status.value();
    }

    public ControllerResponse(@NotNull String error, Integer status) {
        this.response = null;
        this.error = error;
        this.status = status;
    }

    public ControllerResponse(@NotNull String error) {
        this.response = null;
        this.error = error;
        this.status = 500;
    }

    public ResponseEntity<ControllerResponse<T>> response() {
        return new ResponseEntity<>(this, HttpStatus.resolve(this.status) != null ? HttpStatus.resolve(this.status) : HttpStatus.OK);
    }
}

