package com.example.web.service.profileService;

import com.example.web.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl  implements ProfileService{

    private final ProfileRepository profileRepository;


}
