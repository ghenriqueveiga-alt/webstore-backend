package com.hvs.webstore.back.infra.persistence.webstore.endereco;

import com.hvs.webstore.back.domain.entity.webstore.endereco.Endereco;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.usuario.UsuarioEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "endereco")
public class EnderecoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private Boolean principal;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    public EnderecoEntity() {

    }

    public EnderecoEntity(final Long id,
                          final String uuid,
                          final String statusDesc,
                          final UsuarioEntity usuario,
                          final String logradouro,
                          final String numero,
                          final String complemento,
                          final String bairro,
                          final String cidade,
                          final String estado,
                          final String cep,
                          final Boolean principal) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
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

    public static EnderecoEntity from(final Endereco aEndereco) {

        return new EnderecoEntity(
                aEndereco.getId().getValue() < 0 ? null : aEndereco.getId().getValue(),
                aEndereco.getUuid().getValue(),
                aEndereco.getStatusCode().getDesc(),
                aEndereco.getUsuario() != null ? UsuarioEntity.from(aEndereco.getUsuario().getId().getValue()) : null,
                aEndereco.getLogradouro(),
                aEndereco.getNumero(),
                aEndereco.getComplemento(),
                aEndereco.getBairro(),
                aEndereco.getCidade(),
                aEndereco.getEstado(),
                aEndereco.getCep(),
                aEndereco.getPrincipal()
        );
    }

    public static EnderecoEntity from(final Long aEnderecoID) {

        final var endereco = new EnderecoEntity();
        endereco.setId(aEnderecoID);

        return endereco;
    }

    public Endereco toDomain() {

        return Endereco.from(
                getId(),
                uuid,
                statusDesc,
                usuario != null ? usuario.toDomainChildren() : null,
                logradouro,
                numero,
                complemento,
                bairro,
                cidade,
                estado,
                cep,
                principal
        );
    }

    public Endereco toDomainChildren() {

        return Endereco.from(
                getId(),
                uuid,
                statusDesc,
                usuario != null ? usuario.toDomainSimple() : null,
                logradouro,
                numero,
                complemento,
                bairro,
                cidade,
                estado,
                cep,
                principal
        );
    }

    public Endereco toDomainSimple() {

        return Endereco.from(
                getId(),
                uuid,
                null,
                null,
                null,
                null,
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
    public UsuarioEntity getUsuario() {
        return usuario;
    }
}
