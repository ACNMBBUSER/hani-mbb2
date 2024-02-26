package com.profile.profile.service;

import com.profile.profile.entity.ProfileDetails;
import com.profile.profile.handler.RespondHandler;
import com.profile.profile.repository.ProfileDetailsRepo;
import com.profile.profile.response.CreateProfileRes;
import com.profile.profile.response.DeleteProfileRes;
import com.profile.profile.response.Response;
import com.profile.profile.response.UpdateProfileRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileDetailsService {
    @Autowired
    private ProfileDetailsRepo profileDetailsRepo;

    public ResponseEntity<Response> createProfile(ProfileDetails profileDetails){

        ProfileDetails savedProfileDetails = profileDetailsRepo.save(profileDetails);

        CreateProfileRes res = new CreateProfileRes();
        res.setCreateSuccess(true);
        res.setId(savedProfileDetails.getId());

        return RespondHandler.handleResponse(res, HttpStatus.CREATED);
    }

    public List<ProfileDetails> getAllProfile(){
        return profileDetailsRepo.findAll();
    }

    public ProfileDetails getProfileById(long id){
        return profileDetailsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with ID: " + id)
                );
    }

    public DeleteProfileRes deleteProfile(long id){
        profileDetailsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile to delete not found with ID: " + id)
                );

        profileDetailsRepo.deleteById(id);

        DeleteProfileRes res = new DeleteProfileRes();
        res.setDeleteSuccess(true);

        return res;
    }

    public UpdateProfileRes updateProfile(ProfileDetails profileDetails) {
        profileDetailsRepo.findById(profileDetails.getId())
                .orElseThrow(() -> new RuntimeException("Profile to update not found with ID: " + profileDetails.getId())
                );

        profileDetailsRepo.save(profileDetails);

        UpdateProfileRes res = new UpdateProfileRes();
        res.setUpdateSuccess(true);

        return res;
    }

    public UpdateProfileRes patchProfile(ProfileDetails profileDetails) {
        profileDetailsRepo.findById(profileDetails.getId())
                .orElseThrow(() -> new RuntimeException("Profile to update not found with ID: " + profileDetails.getId())
                );


        profileDetailsRepo.save(profileDetails);

        UpdateProfileRes res = new UpdateProfileRes();
        res.setUpdateSuccess(true);

        return res;
    }
}
