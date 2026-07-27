package com.hvs.webstore.back.domain.entity.webstore.cartao;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.usuario.Usuario;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class Cartao extends Entity<CartaoId> {

    private final CartaoUuid uuid;
    private final CartaoStatus statusCode;
    private final String nomeTitular;
    private final String numero;
    private final String bandeira;
    private final String tipo;
    private final Integer mesVencimento;
    private final Integer anoVencimento;
    private final String cvv;
    private final Usuario usuario;

    private Cartao(final CartaoId id,
                   final CartaoUuid uuid,
                   final CartaoStatus statusCode,
                   final String nomeTitular,
                   final String numero,
                   final String bandeira,
                   final String tipo,
                   final Integer mesVencimento,
                   final Integer anoVencimento,
                   final String cvv,
                   final Usuario usuario) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.nomeTitular = nomeTitular;
        this.numero = numero;
        this.bandeira = bandeira;
        this.tipo = tipo;
        this.mesVencimento = mesVencimento;
        this.anoVencimento = anoVencimento;
        this.cvv = cvv;
        this.usuario = usuario;
    }

    public static Cartao create(final String aNomeTitular,
                                final String aNumero,
                                final String aBandeira,
                                final String aTipo,
                                final Integer aMesVencimento,
                                final Integer aAnoVencimento,
                                final String aCvv,
                                final Usuario aUsuario) {

        return new Cartao(
                CartaoId.from(-1L),
                CartaoUuid.unique(),
                CartaoStatus.ACTIVE,
                aNomeTitular,
                aNumero,
                aBandeira,
                aTipo,
                aMesVencimento,
                aAnoVencimento,
                aCvv,
                aUsuario);
    }

    public static Cartao update(final Long aId,
                                final String aUuid,
                                final String aStatusCode,
                                final String aNomeTitular,
                                final String aNumero,
                                final String aBandeira,
                                final String aTipo,
                                final Integer aMesVencimento,
                                final Integer aAnoVencimento,
                                final String aCvv,
                                final Usuario aUsuario) {

        return new Cartao(
                aId != null ? CartaoId.from(aId) : null,
                aUuid != null ? CartaoUuid.from(aUuid) : null,
                aStatusCode != null ? CartaoStatus.findByCode(aStatusCode) : null,
                aNomeTitular,
                aNumero,
                aBandeira,
                aTipo,
                aMesVencimento,
                aAnoVencimento,
                aCvv,
                aUsuario);
    }

    public static Cartao patch(final String aStatusCode,
                               final String aNomeTitular,
                               final String aNumero,
                               final String aBandeira,
                               final String aTipo,
                               final Integer aMesVencimento,
                               final Integer aAnoVencimento,
                               final String aCvv,
                               final Usuario aUsuario,
                               final Cartao aExisting) {

        return new Cartao(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? CartaoStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aNomeTitular != null ? aNomeTitular : aExisting.getNomeTitular(),
                aNumero != null ? aNumero : aExisting.getNumero(),
                aBandeira != null ? aBandeira : aExisting.getBandeira(),
                aTipo != null ? aTipo : aExisting.getTipo(),
                aMesVencimento != null ? aMesVencimento : aExisting.getMesVencimento(),
                aAnoVencimento != null ? aAnoVencimento : aExisting.getAnoVencimento(),
                aCvv != null ? aCvv : aExisting.getCvv(),
                aUsuario != null ? aUsuario : aExisting.getUsuario());
    }

    public static Cartao from(final Long aId,
                              final String aUuid,
                              final String aStatusDesc,
                              final String aNomeTitular,
                              final String aNumero,
                              final String aBandeira,
                              final String aTipo,
                              final Integer aMesVencimento,
                              final Integer aAnoVencimento,
                              final String aCvv,
                              final Usuario aUsuario) {

        return new Cartao(
                aId != null ? CartaoId.from(aId) : null,
                aUuid != null ? CartaoUuid.from(aUuid) : null,
                aStatusDesc != null ? CartaoStatus.findByDesc(aStatusDesc) : null,
                aNomeTitular,
                aNumero,
                aBandeira,
                aTipo,
                aMesVencimento,
                aAnoVencimento,
                aCvv,
                aUsuario);
    }

    public static Cartao from(final Long aId) {

        return new Cartao(
                aId != null ? CartaoId.from(aId) : null,
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

    public static Cartao from(final String aUuid) {

        return new Cartao(
                null,
                aUuid != null ? CartaoUuid.from(aUuid) : null,
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

        new CartaoValidator(aHandler, this).validate();
    }

    public CartaoUuid getUuid() {
        return uuid;
    }
    public CartaoStatus getStatusCode() {
        return statusCode;
    }
    public String getNomeTitular() {
        return nomeTitular;
    }
    public String getNumero() {
        return numero;
    }
    public String getBandeira() {
        return bandeira;
    }
    public String getTipo() {
        return tipo;
    }
    public Integer getMesVencimento() {
        return mesVencimento;
    }
    public Integer getAnoVencimento() {
        return anoVencimento;
    }
    public String getCvv() {
        return cvv;
    }
    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Cartao cartao = (Cartao) o;

        return Objects.equals(uuid, cartao.uuid) &&
                statusCode == cartao.statusCode &&
                Objects.equals(nomeTitular, cartao.nomeTitular) &&
                Objects.equals(numero, cartao.numero) &&
                Objects.equals(bandeira, cartao.bandeira) &&
                Objects.equals(tipo, cartao.tipo) &&
                Objects.equals(mesVencimento, cartao.mesVencimento) &&
                Objects.equals(anoVencimento, cartao.anoVencimento) &&
                Objects.equals(cvv, cartao.cvv) &&
                Objects.equals(usuario, cartao.usuario);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                nomeTitular,
                numero,
                bandeira,
                tipo,
                mesVencimento,
                anoVencimento,
                cvv,
                usuario);
    }
}
