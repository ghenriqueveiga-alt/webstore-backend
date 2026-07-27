package com.hvs.webstore.back.infra.persistence.webstore.notificacaotemplate;

import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplate;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "notificacao_template")
public class NotificacaoTemplateEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String nome;
    private String tipoDesc;
    private String assunto;
    private String corpo;
    private String variaveis;

    public NotificacaoTemplateEntity() {

    }

    public NotificacaoTemplateEntity(final Long id,
                                     final String uuid,
                                     final String statusDesc,
                                     final String nome,
                                     final String tipoDesc,
                                     final String assunto,
                                     final String corpo,
                                     final String variaveis) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.nome = nome;
        this.tipoDesc = tipoDesc;
        this.assunto = assunto;
        this.corpo = corpo;
        this.variaveis = variaveis;
    }

    public static NotificacaoTemplateEntity from(NotificacaoTemplate aNotificacaoTemplate) {

        return new NotificacaoTemplateEntity(
                aNotificacaoTemplate.getId().getValue() < 0 ? null : aNotificacaoTemplate.getId().getValue(),
                aNotificacaoTemplate.getUuid().getValue(),
                aNotificacaoTemplate.getStatusCode().getDesc(),
                aNotificacaoTemplate.getNome(),
                aNotificacaoTemplate.getTipo().getDesc(),
                aNotificacaoTemplate.getAssunto(),
                aNotificacaoTemplate.getCorpo(),
                aNotificacaoTemplate.getVariaveis()
        );
    }

    public static NotificacaoTemplateEntity from(final Long aNotificacaoTemplateId) {

        final var notificacaoTemplate = new NotificacaoTemplateEntity();
        notificacaoTemplate.setId(aNotificacaoTemplateId);

        return notificacaoTemplate;
    }

    public NotificacaoTemplate toDomain() {

        return NotificacaoTemplate.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                tipoDesc,
                assunto,
                corpo,
                variaveis
        );
    }

    public NotificacaoTemplate toDomainChildren() {

        return NotificacaoTemplate.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                tipoDesc,
                assunto,
                corpo,
                variaveis
        );
    }

    public NotificacaoTemplate toDomainSimple() {

        return NotificacaoTemplate.from(
                getId(),
                uuid,
                null,
                null,
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
