package com.metaprofile.api.controllers;

import com.metaprofile.api.core.ControllerResponse;
import com.metaprofile.api.model.File;
import com.metaprofile.api.model.enums.FileStatus;
import com.metaprofile.api.payloads.request.FileStatusRequest;
import com.metaprofile.api.payloads.response.UploadFileResponse;
import com.metaprofile.api.repository.FileRepository;
import com.metaprofile.api.security.models.UserDetailsImpl;
import com.metaprofile.api.service.FileService;
import com.metaprofile.api.service.impl.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

// @todo - добавить функционал сессии загрузки файла (как в instagram)
@RestController
@RequestMapping(value = "/api/v1/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    private final FileStorageService fileStorageService;
    private final FileRepository fileRepository;
    private final FileService fileService;

    public FileController(FileStorageService fileStorageService, FileRepository fileRepository, FileService fileService) {
        this.fileStorageService = fileStorageService;
        this.fileRepository = fileRepository;
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    @PreAuthorize("hasRole('USER') && hasRole('FILES_UPLOAD')")
    public ResponseEntity<ControllerResponse<UploadFileResponse>> uploadFile(@RequestParam("file") MultipartFile file, Authentication authentication) throws NoSuchAlgorithmException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        File f = new File(fileName, file, userDetails.getId(), FileStatus.PRIVATE);
        f.setPath(fileStorageService.storeFile(file, f.getName()));

        File savedFile = fileRepository.save(f);

        return new ControllerResponse<>(new UploadFileResponse(savedFile), 200).response();
    }

    @GetMapping("/remove/{fileId:.+}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ControllerResponse<Boolean>> remove(@PathVariable Long fileId, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new ControllerResponse<>(fileService.removeFile(fileId, userDetails.getId()), 200).response();
    }

    @PostMapping("/status/{fileId:.+}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ControllerResponse<Boolean>> updateStatus(@PathVariable Long fileId, @RequestBody FileStatusRequest fileStatusRequest, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new ControllerResponse<>(
                fileService.updateStatus(fileId, userDetails.getId(), fileStatusRequest.getStatus())
        ).response();
    }

    @GetMapping("/get/{fileId:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId, HttpServletRequest request, Authentication authentication) {
        // Load file as Resource
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        File file = fileService.getFileById(fileId, userDetails.getId());

        Resource resource = fileStorageService.loadFileAsResource(file.getPath());

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
