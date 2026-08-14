package com.hvs.webstore.back.app.usecase.television.grade;

import com.hvs.webstore.back.app.command.television.grade.PatchGradeCommand;
import com.hvs.webstore.back.app.output.television.grade.PatchGradeOutput;
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

public class PatchGradeUseCaseImpl extends PatchGradeUseCase {

    private final GradeDomainGateway gateway;

    public PatchGradeUseCaseImpl(
            GradeDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, PatchGradeOutput> execute(PatchGradeCommand aIn) {

        Optional<Grade> gradeDb;

        if (aIn.aId() != null) {

            gradeDb = this.gateway.read(GradeId.from(aIn.aId()));
        } else {

            gradeDb = this.gateway.readByUuid(GradeUuid.from(aIn.aUuid()));
        }

        if (gradeDb.isPresent()) {

            final var notification = Notification.create();
            final var grade = Grade.patch(aIn.aStatusDesc(),
                                          aIn.aNome(),
                                          aIn.aDescricao(),
                                          aIn.aBlocoIds(),
                                          aIn.aPeriodoInicio(),
                                          aIn.aPeriodoFim(),
                                          aIn.aGradeAtiva(),
                                          gradeDb.get());
            grade.validate(notification);

            return notification.hasError() ? Left(notification) : patch(grade);
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
    private Either<Notification, PatchGradeOutput> patch(final Grade aGrade) {

        return Try(() -> this.gateway.patch(aGrade))
                .toEither()
                .bimap(Notification::create, PatchGradeOutput::from);
    }
}
