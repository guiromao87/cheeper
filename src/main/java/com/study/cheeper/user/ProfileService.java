package com.study.cheeper.user;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.study.cheeper.cheep.Cheep;
import com.study.cheeper.cheep.CheepRepository;
import com.study.cheeper.login.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheepRepository cheepRepository;

    @Value("${url.bucket}")
    private String bucketUrl;

    @Autowired
    private LoggedUser loggedUser;

    public void uploadProfileImage(MultipartFile image) {
        User user = loggedUser.asUser();

        try {
            String imageName = uploadAmazonS3(user.getId(), image);
            user.setImage(bucketUrl + imageName);
            userRepository.save(user);
        } catch (IOException ex) {
            throw new RuntimeException("Erro no carregamento da imagem");
        }
    }

    public List<User> following(String profileName) {
        Optional<User> userOptional = userRepository.findByProfileName(profileName);

        if(!userOptional.isPresent()) throw new UserNotExistsException("Este usuário não existe");

        Page<User> pageOfFollowed = userRepository.followeds(userOptional.get(), PageRequest.of(0, 5));
        return pageOfFollowed.getContent();
    }

    public List<User> followers(String profileName) {
        Optional<User> userOptional = userRepository.findByProfileName(profileName);

        if(!userOptional.isPresent()) throw new UserNotExistsException("Este usuário não existe");

        Page<User> pageOfFollower = userRepository.followers(userOptional.get(), PageRequest.of(0, 5));
        return pageOfFollower.getContent();
    }

    public void follow(User follower, String profileName) {
        Optional<User> beFollowed = userRepository.findByProfileName(profileName);

        if(!beFollowed.isPresent()) throw new UserNotExistsException("Este usuário não existe");

        userRepository.insert(follower, beFollowed.get());
    }

    public void unfollow(User follower, String profileName) {
        Optional<User> beUnFollowed = userRepository.findByProfileName(profileName);

        if(!beUnFollowed.isPresent()) throw new UserNotExistsException("Este usuário não existe");

        userRepository.unfollow(follower, beUnFollowed.get());
    }

    public ProfileDto profile(User profile) {
        User current = loggedUser.asUser();
        Page<Cheep> cheepPage = this.cheepRepository.findByProfileId(profile.getId(),
                PageRequest.of(0, 5, Sort.by("creation").descending()));

        int numberOfIfollow = userRepository.numberOfIfollow(profile);
        int numberOfFollowsMe = userRepository.numberOfFollowsMe(profile);
        int count = userRepository.isFollowing(current, profile);


        ProfileDto profileDto = new ProfileDto(profile, cheepPage, numberOfIfollow, numberOfFollowsMe);

        if(profile.isNotTheSameAs(current) && (count > 0))
            profileDto.beingFollowed();

        return profileDto;
    }

    private String uploadAmazonS3(Integer id, MultipartFile image) throws IOException {
        String imageName = id + "/" + image.getOriginalFilename();

        PutObjectRequest putObjectRequest =
                new PutObjectRequest("cheeper",
                        imageName,
                        image.getInputStream(),
                        new ObjectMetadata());

        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3.putObject(putObjectRequest);

        return imageName;
    }
}
