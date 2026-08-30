package com.hvs.webstore.back.infra.media;

import com.hvs.webstore.back.app.service.MediaPathResolver;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MediaPathResolverImpl implements MediaPathResolver {

    private final Path root;

    public MediaPathResolverImpl(final String aRoot) {

        this.root = (aRoot == null || aRoot.isBlank()) ? null : Paths.get(aRoot);
    }

    @Override
    public String resolve(final String aCaminho) {

        if (aCaminho == null || aCaminho.isBlank() || this.root == null) {
            return aCaminho;
        }
        final Path p = Paths.get(aCaminho);
        if (p.isAbsolute()) {
            return aCaminho;
        }
        return this.root.resolve(p).normalize().toString();
    }

    @Override
    public String relativize(final String aCaminho) {

        if (aCaminho == null || aCaminho.isBlank() || this.root == null) {
            return aCaminho;
        }
        final Path p = Paths.get(aCaminho);
        if (!p.isAbsolute()) {
            return aCaminho;
        }
        try {
            return this.root.relativize(p).toString();
        } catch (IllegalArgumentException e) {
            return aCaminho;
        }
    }
}