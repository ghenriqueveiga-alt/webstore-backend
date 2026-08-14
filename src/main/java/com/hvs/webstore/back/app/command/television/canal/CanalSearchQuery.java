package com.hvs.webstore.back.app.command.television.canal;

public record CanalSearchQuery(String aSearch,
                               int aPage,
                               int aSize,
                               String aSort,
                               String aDirection) {

    public static CanalSearchQuery from(final String aSearch,
                                        final int aPage,
                                        final int aSize,
                                        final String aSort,
                                        final String aDirection) {

        return new CanalSearchQuery(
                aSearch,
                aPage,
                aSize,
                aSort,
                aDirection);
    }
}
