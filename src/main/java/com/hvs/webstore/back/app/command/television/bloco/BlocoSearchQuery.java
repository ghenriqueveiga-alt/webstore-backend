package com.hvs.webstore.back.app.command.television.bloco;

public record BlocoSearchQuery(String aSearch,
                               Long aGradeId,
                               int aPage,
                               int aSize,
                               String aSort,
                               String aDirection) {

    public static BlocoSearchQuery from(final String aSearch,
                                        final Long aGradeId,
                                        final int aPage,
                                        final int aSize,
                                        final String aSort,
                                        final String aDirection) {

        return new BlocoSearchQuery(
                aSearch,
                aGradeId,
                aPage,
                aSize,
                aSort,
                aDirection);
    }
}