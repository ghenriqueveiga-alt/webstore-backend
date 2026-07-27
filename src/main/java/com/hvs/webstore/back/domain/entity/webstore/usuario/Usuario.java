package com.hvs.webstore.back.domain.entity.webstore.usuario;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Usuario extends Entity<UsuarioId> {

    private final UsuarioUuid uuid;
    private final UsuarioStatus statusCode;
    private final String nome;
    private final String email;
    private final String senha;
    private final String telefone;
    private final Instant dataCadastro;

    private Usuario(final UsuarioId id,
                    final UsuarioUuid uuid,
                    final UsuarioStatus statusCode,
                    final String nome,
                    final String email,
                    final String senha,
                    final String telefone,
                    final Instant dataCadastro) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
    }

    public static Usuario create(final String aNome,
                                  final String aEmail,
                                  final String aSenha,
                                  final String aTelefone,
                                  final Instant aDataCadastro) {

        return new Usuario(
                UsuarioId.from(-1L),
                UsuarioUuid.unique(),
                UsuarioStatus.ACTIVE,
                aNome,
                aEmail,
                aSenha,
                aTelefone,
                aDataCadastro != null ? aDataCadastro : Instant.now());
    }

    public static Usuario update(final Long aId,
                                  final String aUuid,
                                  final String aStatusCode,
                                  final String aNome,
                                  final String aEmail,
                                  final String aSenha,
                                  final String aTelefone,
                                  final Instant aDataCadastro) {

        return new Usuario(
                aId != null ? UsuarioId.from(aId) : null,
                aUuid != null ? UsuarioUuid.from(aUuid) : null,
                aStatusCode != null ? UsuarioStatus.findByCode(aStatusCode) : null,
                aNome,
                aEmail,
                aSenha,
                aTelefone,
                aDataCadastro);
    }

    public static Usuario patch(final String aStatusCode,
                                 final String aNome,
                                 final String aEmail,
                                 final String aSenha,
                                 final String aTelefone,
                                 final Instant aDataCadastro,
                                 final Usuario aExisting) {

        return new Usuario(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? UsuarioStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aNome != null ? aNome : aExisting.getNome(),
                aEmail != null ? aEmail : aExisting.getEmail(),
                aSenha != null ? aSenha : aExisting.getSenha(),
                aTelefone != null ? aTelefone : aExisting.getTelefone(),
                aDataCadastro != null ? aDataCadastro : aExisting.getDataCadastro());
    }

    public static Usuario from(final Long aId,
                                final String aUuid,
                                final String aStatusDesc,
                                final String aNome,
                                final String aEmail,
                                final String aSenha,
                                final String aTelefone,
                                final Instant aDataCadastro) {

        return new Usuario(
                aId != null ? UsuarioId.from(aId) : null,
                aUuid != null ? UsuarioUuid.from(aUuid) : null,
                aStatusDesc != null ? UsuarioStatus.findByDesc(aStatusDesc) : null,
                aNome,
                aEmail,
                aSenha,
                aTelefone,
                aDataCadastro);
    }

    public static Usuario from(final Long aId) {

        return new Usuario(
                aId != null ? UsuarioId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Usuario from(final String aUuid) {

        return new Usuario(
                null,
                aUuid != null ? UsuarioUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new UsuarioValidator(aHandler, this).validate();
    }

    public UsuarioUuid getUuid() {
        return uuid;
    }
    public UsuarioStatus getStatusCode() {
        return statusCode;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public String getSenha() {
        return senha;
    }
    public String getTelefone() {
        return telefone;
    }
    public Instant getDataCadastro() {
        return dataCadastro;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Usuario usuario = (Usuario) o;

        return Objects.equals(uuid, usuario.uuid) &&
                statusCode == usuario.statusCode &&
                Objects.equals(nome, usuario.nome) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(senha, usuario.senha) &&
                Objects.equals(telefone, usuario.telefone) &&
                Objects.equals(dataCadastro, usuario.dataCadastro);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                nome,
                email,
                senha,
                telefone,
                dataCadastro);
    }
}
