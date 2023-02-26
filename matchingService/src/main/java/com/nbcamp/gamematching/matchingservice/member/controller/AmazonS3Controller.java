package com.nbcamp.gamematching.matchingservice.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class AmazonS3Controller {
//
//    private final AwsS3Service awsS3Service;
//
//    @PostMapping("/uploadFile")
//    public ResponseEntity<List<String>> uploadFile(List<MultipartFile> multipartFiles) {
//        return ResponseEntity.ok(awsS3Service.uploadFile(multipartFiles));
//    }
//
//    @DeleteMapping("/deleteFile")
//    public ResponseEntity<String> deleteFile(@RequestParam String fileName) {
//        awsS3Service.deleteFile(fileName);
//        return ResponseEntity.ok(fileName);
//    }
}