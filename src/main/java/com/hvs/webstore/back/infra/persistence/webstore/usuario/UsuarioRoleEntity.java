package com.hvs.webstore.back.infra.persistence.webstore.usuario;

import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.role.RoleEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario_role")
public class UsuarioRoleEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    public UsuarioRoleEntity() {

    }

    public UsuarioRoleEntity(final Long id,
                             final UsuarioEntity usuario,
                             final RoleEntity role) {

        this.id = id;
        this.usuario = usuario;
        this.role = role;
    }

    @Override
    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }

    public RoleEntity getRole() {
        return role;
    }
}
