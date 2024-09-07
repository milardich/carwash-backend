package com.sm.carwashmonitor.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileReaderUtil {
    public static String readStringFromFile(String fileName) {
        String content = "";
        ClassPathResource classPathResource = new ClassPathResource(fileName);
        try {
            byte[] binaryData = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
            content = new String(binaryData, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
