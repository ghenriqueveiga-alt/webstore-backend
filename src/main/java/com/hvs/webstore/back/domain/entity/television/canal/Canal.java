package com.hvs.webstore.back.domain.entity.television.canal;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class Canal extends Entity<CanalId> {

    private final CanalUuid uuid;
    private final CanalStatus status;
    private final String nome;
    private final String descricao;
    private final String logotipoUrl;
    private final String site;

    private Canal(final CanalId id,
                  final CanalUuid uuid,
                  final CanalStatus status,
                  final String nome,
                  final String descricao,
                  final String logotipoUrl,
                  final String site) {

        super(id);
        this.uuid = uuid;
        this.status = status;
        this.nome = nome;
        this.descricao = descricao;
        this.logotipoUrl = logotipoUrl;
        this.site = site;
    }

    public static Canal create(final String aNome,
                               final String aDescricao,
                               final String aLogotipoUrl,
                               final String aSite) {

        return new Canal(
                CanalId.from(-1L),
                CanalUuid.unique(),
                CanalStatus.ACTIVE,
                aNome,
                aDescricao,
                aLogotipoUrl,
                aSite);
    }

    public static Canal update(final Long aId,
                               final String aUuid,
                               final String aStatusCode,
                               final String aNome,
                               final String aDescricao,
                               final String aLogotipoUrl,
                               final String aSite) {

        return new Canal(
                aId != null ? CanalId.from(aId) : null,
                aUuid != null ? CanalUuid.from(aUuid) : null,
                aStatusCode != null ? CanalStatus.findByCode(aStatusCode) : null,
                aNome,
                aDescricao,
                aLogotipoUrl,
                aSite);
    }

    public static Canal patch(final String aStatusCode,
                              final String aNome,
                              final String aDescricao,
                              final String aLogotipoUrl,
                              final String aSite,
                              final Canal aCanalDB) {

        return new Canal(
                aCanalDB.getId(),
                aCanalDB.getUuid(),
                aStatusCode != null ? CanalStatus.findByCode(aStatusCode) : aCanalDB.getStatus(),
                aNome != null ? aNome : aCanalDB.getNome(),
                aDescricao != null ? aDescricao : aCanalDB.getDescricao(),
                aLogotipoUrl != null ? aLogotipoUrl : aCanalDB.getLogotipoUrl(),
                aSite != null ? aSite : aCanalDB.getSite());
    }

    public static Canal from(final Long aId,
                             final String aUuid,
                             final String aStatusDesc,
                             final String aNome,
                             final String aDescricao,
                             final String aLogotipoUrl,
                             final String aSite) {

        return new Canal(
                aId != null ? CanalId.from(aId) : null,
                aUuid != null ? CanalUuid.from(aUuid) : null,
                aStatusDesc != null ? CanalStatus.findByDesc(aStatusDesc) : null,
                aNome,
                aDescricao,
                aLogotipoUrl,
                aSite);
    }

    public static Canal from(final Long aId) {

        return new Canal(
                aId != null ? CanalId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Canal from(final String aUuid) {

        return new Canal(
                null,
                aUuid != null ? CanalUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new CanalValidator(aHandler, this).validate();
    }

    public CanalUuid getUuid() { return uuid; }
    public CanalStatus getStatus() { return status; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public String getLogotipoUrl() { return logotipoUrl; }
    public String getSite() { return site; }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Canal canal = (Canal) o;

        return Objects.equals(uuid, canal.uuid) &&
                status == canal.status &&
                Objects.equals(nome, canal.nome) &&
                Objects.equals(descricao, canal.descricao) &&
                Objects.equals(logotipoUrl, canal.logotipoUrl) &&
                Objects.equals(site, canal.site);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                status,
                nome,
                descricao,
                logotipoUrl,
                site);
    }
}
