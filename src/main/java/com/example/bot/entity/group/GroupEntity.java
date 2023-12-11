package com.example.bot.entity.group;

import com.example.web.entity.base.BaseLongEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupEntity extends BaseLongEntity {
    private String name; // group name
    private String description; // some text about this group
    private String imageId; // id is telegram media id
    private LocalDate startDate; // start date when start this group

}
