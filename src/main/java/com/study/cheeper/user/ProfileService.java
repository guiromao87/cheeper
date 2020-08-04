package com.study.cheeper.user;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.study.cheeper.user.User;
import com.study.cheeper.user.UserRepository;
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
        String imageName = id + "/" + image.getOriginalFilename();


        PutObjectRequest putObjectRequest =
                new PutObjectRequest("cheeper",
                        imageName,
                        image.getInputStream(),
                        new ObjectMetadata());

        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3.putObject(putObjectRequest);

        User user = userRepository.getOne(id);
        user.setImage(bucketUrl + imageName);
        userRepository.save(user);
    }
}
