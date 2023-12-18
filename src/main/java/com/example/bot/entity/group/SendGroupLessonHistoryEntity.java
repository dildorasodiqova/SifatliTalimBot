package com.example.bot.entity.group;

import com.example.web.entity.base.BaseStringEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 'Mukhtarov Sarvarbek' on 18.12.2023
 * @project sifatli_talim_bot
 * @contact @sarvargo
 */
@Entity
@Table(name = "send_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendGroupLessonHistoryEntity extends BaseStringEntity {
    private Long userId;
    private Integer messageId;
    private Long groupId;
    private String groupLessonId;
}
