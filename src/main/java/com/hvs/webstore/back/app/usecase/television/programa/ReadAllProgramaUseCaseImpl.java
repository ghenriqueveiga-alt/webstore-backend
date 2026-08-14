package com.hvs.webstore.back.app.usecase.television.programa;

import com.hvs.webstore.back.app.command.television.programa.ReadAllProgramaCommand;
import com.hvs.webstore.back.app.output.television.programa.ReadAllProgramaOutput;
import com.hvs.webstore.back.domain.entity.television.programa.Programa;
import com.hvs.webstore.back.domain.entity.television.programa.ProgramaDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;

public class ReadAllProgramaUseCaseImpl extends ReadAllProgramaUseCase {

    private final ProgramaDomainGateway gateway;

    public ReadAllProgramaUseCaseImpl(ProgramaDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllProgramaOutput> execute(ReadAllProgramaCommand aProgramaCommand) {

        Pagination<Programa> programaPagination = this.gateway.readAll(aProgramaCommand.aProgramaSearchQuery());
        List<Programa> lista = programaPagination.aContent()
                .stream().filter(corte -> corte.getStatus().getDesc().equals("Active")).toList();

        if (!lista.isEmpty()) {
            return Either.right(ReadAllProgramaOutput.from(Pagination.from(
                    programaPagination.aPageNumber(),
                    programaPagination.aTotalElements(),
                    programaPagination.aTotalPages(),
                    lista)));
        } else {
            return Either.left(Notification.create(new Error("No Program was found.")));
        }
    }
}
