package com.hvs.webstore.back.infra.media;

import com.hvs.webstore.back.app.service.VideoCutDetector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FFmpegVideoCutDetector implements VideoCutDetector {

    private static final long TIMEOUT_SECONDS = 120L;
    private static final double SCENE_THRESHOLD = 0.4;
    private static final double SILENCE_NOISE_DB = -30.0;
    private static final double COMMERCIAL_MIN_SILENCE_SECONDS = 2.0;

    private static final Pattern SCENE_PATTERN =
            Pattern.compile("pts_time:(\\d+(?:\\.\\d+)?)\\s+lavfi\\.scene_score=(\\d+(?:\\.\\d+)?)");
    private static final Pattern SILENCE_START_PATTERN =
            Pattern.compile("silence_start: (\\d+(?:\\.\\d+)?)");
    private static final Pattern SILENCE_END_PATTERN =
            Pattern.compile("silence_end: (\\d+(?:\\.\\d+)?)");
    private static final Pattern SILENCE_DURATION_PATTERN =
            Pattern.compile("silence_duration: (\\d+(?:\\.\\d+)?)");

    @Override
    public List<DetectedScene> detectScenes(final String aCaminho) {

        if (aCaminho == null || aCaminho.isBlank()) {
            return List.of();
        }
        final List<String> output = this.runFfmpeg(List.of(
                "-i", aCaminho,
                "-vf", "select='gt(scene,0.4)',metadata=print",
                "-an", "-f", "null", "-"));

        final List<DetectedScene> scenes = new ArrayList<>();
        for (String line : output) {
            final Matcher matcher = SCENE_PATTERN.matcher(line);
            if (matcher.find()) {
                scenes.add(new DetectedScene("SCENE",
                        Math.round(Double.parseDouble(matcher.group(1))),
                        Double.parseDouble(matcher.group(2))));
            }
        }
        return scenes;
    }

    @Override
    public List<DetectedCommercial> detectCommercials(final String aCaminho) {

        if (aCaminho == null || aCaminho.isBlank()) {
            return List.of();
        }
        final List<String> output = this.runFfmpeg(List.of(
                "-i", aCaminho,
                "-vn", "-af", "silencedetect=noise=-30.0dB:d=2.0",
                "-f", "null", "-"));

        final List<DetectedCommercial> commercials = new ArrayList<>();
        Double pendingStart = null;
        for (String line : output) {
            final Matcher startMatcher = SILENCE_START_PATTERN.matcher(line);
            if (startMatcher.find()) {
                pendingStart = Double.parseDouble(startMatcher.group(1));
                continue;
            }
            final Matcher endMatcher = SILENCE_END_PATTERN.matcher(line);
            if (pendingStart == null || !endMatcher.find()) {
                continue;
            }
            final double fim = Double.parseDouble(endMatcher.group(1));
            final Matcher durationMatcher = SILENCE_DURATION_PATTERN.matcher(line);
            final double duracao = durationMatcher.find()
                    ? Double.parseDouble(durationMatcher.group(1))
                    : fim - pendingStart;
            if (duracao >= COMMERCIAL_MIN_SILENCE_SECONDS) {
                commercials.add(new DetectedCommercial(
                        Math.round(pendingStart), Math.round(fim), duracao));
            }
            pendingStart = null;
        }
        return commercials;
    }

    private List<String> runFfmpeg(final List<String> aArgs) {

        final List<String> command = new ArrayList<>();
        command.add("ffmpeg");
        command.add("-hide_banner");
        command.add("-nostats");
        command.addAll(aArgs);

        try {
            final Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
            final List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }
            if (!process.waitFor(TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
                process.destroyForcibly();
                return List.of();
            }
            return lines;
        } catch (Exception e) {
            return List.of();
        }
    }
}