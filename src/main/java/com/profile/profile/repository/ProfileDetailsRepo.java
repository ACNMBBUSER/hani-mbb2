package com.profile.profile.repository;

import com.profile.profile.entity.ProfileDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileDetailsRepo extends JpaRepository<ProfileDetails, Long> {
}
