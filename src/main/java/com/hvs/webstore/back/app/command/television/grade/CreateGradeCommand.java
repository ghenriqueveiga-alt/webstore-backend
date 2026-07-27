package com.hvs.webstore.back.app.command.television.grade;

import java.util.List;

public record CreateGradeCommand(String aNome,
                                 String aDescricao,
                                 List<Long> aBlocoIds) {

    public static CreateGradeCommand from(final String aNome,
                                          final String aDescricao,
                                          final List<Long> aBlocoIds) {

        return new CreateGradeCommand(
                aNome,
                aDescricao,
                aBlocoIds);
    }
}