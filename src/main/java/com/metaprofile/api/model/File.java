package com.metaprofile.api.model;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Base64;

@Entity
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "path")
    private String path;

    @Column(name = "type")
    private String type;

    @Column(name = "sender_id")
    private Long senderId;

    // todo: сделать enum для статуса
    // todo: сделать статус 2, что означает, что доступ к файлу публичный
    // @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private Integer status;

    @Column(name = "size")
    private Long size;

    @Column(name = "time")
    private Timestamp time;

    public File(){
    }
    public File(String realName, MultipartFile file, Long userId) {
        this.realName = realName;
        this.size = file.getSize();
        this.type = file.getContentType();
        this.senderId = userId;
        this.status = 1;
        this.time = Timestamp.from(Instant.now());

        // Построение имени объекта
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String salt = Timestamp.from(Instant.now()).toString();
        byte[] source = Base64.getEncoder().encode((realName + salt).getBytes(StandardCharsets.UTF_8));

        this.name = (new String(source, StandardCharsets.UTF_8)) + '.' + extension;
    }

    /**
     * Возвращает путь до файла
     * @return путь до файла
     */
    public String getURL(){
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/file/get/")
                .path(getId().toString())
                .toUriString();
    }

    /**
     * Возвращает синтетический путь до фалйа
     * @return
     */
    public String getPath() {
        return path;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRealName() {
        return realName;
    }

    public String getType() {
        return type;
    }

    public Long getSenderId() {
        return senderId;
    }

    public Integer getStatus() {
        return status;
    }

    public Timestamp getTime() {
        return time;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setPath(String path) {
        this.path = path;
    }
}