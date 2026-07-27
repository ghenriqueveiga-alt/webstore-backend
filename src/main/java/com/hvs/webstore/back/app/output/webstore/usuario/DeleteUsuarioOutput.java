package com.hvs.webstore.back.app.output.webstore.usuario;

import com.hvs.webstore.back.domain.entity.webstore.usuario.Usuario;

public record DeleteUsuarioOutput(Long aId,
                                  String aUuid,
                                  String aMessage) {

    public static DeleteUsuarioOutput from(Usuario aUsuario) {

        return new DeleteUsuarioOutput(
                aUsuario.getId().getValue(),
                aUsuario.getUuid().getValue(),
                "The Usuario with id: " + aUsuario.getUuid().getValue() + " has been successfully deleted.");
    }
}
