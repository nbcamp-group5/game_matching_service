package com.nbcamp.gamematching.matchingservice.member.service;

import com.nbcamp.gamematching.matchingservice.member.domain.AmazonS3ResourceStorage;
import com.nbcamp.gamematching.matchingservice.member.domain.FileDetail;
import java.io.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final AmazonS3ResourceStorage amazonS3ResourceStorage;

    public FileDetail save(MultipartFile multipartFile) {
        FileDetail fileDetail = FileDetail.multipartOf(multipartFile);
        amazonS3ResourceStorage.store(fileDetail.getPath(), multipartFile);
        return fileDetail;
    }

    public String getPreSignedURL(File uploadFile) {
        return amazonS3ResourceStorage.getPreSignedURL(uploadFile, "imagesV2");
    }
}
