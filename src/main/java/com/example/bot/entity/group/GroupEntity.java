package com.example.bot.entity.group;

import com.example.web.entity.base.BaseLongEntity;
import com.example.web.entity.profile.ProfileEntity;
import jakarta.persistence.*;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", insertable = false, updatable = false)
    private ProfileEntity teacher;
    @Column(name = "teacher_id")
    private Long teacherId;

    private String name; // group name
    private String description; // some text about this group
    private LocalDate startDate; // start date when start this group
    private Boolean started = false;
    private Integer currentOrderNumber = 0; // qaysi darsni yuboryotgani
}
