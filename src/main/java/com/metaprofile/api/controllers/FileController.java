package com.metaprofile.api.controllers;

import com.metaprofile.api.core.ControllerResponse;
import com.metaprofile.api.model.File;
import com.metaprofile.api.model.enums.FileStatus;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    private final FileStorageService fileStorageService;
    private final FileService fileService;

    public FileController(FileStorageService fileStorageService, FileService fileService) {
        this.fileStorageService = fileStorageService;
        this.fileService = fileService;
    }

    @GetMapping("/remove/{fileId:.+}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ControllerResponse<Boolean>> remove(@PathVariable Long fileId, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new ControllerResponse<>(fileService.removeFile(fileId, userDetails.getId()), 200).response();
    }

    @PostMapping("/status/{fileId:.+}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ControllerResponse<Boolean>> updateStatus(@PathVariable Long fileId, @RequestParam String status, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Boolean result = false;
        switch (status.toLowerCase()) {
            case "public":
                result = fileService.updateStatus(fileId, userDetails.getId(), FileStatus.PUBLIC);
                break;
            case "private":
                result = fileService.updateStatus(fileId, userDetails.getId(), FileStatus.PRIVATE);
                break;
        }
        return ControllerResponse.ok(result).response();
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
