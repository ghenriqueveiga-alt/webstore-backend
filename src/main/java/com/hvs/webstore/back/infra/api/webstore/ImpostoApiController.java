package com.hvs.webstore.back.infra.api.webstore;

import com.hvs.webstore.back.app.command.webstore.imposto.*;
import com.hvs.webstore.back.app.usecase.webstore.imposto.*;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/imposto")
public class ImpostoApiController {

    private final CreateImpostoUseCase createUseCase;
    private final ReadImpostoUseCase readUseCase;
    private final ReadAllImpostoUseCase readAllUseCase;
    private final UpdateImpostoUseCase updateUseCase;
    private final PatchImpostoUseCase patchUseCase;
    private final DeleteImpostoUseCase deleteUseCase;

    public ImpostoApiController(final CreateImpostoUseCase aCreateUseCase,
                                final ReadImpostoUseCase aReadUseCase,
                                final ReadAllImpostoUseCase aReadAllUseCase,
                                final UpdateImpostoUseCase aUpdateUseCase,
                                final PatchImpostoUseCase aPatchUseCase,
                                final DeleteImpostoUseCase aDeleteUseCase) {

        this.createUseCase = aCreateUseCase;
        this.readUseCase = aReadUseCase;
        this.readAllUseCase = aReadAllUseCase;
        this.updateUseCase = aUpdateUseCase;
        this.patchUseCase = aPatchUseCase;
        this.deleteUseCase = aDeleteUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateImpostoCommand aCreateImpostoCommand) {

        return createUseCase.execute(aCreateImpostoCommand)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> readById(@PathVariable Long aId) {

        return readUseCase.execute(ReadImpostoCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<?> readByUuid(@PathVariable String aUuid) {

        return readUseCase.execute(ReadImpostoCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<?> readAll(@RequestParam(required = false) String aSearch,
                                     @RequestParam(defaultValue = "0") int aPage,
                                     @RequestParam(defaultValue = "10") int aSize,
                                     @RequestParam(defaultValue = "id") String aSort,
                                     @RequestParam(defaultValue = "asc") String aDirection) {

        return readAllUseCase.execute(new ReadAllImpostoCommand(new SearchQuery(aSearch, aPage, aSize, aSort, aDirection)))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long aId,
                                        @RequestBody UpdateImpostoCommand aUpdateImpostoCommand) {

        return updateUseCase.execute(UpdateImpostoCommand.from(aId, aUpdateImpostoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/uuid/{uuid}")
    public ResponseEntity<?> updateByUuid(@PathVariable String aUuid,
                                          @RequestBody UpdateImpostoCommand aUpdateImpostoCommand) {

        return updateUseCase.execute(UpdateImpostoCommand.from(aUuid, aUpdateImpostoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<?> patchById(@PathVariable Long aId,
                                       @RequestBody PatchImpostoCommand aPatchImpostoCommand) {

        return patchUseCase.execute(PatchImpostoCommand.from(aId, aPatchImpostoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/uuid/{uuid}")
    public ResponseEntity<?> patchByUuid(@PathVariable String aUuid,
                                         @RequestBody PatchImpostoCommand aPatchImpostoCommand) {

        return patchUseCase.execute(PatchImpostoCommand.from(aUuid, aPatchImpostoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long aId) {

        return deleteUseCase.execute(DeleteImpostoCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<?> deleteByUuid(@PathVariable String aUuid) {

        return deleteUseCase.execute(DeleteImpostoCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }
}
