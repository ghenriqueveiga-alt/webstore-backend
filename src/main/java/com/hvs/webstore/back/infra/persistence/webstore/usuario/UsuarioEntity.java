package com.hvs.webstore.back.infra.persistence.webstore.usuario;

import com.hvs.webstore.back.domain.entity.webstore.usuario.Usuario;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "usuario")
public class UsuarioEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private Instant dataCadastro;

    public UsuarioEntity() {

    }

    public UsuarioEntity(final Long id,
                         final String uuid,
                         final String statusDesc,
                         final String nome,
                         final String email,
                         final String senha,
                         final String telefone,
                         final Instant dataCadastro) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
    }

    public static UsuarioEntity from(final Usuario aUsuario) {

        return new UsuarioEntity(
                aUsuario.getId().getValue() < 0 ? null : aUsuario.getId().getValue(),
                aUsuario.getUuid().getValue(),
                aUsuario.getStatusCode().getDesc(),
                aUsuario.getNome(),
                aUsuario.getEmail(),
                aUsuario.getSenha(),
                aUsuario.getTelefone(),
                aUsuario.getDataCadastro()
        );
    }

    public static UsuarioEntity from(final Long aUsuarioID) {

        final var usuario = new UsuarioEntity();
        usuario.setId(aUsuarioID);

        return usuario;
    }

    public Usuario toDomain() {

        return Usuario.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                email,
                senha,
                telefone,
                dataCadastro
        );
    }

    public Usuario toDomainChildren() {

        return Usuario.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                email,
                senha,
                telefone,
                dataCadastro
        );
    }

    public Usuario toDomainSimple() {

        return Usuario.from(
                getId(),
                uuid,
                null,
                nome,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setStatusDesc(final String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
