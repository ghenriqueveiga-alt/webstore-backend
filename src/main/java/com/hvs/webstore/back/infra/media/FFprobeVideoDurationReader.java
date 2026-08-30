package com.hvs.webstore.back.infra.media;

import com.hvs.webstore.back.app.service.VideoDurationReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FFprobeVideoDurationReader implements VideoDurationReader {

    private static final long TIMEOUT_SECONDS = 15L;

    @Override
    public long readDurationSeconds(final String aCaminho) {

        if (aCaminho == null || aCaminho.isBlank()) {
            return 0L;
        }
        try {
            final Process process = new ProcessBuilder(List.of(
                    "ffprobe", "-v", "error",
                    "-show_entries", "format=duration",
                    "-of", "default=noprint_wrappers=1:nokey=1",
                    aCaminho)).start();
            if (!process.waitFor(TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
                process.destroyForcibly();
                return 0L;
            }
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
                final String line = reader.readLine();
                if (line == null || line.isBlank()) {
                    return 0L;
                }
                return Math.round(Double.parseDouble(line.trim()));
            }
        } catch (Exception e) {
            return 0L;
        }
    }
}