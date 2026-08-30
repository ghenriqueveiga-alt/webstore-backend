package com.hvs.webstore.back.app.usecase.television.grade;

import com.hvs.webstore.back.app.command.television.grade.UpdateGradeCommand;
import com.hvs.webstore.back.app.output.television.grade.UpdateGradeOutput;
import com.hvs.webstore.back.domain.entity.television.grade.Grade;
import com.hvs.webstore.back.domain.entity.television.grade.GradeDomainGateway;
import com.hvs.webstore.back.domain.entity.television.grade.GradeId;
import com.hvs.webstore.back.domain.entity.television.grade.GradeUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateGradeUseCaseImpl extends UpdateGradeUseCase {

    private final GradeDomainGateway gateway;

    public UpdateGradeUseCaseImpl(
            GradeDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, UpdateGradeOutput> execute(UpdateGradeCommand aIn) {

        Optional<Grade> gradeDb;

        if (aIn.aId() != null) {

            gradeDb = this.gateway.read(GradeId.from(aIn.aId()));
        } else {

            gradeDb = this.gateway.readByUuid(GradeUuid.from(aIn.aUuid()));
        }

        if (gradeDb.isPresent()) {

            final var notification = Notification.create();
            final var grade = Grade.update(gradeDb.get().getId().getValue(),
                                           gradeDb.get().getUuid().getValue(),
                                           aIn.aStatusCode(),
                                           aIn.aNome(),
                                           aIn.aDescricao(),
                                           aIn.aBlocoIds(),
                                           aIn.aPeriodoInicio(),
                                           aIn.aPeriodoFim(),
                                           aIn.aGradeAtiva());
            grade.validate(notification);

            return notification.hasError() ? Left(notification) : update(grade);
        } else {

            String responseId;

            if (aIn.aId() != null) {

                responseId = String.valueOf(aIn.aId());
            } else {

                responseId = aIn.aUuid();
            }

            return Either.left(Notification
                    .create(new Error("The Grade with id: " + responseId + " could not be found.")));
        }
    }

    @Transactional
    private Either<Notification, UpdateGradeOutput> update(final Grade aGrade){

        return Try(() -> this.gateway.update(aGrade))
                .toEither()
                .bimap(Notification::create, UpdateGradeOutput::from);
    }
}
