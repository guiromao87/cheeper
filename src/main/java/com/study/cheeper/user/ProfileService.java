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

    public void follow(User follower, String profileName) {
        Optional<User> beFollowed = userRepository.findByProfileName(profileName);

        if(!beFollowed.isPresent()) throw new UserNotExistsException("Este usuário não existe");

        follower.follow(beFollowed.get());
        userRepository.save(follower);

    }

    public void unfollow(User follower, String profileName) {
        Optional<User> beUnFollowed = userRepository.findByProfileName(profileName);

        if(!beUnFollowed.isPresent()) throw new UserNotExistsException("Este usuário não existe");

        follower.unfollow(beUnFollowed.get());
        userRepository.save(follower);
    }

    public ProfileDto profile(User profile) {
        User current = loggedUser.asUser();
        List<Cheep> cheepsByProfile = this.cheepRepository.findByProfileId(profile.getId());
        ProfileDto profileDto = new ProfileDto(profile, cheepsByProfile);

        if(profile.isNotTheSameAs(current) && (current.isFollowing(profile)))
            profileDto.beingFollowed();

        return profileDto;
    }
}
