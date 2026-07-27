package com.hvs.webstore.back.infra.api.webstore;

import com.hvs.webstore.back.app.command.webstore.tokenverificacao.*;
import com.hvs.webstore.back.app.usecase.webstore.tokenverificacao.*;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/token-verificacao")
public class TokenVerificacaoApiController {

    private final CreateTokenVerificacaoUseCase createUseCase;
    private final ReadTokenVerificacaoUseCase readUseCase;
    private final ReadAllTokenVerificacaoUseCase readAllUseCase;
    private final UpdateTokenVerificacaoUseCase updateUseCase;
    private final PatchTokenVerificacaoUseCase patchUseCase;
    private final DeleteTokenVerificacaoUseCase deleteUseCase;

    public TokenVerificacaoApiController(final CreateTokenVerificacaoUseCase aCreateUseCase,
                                         final ReadTokenVerificacaoUseCase aReadUseCase,
                                         final ReadAllTokenVerificacaoUseCase aReadAllUseCase,
                                         final UpdateTokenVerificacaoUseCase aUpdateUseCase,
                                         final PatchTokenVerificacaoUseCase aPatchUseCase,
                                         final DeleteTokenVerificacaoUseCase aDeleteUseCase) {

        this.createUseCase = aCreateUseCase;
        this.readUseCase = aReadUseCase;
        this.readAllUseCase = aReadAllUseCase;
        this.updateUseCase = aUpdateUseCase;
        this.patchUseCase = aPatchUseCase;
        this.deleteUseCase = aDeleteUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateTokenVerificacaoCommand aCreateTokenVerificacaoCommand) {

        return createUseCase.execute(aCreateTokenVerificacaoCommand)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> readById(@PathVariable Long aId) {

        return readUseCase.execute(ReadTokenVerificacaoCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<?> readByUuid(@PathVariable String aUuid) {

        return readUseCase.execute(ReadTokenVerificacaoCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<?> readAll(@RequestParam(required = false) String aSearch,
                                     @RequestParam(defaultValue = "0") int aPage,
                                     @RequestParam(defaultValue = "10") int aSize,
                                     @RequestParam(defaultValue = "id") String aSort,
                                     @RequestParam(defaultValue = "asc") String aDirection) {

        return readAllUseCase.execute(new ReadAllTokenVerificacaoCommand(new SearchQuery(aSearch, aPage, aSize, aSort, aDirection)))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long aId,
                                        @RequestBody UpdateTokenVerificacaoCommand aUpdateTokenVerificacaoCommand) {

        return updateUseCase.execute(UpdateTokenVerificacaoCommand.from(aId, aUpdateTokenVerificacaoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/uuid/{uuid}")
    public ResponseEntity<?> updateByUuid(@PathVariable String aUuid,
                                          @RequestBody UpdateTokenVerificacaoCommand aUpdateTokenVerificacaoCommand) {

        return updateUseCase.execute(UpdateTokenVerificacaoCommand.from(aUuid, aUpdateTokenVerificacaoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<?> patchById(@PathVariable Long aId,
                                       @RequestBody PatchTokenVerificacaoCommand aPatchTokenVerificacaoCommand) {

        return patchUseCase.execute(PatchTokenVerificacaoCommand.from(aId, aPatchTokenVerificacaoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/uuid/{uuid}")
    public ResponseEntity<?> patchByUuid(@PathVariable String aUuid,
                                         @RequestBody PatchTokenVerificacaoCommand aPatchTokenVerificacaoCommand) {

        return patchUseCase.execute(PatchTokenVerificacaoCommand.from(aUuid, aPatchTokenVerificacaoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long aId) {

        return deleteUseCase.execute(DeleteTokenVerificacaoCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<?> deleteByUuid(@PathVariable String aUuid) {

        return deleteUseCase.execute(DeleteTokenVerificacaoCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }
}
