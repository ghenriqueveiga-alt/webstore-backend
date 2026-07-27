package com.hvs.webstore.back.app.command.webstore.role;

public record DeleteRoleCommand(Long aId,
                                String aUuid) {

    public static DeleteRoleCommand from(final Long aId) {

        return new DeleteRoleCommand(
                aId,
                null);
    }

    public static DeleteRoleCommand from(final String aUuid) {

        return new DeleteRoleCommand(
                null,
                aUuid);
    }
}
