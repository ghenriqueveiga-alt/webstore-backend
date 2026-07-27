package com.hvs.webstore.back.app.output.webstore.role;

import com.hvs.webstore.back.domain.entity.webstore.role.Role;

public record CreateRoleOutput(Long aId,
                               String aUuid,
                               String aMessage) {

    public static CreateRoleOutput from(Role aRole) {

        return new CreateRoleOutput(
                aRole.getId().getValue(),
                aRole.getUuid().getValue(),
                "Role created: " + aRole.getUuid().getValue());
    }
}
