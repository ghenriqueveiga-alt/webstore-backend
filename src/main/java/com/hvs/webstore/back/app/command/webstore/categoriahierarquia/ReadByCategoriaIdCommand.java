package com.hvs.webstore.back.app.command.webstore.categoriahierarquia;

public record ReadByCategoriaIdCommand(Long aCategoriaId) {

    public static ReadByCategoriaIdCommand from(final Long aCategoriaId) {

        return new ReadByCategoriaIdCommand(aCategoriaId);
    }
}
