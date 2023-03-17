package com.nbcamp.gamematching.matchingservice.member.domain;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.net.URL;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
@Slf4j
public class AmazonS3ResourceStorage {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3Client amazonS3Client;

    public void store(String path, MultipartFile multipartFile) {
        File file = new File(MultipartUtil.getLocalHomeDirectory(), path);
        try {
            multipartFile.transferTo(file);
            amazonS3Client.putObject(new PutObjectRequest(bucket, path, file)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public String getPreSignedURL(File uploadFile, String dirName) {
        String preSignedURL = "";
        String fileName = dirName + "/" + uploadFile.getName();

        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60;
        expiration.setTime(expTimeMillis);

        log.info(expiration.toString());

        try {

            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(bucket, fileName)
                            .withMethod(HttpMethod.PUT)
                            .withExpiration(expiration);
            URL url = amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest);
            preSignedURL = url.toString();
            log.info("Pre-Signed URL : " + url.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return preSignedURL;
    }





}
