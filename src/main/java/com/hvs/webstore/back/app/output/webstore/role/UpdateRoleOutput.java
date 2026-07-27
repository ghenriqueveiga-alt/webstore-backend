package com.hvs.webstore.back.app.output.webstore.role;

import com.hvs.webstore.back.domain.entity.webstore.role.Role;

public record UpdateRoleOutput(Long aId,
                               String aUuid,
                               String aMessage) {

    public static UpdateRoleOutput from(Role aRole) {

        return new UpdateRoleOutput(
                aRole.getId().getValue(),
                aRole.getUuid().getValue(),
                "Role updated: " + aRole.getUuid().getValue());
    }
}
