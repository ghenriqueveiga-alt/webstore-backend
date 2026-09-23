package com.hvs.ws.back.app.usecase.grade;

import com.hvs.ws.back.app.command.grade.ReadAllGradeCommand;
import com.hvs.ws.back.app.output.grade.ReadAllGradeOutput;
import com.hvs.ws.back.domain.entity.grade.Grade;
import com.hvs.ws.back.domain.entity.grade.GradeDomainGateway;
import com.hvs.ws.back.domain.pagination.Pagination;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;

public class ReadAllGradeUseCaseImpl extends ReadAllGradeUseCase {

    private final GradeDomainGateway gateway;

    public ReadAllGradeUseCaseImpl(
            GradeDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllGradeOutput> execute(ReadAllGradeCommand aIn) {

        Pagination<Grade> gradePagination = this.gateway.readAll(aIn.aGradeSearchQuery());
        List<Grade> lista = gradePagination.aContent()
                .stream().filter(corte -> corte.getStatus() != null && corte.getStatus().getDesc().equals("Active")).toList();

        if (!lista.isEmpty()) {

            return Either.right(ReadAllGradeOutput.from(Pagination.from(
                    gradePagination.aPageNumber(),
                    gradePagination.aTotalElements(),
                    gradePagination.aTotalPages(),
                    lista)));
        } else {

            return Either.left(Notification
                    .create(new Error("No Grade was found.")));
        }
    }
}
