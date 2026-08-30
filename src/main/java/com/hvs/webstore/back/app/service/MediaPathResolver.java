package com.hvs.webstore.back.app.service;

public interface MediaPathResolver {

    String resolve(String aCaminho);

    String relativize(String aCaminho);
}