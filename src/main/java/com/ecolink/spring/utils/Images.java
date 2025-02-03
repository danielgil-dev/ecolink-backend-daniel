package com.ecolink.spring.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Images {
    public  boolean isExtensionImageValid(MultipartFile imagen) {
        String nameImage = imagen.getOriginalFilename();
        if (nameImage.endsWith(".jpg") || nameImage.endsWith(".jpeg") || nameImage.endsWith(".png")) {
            return true;
        }

        return false;
    }

    public String uploadFile(MultipartFile file, String directory) {
        if (file.isEmpty()) {
            return null;
        }

        String uniqueId = UUID.randomUUID().toString().replace("-", "");
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex != -1) {
            String nameImage = fileName.substring(0, lastDotIndex);
            String extension = fileName.substring(lastDotIndex + 1);
            String fullImage = (nameImage + "_" + uniqueId + "." + extension).replace(" ", "");
            String fullDir = null;
            fullDir = Paths.get("").toAbsolutePath().toString() + directory;

            Path uploadPath = Paths.get(fullDir);
            Path filePath = uploadPath.resolve(fullImage);

            try {
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                return fullImage;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
