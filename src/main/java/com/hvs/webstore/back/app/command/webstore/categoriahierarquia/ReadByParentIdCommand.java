package com.hvs.webstore.back.app.command.webstore.categoriahierarquia;

public record ReadByParentIdCommand(Long aParentId) {

    public static ReadByParentIdCommand from(final Long aParentId) {

        return new ReadByParentIdCommand(aParentId);
    }
}
