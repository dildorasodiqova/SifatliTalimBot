package com.example.web.service.profileService;

import com.example.bot.dto.responseDto.GroupResponseDto;
import com.example.web.dto.createdDto.ProfileCreatedDto;
import com.example.web.dto.responseDto.ProfileResponseDto;
import org.springframework.data.domain.PageImpl;

public interface ProfileService{
    void  save(ProfileCreatedDto dto);
    void  update(ProfileCreatedDto dto,Long teacherId);
    void delete(Long teacherId);
    PageImpl<ProfileResponseDto> getAll(String query, Integer page, Integer size);

}
