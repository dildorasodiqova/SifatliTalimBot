package com.example.web.service.profileService;

import com.example.bot.dto.responseDto.GroupResponseDto;
import com.example.bot.entity.group.GroupEntity;
import com.example.bot.exception.ApiResponse;
import com.example.web.dto.createdDto.ProfileCreatedDto;
import com.example.web.dto.responseDto.ProfileResponseDto;
import com.example.web.entity.profile.ProfileEntity;
import com.example.web.enums.ProfileRole;
import com.example.web.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl  implements ProfileService{

    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(ProfileCreatedDto dto) {
        ProfileEntity parse = parse(dto);
        profileRepository.save(parse);
    }

    @Override
    public void update(ProfileCreatedDto dto, Long profileId) {
        Optional<ProfileEntity> byId = profileRepository.findById(profileId);
        ApiResponse<ProfileEntity> entity = byId.map(group -> new ApiResponse<>(true, 200, "Successfully", group)).orElseGet(() -> new ApiResponse<>(false, 400, "Profile not found."));
        ProfileEntity data = entity.getData();
        data.setRole(ProfileRole.valueOf(dto.getRole()));
        if (dto.getPassword() != null && !dto.getPassword().isBlank()){
        data.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        data.setUserName(dto.getLogin());
        data.setFullName(dto.getFullName());

        profileRepository.save(data);
    }


    @Override
    public void delete(Long profileId) {
        Optional<ProfileEntity> byId = profileRepository.findById(profileId);
        ApiResponse<ProfileEntity> entity = byId.map(group -> new ApiResponse<>(true, 200, "Successfully", group)).orElseGet(() -> new ApiResponse<>(false, 400, "Profile not found."));
        ProfileEntity data = entity.getData();
        data.setVisible(false);
        profileRepository.save(data);
    }

    @Override
    public PageImpl<ProfileResponseDto> getAll(String query, Integer page, Integer size) {
            Pageable pageRequest = PageRequest
                    .of(
                            page,
                            size,
                            Sort.by(Sort.Direction.DESC, "createdDate")
                    );
            Page<ProfileEntity> roadPage = profileRepository.findAllByActiveTrue(query, pageRequest);
        List<ProfileEntity> content = roadPage.getContent();
        return new PageImpl<>(parse(content), pageRequest, roadPage.getTotalElements());
    }

    private List<ProfileResponseDto> parse(List<ProfileEntity> profiles) {
        List<ProfileResponseDto> list = new ArrayList<>();
        for (ProfileEntity entity : profiles) {
            list.add(new ProfileResponseDto(entity.getId(), entity.getFullName(), entity.getUserName(), entity.getPassword(), entity.getCreatedDate(), entity.getRole().name()));
        }
        return list;
    }

    private ProfileEntity parse(ProfileCreatedDto dto){
        return new ProfileEntity(dto.getFullName(), dto.getLogin(), passwordEncoder.encode(dto.getPassword()), ProfileRole.valueOf(dto.getRole()));
    }
}
