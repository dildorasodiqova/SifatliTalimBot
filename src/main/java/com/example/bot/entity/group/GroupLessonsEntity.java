package com.example.bot.entity.group;

import com.example.bot.enums.LessonMediaType;
import com.example.bot.enums.TextType;
import com.example.web.entity.base.BaseStringEntity;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;


@Entity
@Table(name = "group_lessons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupLessonsEntity extends BaseStringEntity {
    @Column(name = "group_id")
    private Long groupId;

    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private GroupEntity group;

    @Column(nullable = false)
    private String mediaId;

    @Enumerated(EnumType.STRING)
    private LessonMediaType mediaType;

    @Column(columnDefinition = "text")
    private String text;

    @Enumerated(EnumType.STRING)
    private TextType textType;

    private Integer orderNumber; // grupa kontentlari orasida nechanchi bolib yuborilishi

    private LocalTime sendTime; // 10:00

    @Enumerated(EnumType.STRING)
    private DayOfWeek sendDay; // MONDAY
}
