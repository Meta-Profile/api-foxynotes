package com.metaprofile.api.metaprofile.errors;

public class MetaProfileErrorMessage {
    public static final String notFoundProfile = "Мета профиль mp:{0} не найден";
    public static final String notFoundCategory = "Категория мета полей mpc:{0} не найдена";
    public static final String notFoundField = "Сигнатура мета поля mpf:{0} не найдена";
    public static final String notFoundData= "Данные мета поля mpf:{0} не найдены";

    public static final String fieldNotFoundInProfile = "Сигнатура мета поля mpf:{0} не найдена в профиле mp:{1}";
    public static final String fieldHasAlreadyInProfile = "Сигнатура мета поля mpf:{0} уже содержится в профиле mp:{1}";
    public static final String fieldNotFoundInCategory = "Сигнатура мета поля mpf:{0} не найдена в категории mpc:{1}";

    public static final String dataNotFoundInProfile = "Данные мета поля mpd:{0} не найдены в профиле mp:{1}";
    public static final String dataChangeForbidden = "Данные мета поля mpd:{0} профиле mp:{1} недоступны для изменения";
}
