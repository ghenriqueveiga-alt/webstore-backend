package com.hvs.webstore.back.infra.media;

import com.hvs.webstore.back.app.service.EndingDetector;
import com.hvs.webstore.back.app.service.IntroDetector;
import com.hvs.webstore.back.app.service.MediaPathResolver;
import com.hvs.webstore.back.app.service.VideoCutDetector;
import com.hvs.webstore.back.app.service.VideoDurationReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class MediaConfig {

    @Bean
    public MediaPathResolver mediaPathResolverBean(@Value("${webstore.media.root:}") String aRoot) {

        return new MediaPathResolverImpl(aRoot);
    }

    @Bean
    public VideoDurationReader videoDurationReaderBean() {

        return new FFprobeVideoDurationReader();
    }

    @Bean
    public VideoCutDetector videoCutDetectorBean() {

        return new FFmpegVideoCutDetector();
    }

    @Bean
    public FingerprintCache fingerprintCacheBean() {
        return new FingerprintCache();
    }

    @Bean
    public ExecutorService fingerprintThreadPool() {
        final int processors = Runtime.getRuntime().availableProcessors();
        final int threads = Math.max(2, Math.min(6, processors / 2));
        return Executors.newFixedThreadPool(threads, r -> {
            final Thread t = new Thread(r, "fingerprint-" + r.hashCode());
            t.setDaemon(true);
            return t;
        });
    }

    @Bean
    public SceneBoundaryVerifier sceneBoundaryVerifierBean(final VideoCutDetector aCutDetector) {
        return new SceneBoundaryVerifier(aCutDetector);
    }

    @Bean
    public IntroDetector introDetectorBean(final VideoDurationReader videoDurationReader,
                                           final FingerprintCache aCache,
                                           final ExecutorService aThreadPool,
                                           final SceneBoundaryVerifier aSceneVerifier) {

        final ChromaprintIntroDetector chromaprint = new ChromaprintIntroDetector(
                videoDurationReader, aThreadPool, aCache, aSceneVerifier);
        return new VisualFusionIntroDetector(chromaprint, new VisualFusion(videoDurationReader), aSceneVerifier);
    }

    @Bean
    public EndingDetector endingDetectorBean(final VideoDurationReader videoDurationReader,
                                             @Value("${webstore.ending-detectado.threads:0}") int aThreads,
                                             final FingerprintCache aCache,
                                             final ExecutorService aThreadPool,
                                             final SceneBoundaryVerifier aSceneVerifier) {

        final ChromaprintEndingDetector chromaprint = new ChromaprintEndingDetector(
                videoDurationReader, aThreads, aThreadPool, aCache, aSceneVerifier);
        return new VisualFusionEndingDetector(chromaprint, new VisualFusion(videoDurationReader), aSceneVerifier);
    }
}
