package com.hvs.webstore.back.domain.entity.webstore.endereco;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.usuario.Usuario;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class Endereco extends Entity<EnderecoId> {

    private final EnderecoUuid uuid;
    private final EnderecoStatus statusCode;
    private final Usuario usuario;
    private final String logradouro;
    private final String numero;
    private final String complemento;
    private final String bairro;
    private final String cidade;
    private final String estado;
    private final String cep;
    private final Boolean principal;

    private Endereco(final EnderecoId id,
                     final EnderecoUuid uuid,
                     final EnderecoStatus statusCode,
                     final Usuario usuario,
                     final String logradouro,
                     final String numero,
                     final String complemento,
                     final String bairro,
                     final String cidade,
                     final String estado,
                     final String cep,
                     final Boolean principal) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.usuario = usuario;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.principal = principal;
    }

    public static Endereco create(final Long aUsuarioId,
                                  final String aLogradouro,
                                  final String aNumero,
                                  final String aComplemento,
                                  final String aBairro,
                                  final String aCidade,
                                  final String aEstado,
                                  final String aCep,
                                  final Boolean aPrincipal) {

        return new Endereco(
                EnderecoId.from(-1L),
                EnderecoUuid.unique(),
                EnderecoStatus.ACTIVE,
                aUsuarioId != null ? Usuario.from(aUsuarioId) : null,
                aLogradouro,
                aNumero,
                aComplemento,
                aBairro,
                aCidade,
                aEstado,
                aCep,
                aPrincipal);
    }

    public static Endereco update(final Long aId,
                                  final String aUuid,
                                  final String aStatusCode,
                                  final Long aUsuarioId,
                                  final String aLogradouro,
                                  final String aNumero,
                                  final String aComplemento,
                                  final String aBairro,
                                  final String aCidade,
                                  final String aEstado,
                                  final String aCep,
                                  final Boolean aPrincipal) {

        return new Endereco(
                aId != null ? EnderecoId.from(aId) : null,
                aUuid != null ? EnderecoUuid.from(aUuid) : null,
                aStatusCode != null ? EnderecoStatus.findByCode(aStatusCode) : null,
                aUsuarioId != null ? Usuario.from(aUsuarioId) : null,
                aLogradouro,
                aNumero,
                aComplemento,
                aBairro,
                aCidade,
                aEstado,
                aCep,
                aPrincipal);
    }

    public static Endereco patch(final String aStatusCode,
                                 final String aLogradouro,
                                 final String aNumero,
                                 final String aComplemento,
                                 final String aBairro,
                                 final String aCidade,
                                 final String aEstado,
                                 final String aCep,
                                 final Boolean aPrincipal,
                                 final Endereco aExisting) {

        return new Endereco(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? EnderecoStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aExisting.getUsuario(),
                aLogradouro != null ? aLogradouro : aExisting.getLogradouro(),
                aNumero != null ? aNumero : aExisting.getNumero(),
                aComplemento != null ? aComplemento : aExisting.getComplemento(),
                aBairro != null ? aBairro : aExisting.getBairro(),
                aCidade != null ? aCidade : aExisting.getCidade(),
                aEstado != null ? aEstado : aExisting.getEstado(),
                aCep != null ? aCep : aExisting.getCep(),
                aPrincipal != null ? aPrincipal : aExisting.getPrincipal());
    }

    public static Endereco from(final Long aId,
                                final String aUuid,
                                final String aStatusDesc,
                                final Usuario aUsuario,
                                final String aLogradouro,
                                final String aNumero,
                                final String aComplemento,
                                final String aBairro,
                                final String aCidade,
                                final String aEstado,
                                final String aCep,
                                final Boolean aPrincipal) {

        return new Endereco(
                aId != null ? EnderecoId.from(aId) : null,
                aUuid != null ? EnderecoUuid.from(aUuid) : null,
                aStatusDesc != null ? EnderecoStatus.findByDesc(aStatusDesc) : null,
                aUsuario,
                aLogradouro,
                aNumero,
                aComplemento,
                aBairro,
                aCidade,
                aEstado,
                aCep,
                aPrincipal);
    }

    public static Endereco from(final Long aId) {

        return new Endereco(
                aId != null ? EnderecoId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Endereco from(final String aUuid) {

        return new Endereco(
                null,
                aUuid != null ? EnderecoUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new EnderecoValidator(aHandler, this).validate();
    }

    public EnderecoUuid getUuid() {
        return uuid;
    }
    public EnderecoStatus getStatusCode() {
        return statusCode;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public String getNumero() {
        return numero;
    }
    public String getComplemento() {
        return complemento;
    }
    public String getBairro() {
        return bairro;
    }
    public String getCidade() {
        return cidade;
    }
    public String getEstado() {
        return estado;
    }
    public String getCep() {
        return cep;
    }
    public Boolean getPrincipal() {
        return principal;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Endereco endereco = (Endereco) o;

        return Objects.equals(uuid, endereco.uuid) &&
                statusCode == endereco.statusCode &&
                Objects.equals(usuario, endereco.usuario) &&
                Objects.equals(logradouro, endereco.logradouro) &&
                Objects.equals(numero, endereco.numero) &&
                Objects.equals(complemento, endereco.complemento) &&
                Objects.equals(bairro, endereco.bairro) &&
                Objects.equals(cidade, endereco.cidade) &&
                Objects.equals(estado, endereco.estado) &&
                Objects.equals(cep, endereco.cep) &&
                Objects.equals(principal, endereco.principal);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                usuario,
                logradouro,
                numero,
                complemento,
                bairro,
                cidade,
                estado,
                cep,
                principal);
    }
}
