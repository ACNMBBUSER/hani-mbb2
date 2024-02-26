package com.profile.profile.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.profile.profile.entity.ProfileDetails;
import com.profile.profile.exception.NotFoundException;
import com.profile.profile.handler.RespondHandler;
import com.profile.profile.response.CreateProfileRes;
import com.profile.profile.service.ProfileDetailsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// @WebMvcTest
// - to disable full autoconfiguration
// - apply only configuration for MVC tests (Rest controller)
// - @Service, @component, @Repository. components won't be loaded

// Mockito APIs
// - Create mock objects of service component used by Rest controller
// - Mock objects in arguments return statement of service method
// - Verify if a service method gets called

// MockMvc
// - Perform requests (API calls)
// - Assert the response

@WebMvcTest(ProfileDetailsController.class)
class ProfileDetailsControllerTest {

    private static final String END_POINT_PATH = "/api/profile";
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private ProfileDetailsService profileDetailsService;

    @Test
    void createProfileBadRequest() throws Exception {
        ProfileDetails newProfileDetails = new ProfileDetails();
        newProfileDetails.setName(null);
        newProfileDetails.setAge(0);
        newProfileDetails.setOccupation(null);
        newProfileDetails.setPhoneNumber(null);

        String requestBody = objectMapper.writeValueAsString(newProfileDetails);

        mockMvc.perform(post(END_POINT_PATH + "/create")
                .contentType("application/json")
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void createProfileCreated() throws Exception {
        ProfileDetails newProfileDetails = new ProfileDetails();
        newProfileDetails.setName("Tester");
        newProfileDetails.setAge(4);
        newProfileDetails.setOccupation("Teacher");
        newProfileDetails.setPhoneNumber("012-1922238");

//        CreateProfileRes res = new CreateProfileRes();
//        res.setCreateSuccess(true);
//        res.setId(123);
//
//        Mockito.when(profileDetailsService.createProfile(newProfileDetails)).thenReturn(res);

        String requestBody = objectMapper.writeValueAsString(newProfileDetails);

        mockMvc.perform(post(END_POINT_PATH + "/create")
                .contentType("application/json")
                .content(requestBody))
                .andExpect(status().isCreated())
                .andDo(print());
    }

//    @Test
//    void getAllProfileDetails() {
//    }

    @Test
    void getProfileDetailsNotFound() throws Exception {
        long userId = 0;
        String requestURI = END_POINT_PATH + "/get/" + userId;

        Mockito.when(profileDetailsService.getProfileById(userId)).thenThrow(new RuntimeException("Profile not found with ID: " + userId));

        mockMvc.perform(get(requestURI)
                .contentType("application/json"))
                .andExpect(status().isNotFound())
                .andDo(print());

    }

    @Test
    void getProfileDetailsOk() throws Exception {
        long userId = 1253;
        String requestURI = END_POINT_PATH + "/get/" + userId;

        ProfileDetails newProfileDetails = new ProfileDetails();
        newProfileDetails.setName("Tester");
        newProfileDetails.setAge(4);
        newProfileDetails.setOccupation("Teacher");
        newProfileDetails.setPhoneNumber("012-1922238");

        Mockito.when(profileDetailsService.getProfileById(userId)).thenReturn(newProfileDetails);

        mockMvc.perform(get(requestURI)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andDo(print());

    }

//    @Test
//    void deleteProfileDetails() {
//    }
//
//    @Test
//    void updateProfileDetails() {
//    }
}