package com.hvs.webstore.back.app.command.television.grade;

public record GradeSearchQuery(String aSearch,
                               int aPage,
                               int aSize,
                               String aSort,
                               String aDirection) {

    public static GradeSearchQuery from(final String aSearch,
                                        final int aPage,
                                        final int aSize,
                                        final String aSort,
                                        final String aDirection) {

        return new GradeSearchQuery(
                aSearch,
                aPage,
                aSize,
                aSort,
                aDirection);
    }
}