package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

@Service
public class SupabaseStorageService {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.service-key}")
    private String serviceKey;

    @Value("${supabase.storage.bucket}")
    private String bucket;

    private final HttpClient httpClient =
            HttpClient.newHttpClient();

    public String uploadImage(
            MultipartFile file,
            String mouseId) throws IOException, InterruptedException {

        if (file.isEmpty()) {
            throw new IllegalArgumentException(
                    "Please select an image"
            );
        }

        String contentType = file.getContentType();

        if (contentType == null ||
                !contentType.startsWith("image/")) {

            throw new IllegalArgumentException(
                    "Only image files are allowed"
            );
        }

        if (file.getSize() > 5 * 1024 * 1024) {

            throw new IllegalArgumentException(
                    "Image must be smaller than 5 MB"
            );
        }

        String extension = getExtension(
                file.getOriginalFilename()
        );

        String fileName =
                mouseId + "-" +
                        UUID.randomUUID() +
                        extension;

        String storagePath =
                "mice/" + fileName;

        String uploadUrl =
                supabaseUrl +
                        "/storage/v1/object/" +
                        bucket +
                        "/" +
                        storagePath;

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(URI.create(uploadUrl))
                        .header(
                                HttpHeaders.AUTHORIZATION,
                                "Bearer " + serviceKey
                        )
                        .header(
                                "apikey",
                                serviceKey
                        )
                        .header(
                                HttpHeaders.CONTENT_TYPE,
                                contentType
                        )
                        .POST(
                                HttpRequest.BodyPublishers
                                        .ofByteArray(file.getBytes())
                        )
                        .build();

        HttpResponse<String> response =
                httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        if (response.statusCode() < 200 ||
                response.statusCode() >= 300) {

            throw new RuntimeException(
                    "Supabase upload failed: " +
                            response.body()
            );
        }

        return supabaseUrl +
                "/storage/v1/object/public/" +
                bucket +
                "/" +
                storagePath;
    }

    private String getExtension(String filename) {

        if (filename == null ||
                !filename.contains(".")) {

            return ".jpg";
        }

        return filename.substring(
                filename.lastIndexOf(".")
        );
    }
}