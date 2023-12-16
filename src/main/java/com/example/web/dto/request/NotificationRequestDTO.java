package com.example.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Admin on 15.12.2023
 * @project sifatli_talim_bot
 * @package com.example.bot.dto.request
 * @contact @sarvargo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequestDTO {
    private String text;
    private MultipartFile media;
}
