package com.hvs.webstore.back.app.usecase.television.grade;

import com.hvs.webstore.back.app.command.television.grade.ReadAllGradeCommand;
import com.hvs.webstore.back.app.output.television.grade.ReadAllGradeOutput;
import com.hvs.webstore.back.domain.entity.television.grade.Grade;
import com.hvs.webstore.back.domain.entity.television.grade.GradeDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;
import static io.vavr.API.Try;

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
                .stream().filter(corte -> corte.getStatusCode().getDesc().equals("Active")).toList();

        if (!lista.isEmpty()) {

            return Try(() -> this.gateway.readAll(aIn.aGradeSearchQuery()))
                    .toEither()
                    .bimap(Notification::create, ReadAllGradeOutput::from);
        } else {

            return Either.left(Notification
                    .create(new Error("No Grade was found.")));
        }
    }
}
