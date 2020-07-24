package com.study.cheeper.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.study.cheeper.model.User;
import com.study.cheeper.repository.UserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProfileService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private UserRepository userRepository;

    @Value("${url.bucket}")
    private String bucketUrl;


    public void uploadProfileImage(Integer id, MultipartFile image) throws IOException {
        PutObjectRequest putObjectRequest =
                new PutObjectRequest("cheeper",
                        image.getOriginalFilename(),
                        image.getInputStream(),
                        new ObjectMetadata());

        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3.putObject(putObjectRequest);

        User user = userRepository.getOne(id);
        user.setImage(bucketUrl + image.getOriginalFilename());
        userRepository.save(user);
    }
}
