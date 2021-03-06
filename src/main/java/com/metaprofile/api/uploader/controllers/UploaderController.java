package com.metaprofile.api.uploader.controllers;

import com.metaprofile.api.core.ControllerResponse;
import com.metaprofile.api.uploader.models.File;
import com.metaprofile.api.uploader.enums.FileStatus;
import com.metaprofile.api.uploader.repositories.FileRepository;
import com.metaprofile.api.security.models.UserDetailsImpl;
import com.metaprofile.api.uploader.services.FileService;
import com.metaprofile.api.uploader.services.FileStorageService;
import com.metaprofile.api.uploader.enums.UploadSessionStatus;
import com.metaprofile.api.uploader.models.UploaderSession;
import com.metaprofile.api.uploader.services.UploaderSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;


@RestController
@Tag(name = "Files Uploader", description = "Загрузка файлов")
@RequestMapping(value = "/v1/uploader")
public class UploaderController {

    /**
     * Логгер
     */
    private static final Logger logger = LoggerFactory.getLogger(UploaderController.class);

    /**
     * Сервис файлового хранилища
     */
    private final FileStorageService fileStorageService;

    /**
     * Сервис загрузчика
     */
    private final UploaderSessionService uploaderSessionService;

    /**
     * Файловый репозиторий
     */
    private final FileRepository fileRepository;

    /**
     * Файловый сервис
     */
    private final FileService fileService;

    public UploaderController(UploaderSessionService uploaderSessionService, FileStorageService fileStorageService, FileRepository fileRepository, FileService fileService) {
        this.uploaderSessionService = uploaderSessionService;
        this.fileStorageService = fileStorageService;
        this.fileRepository = fileRepository;
        this.fileService = fileService;
    }

    /**
     * Метод создания сессии загрузки файла
     *
     * @param authentication
     * @return
     */
    @GetMapping("/create")
    @PreAuthorize("hasRole('FILES_UPLOAD')")
    @Operation(
            summary = "Создает сессию загрузки файла",
            description = "Метод открывает новую сессию загрузки файлов"
    )
    public ResponseEntity<ControllerResponse<UploaderSession>> Create(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UploaderSession session = uploaderSessionService.createSession(userDetails.getUserId());
        return ControllerResponse.ok(session).response();
    }

    /**
     * Метод получения информации о файле
     *
     * @param id
     * @param authentication
     * @return
     */
    @GetMapping("/upload:{id:.+}")
    @PreAuthorize("hasRole('FILES_UPLOAD')")
    @Operation(summary = "Получает статут сесии загрузки файла")
    public ResponseEntity<ControllerResponse<UploaderSession>> Get(@PathVariable Long id, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ControllerResponse.ok(uploaderSessionService.getByIdSafe(id, userDetails.getUserId())).response();
    }


    @PostMapping("/upload:{id:.+}")
    @PreAuthorize("hasRole('USER') && hasRole('FILES_UPLOAD')")
    @Operation(summary = "Выполняет загрузку файла")
    public ResponseEntity<?> uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file, @RequestParam(name = "type", required = false, defaultValue = "1") Integer fileStatus, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        // Получаем активную сессию
        UploaderSession uploaderSession = uploaderSessionService.getByIdSafe(id, userDetails.getUserId());

        if (uploaderSession.getCompleted().equals(UploadSessionStatus.COMPLETED)) {
            // Сообщить что нельзя загружать файл по данной сессии
            return ControllerResponse.error("Сессия заблокирована", HttpStatus.FORBIDDEN).response();
        }

        // Запускаем поток сохранения файла и обновляем статус
        uploaderSessionService.setStatus(id, UploadSessionStatus.WAITING);

        // Запускаем новый поток для сохранения файла
        // @todo написать систему очереди
        try {
            // Нормализуем имя файла
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            // @fixme создать метод в сервисе
            File f = new File(fileName, file, userDetails.getUserId(), FileStatus.values()[fileStatus]);
            f.setPath(fileStorageService.storeFile(file, f.getName()));

            File savedFile = fileRepository.save(f);
            uploaderSessionService.complete(id, savedFile.getId());
        } catch (Exception e) {
            uploaderSessionService.setStatus(id, UploadSessionStatus.ERROR);
            e.printStackTrace();
        }

        return new ControllerResponse<>(true, 200).response();
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Отображает ве файлы, загруженные пользователем")
    public ResponseEntity<?> GetFilesList(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ControllerResponse.ok(fileService.getListWithoutRemoved(userDetails.getUserId())).response();
    }
}
