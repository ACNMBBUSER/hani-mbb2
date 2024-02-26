package com.profile.profile.controller;

import com.profile.profile.entity.ProfileDetails;
import com.profile.profile.exception.NotFoundException;
import com.profile.profile.handler.RespondHandler;
import com.profile.profile.response.CreateProfileRes;
import com.profile.profile.response.Response;
import com.profile.profile.service.ProfileDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@SecurityRequirement(name= "bearerAuth")
@Tag(name = "User", description = "The User API. Contains all the operations that can be performed on a user.")
public class ProfileDetailsController {

    @Autowired
    private ProfileDetailsService profileDetailsService;

    @Operation(summary = "Create user", description = "Create user")
    @PostMapping("/create")
    public ResponseEntity<Response> createProfile(@RequestBody @Valid ProfileDetails profileDetails) throws Exception{
//            return RespondHandler.handleResponse(profileDetailsService.createProfile(profileDetails), HttpStatus.CREATED);
        return profileDetailsService.createProfile(profileDetails);
    }

    @Operation(summary = "Get all user", description = "Get all user")
    @GetMapping("/get/all")
    public ResponseEntity<?> getAllProfileDetails(){
        return RespondHandler.handleResponse(profileDetailsService.getAllProfile(), HttpStatus.OK);
    }
    @Operation(summary = "Get user by ID", description = "Get user by ID")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProfileDetails(@PathVariable long id) {
        try{
            return RespondHandler.handleResponse(profileDetailsService.getProfileById(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Operation(summary = "Delete user by ID", description = "Delete user by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProfileDetails(@PathVariable long id){
        try{
            return RespondHandler.handleResponse(profileDetailsService.deleteProfile(id), HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Operation(summary = "Update user by ID", description = "Update user by ID")
    @PutMapping("/update")
    public ResponseEntity<?> updateProfileDetails(@RequestBody ProfileDetails profileDetails){
        return RespondHandler.handleResponse(profileDetailsService.updateProfile(profileDetails), HttpStatus.OK);
    }


}
