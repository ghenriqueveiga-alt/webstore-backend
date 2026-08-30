package com.hvs.webstore.back.infra.media;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class FingerprintCache {

    private static final Logger LOG = Logger.getLogger(FingerprintCache.class.getName());
    private static final Path CACHE_DIR = Path.of(System.getProperty("user.home"), ".webstore", "fingerprints");

    private final Map<String, CacheEntry> memoryCache = new ConcurrentHashMap<>();

    public FingerprintCache() {
        try {
            Files.createDirectories(CACHE_DIR);
        } catch (IOException e) {
            LOG.warning("Could not create fingerprint cache dir: " + e.getMessage());
        }
    }

    public long[] get(final String aCaminho, final int aScanSeconds, final String aType) {
        final String key = cacheKey(aCaminho, aScanSeconds, aType);
        final CacheEntry mem = memoryCache.get(key);
        if (mem != null) {
            return mem.frames;
        }
        final Path file = cacheFile(key);
        if (Files.exists(file)) {
            try {
                final long[] frames = readFrames(file);
                memoryCache.put(key, new CacheEntry(frames, System.currentTimeMillis()));
                return frames;
            } catch (Exception e) {
                LOG.fine("Cache read failed for " + aCaminho + ": " + e.getMessage());
            }
        }
        return null;
    }

    public void put(final String aCaminho, final int aScanSeconds, final String aType, final long[] aFrames) {
        if (aFrames == null || aFrames.length == 0) {
            return;
        }
        final String key = cacheKey(aCaminho, aScanSeconds, aType);
        memoryCache.put(key, new CacheEntry(aFrames, System.currentTimeMillis()));
        final Path file = cacheFile(key);
        try {
            writeFrames(file, aFrames);
        } catch (Exception e) {
            LOG.fine("Cache write failed for " + aCaminho + ": " + e.getMessage());
        }
    }

    public boolean isCached(final String aCaminho, final int aScanSeconds, final String aType) {
        final String key = cacheKey(aCaminho, aScanSeconds, aType);
        if (memoryCache.containsKey(key)) {
            return true;
        }
        return Files.exists(cacheFile(key));
    }

    public int cachedCount() {
        return memoryCache.size();
    }

    private String cacheKey(final String aCaminho, final int aScanSeconds, final String aType) {
        final Path p = Path.of(aCaminho);
        long size = 0;
        long modified = 0;
        try {
            size = Files.size(p);
            modified = Files.getLastModifiedTime(p).toMillis();
        } catch (IOException e) {
            // defaults already set
        }
        return sha256(aType + "|" + aCaminho + "|" + size + "|" + modified + "|" + aScanSeconds);
    }

    private Path cacheFile(final String aKey) {
        return CACHE_DIR.resolve(aKey + ".fp");
    }

    private void writeFrames(final Path aFile, final long[] aFrames) throws IOException {
        final ByteBuffer buf = ByteBuffer.allocate(4 + aFrames.length * 4);
        buf.order(ByteOrder.LITTLE_ENDIAN);
        buf.putInt(aFrames.length);
        for (long f : aFrames) {
            buf.putInt((int) (f & 0xFFFFFFFFL));
        }
        Files.write(aFile, buf.array());
    }

    private long[] readFrames(final Path aFile) throws IOException {
        final byte[] raw = Files.readAllBytes(aFile);
        final ByteBuffer buf = ByteBuffer.wrap(raw);
        buf.order(ByteOrder.LITTLE_ENDIAN);
        final int n = buf.getInt();
        final long[] frames = new long[n];
        for (int i = 0; i < n; i++) {
            frames[i] = buf.getInt() & 0xFFFFFFFFL;
        }
        return frames;
    }

    private static String sha256(final String aInput) {
        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            final byte[] hash = md.digest(aInput.getBytes());
            final StringBuilder sb = new StringBuilder(64);
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return String.valueOf(aInput.hashCode());
        }
    }

    private record CacheEntry(long[] frames, long timestamp) {
    }
}
