package com.hvs.webstore.back.app.usecase.television.grade;

import com.hvs.webstore.back.app.command.television.grade.CreateGradeCommand;
import com.hvs.webstore.back.app.output.television.grade.CreateGradeOutput;
import com.hvs.webstore.back.domain.entity.television.grade.Grade;
import com.hvs.webstore.back.domain.entity.television.grade.GradeDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import static io.vavr.API.Try;

public class CreateGradeUseCaseImpl extends CreateGradeUseCase {

    private final GradeDomainGateway gateway;

    public CreateGradeUseCaseImpl(
            GradeDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, CreateGradeOutput> execute(CreateGradeCommand aIn) {

        final var notification = Notification.create();
        final var grade = Grade.create(aIn.aNome(),
                                       aIn.aDescricao(),
                                       aIn.aBlocoIds(),
                                       aIn.aPeriodoInicio(),
                                       aIn.aPeriodoFim(),
                                       aIn.aGradeAtiva());
        grade.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(grade);
    }

    @Transactional
    private Either<Notification, CreateGradeOutput> create(final Grade aGrade){

        return Try(() -> this.gateway.create(aGrade))
                .toEither()
                .bimap(Notification::create, CreateGradeOutput::from);
    }
}
