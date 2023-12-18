package com.example.bot.util;

import com.example.bot.TalimBot;
import com.example.bot.enums.LessonMediaType;
import org.springframework.web.multipart.MultipartFile;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Admin on 17.12.2023
 * @project sifatli_talim_bot
 * @package com.example.bot.util
 * @contact @sarvargo
 */
public class MediaUtil {

    private static final List<String> IMAGE_TYPES = Arrays.asList("image/jpeg", "image/png", "image/gif");
    private static final List<String> VIDEO_TYPES = Arrays.asList("video/mp4", "video/mpeg");
    private static final List<String> DOC_TYPES = Arrays.asList("application/pdf",
            "application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/vnd.ms-excel",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    private static final List<String> AUDIO_TYPES = Arrays.asList(
            "audio/mpeg",
            "audio/wav",
            "audio/x-wav",
            "audio/mp4",
            "audio/flac"
    );

    public static String detectFileType(MultipartFile file) {
        String contentType = file.getContentType();

        if (contentType != null) {
            if (IMAGE_TYPES.contains(contentType)) {
                return "PHOTO";
            } else if (VIDEO_TYPES.contains(contentType)) {
                return "VIDEO";
            } else if (DOC_TYPES.contains(contentType)) {
                // Handle other file types as needed
                return "DOCUMENT";
            } else if (AUDIO_TYPES.contains(contentType)) {
                return "AUDIO";
            }
        }
        return "";
    }

    public static LessonMediaType detectType(MultipartFile file) {
        String contentType = file.getContentType();

        if (contentType != null) {
            if (IMAGE_TYPES.contains(contentType)) {
                return LessonMediaType.PHOTO;
            } else if (VIDEO_TYPES.contains(contentType)) {
                return LessonMediaType.VIDEO;
            } else if (DOC_TYPES.contains(contentType)) {
                // Handle other file types as needed
                return LessonMediaType.DOCUMENT;
            } else if (AUDIO_TYPES.contains(contentType)) {
                return LessonMediaType.AUDIO;
            }
        }
        return LessonMediaType.MESSAGE;
    }

    public static String mediaUpload(MultipartFile file, TalimBot telegramBot, Long devId) throws IOException {
        switch (detectFileType(file)) {
            case "PHOTO" -> {
                SendPhoto sendPhoto = new SendPhoto();
                sendPhoto.setPhoto(new InputFile(file.getInputStream(), file.getOriginalFilename()));
                sendPhoto.setChatId(devId);
                return telegramBot.send(sendPhoto).getPhoto().get(0).getFileId();
            }
            case "VIDEO" -> {
                SendVideo sendVideo = new SendVideo();
                sendVideo.setVideo(new InputFile(file.getInputStream(), file.getOriginalFilename()));
                sendVideo.setChatId(devId);
                return telegramBot.send(sendVideo).getVideo().getFileId();
            }
            case "DOCUMENT" -> {
                SendDocument document = new SendDocument();
                document.setChatId(devId);
                document.setDocument(new InputFile(file.getInputStream(), file.getOriginalFilename()));
                return telegramBot.send(document).getDocument().getFileId();
            }
        }
        return null;
    }
}
