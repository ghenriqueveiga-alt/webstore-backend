package com.hvs.webstore.back.infra.api.webstore;

import com.hvs.webstore.back.app.command.webstore.precopromocional.*;
import com.hvs.webstore.back.app.usecase.webstore.precopromocional.*;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/preco-promocional")
public class PrecoPromocionalApiController {

    private final CreatePrecoPromocionalUseCase createUseCase;
    private final ReadPrecoPromocionalUseCase readUseCase;
    private final ReadAllPrecoPromocionalUseCase readAllUseCase;
    private final UpdatePrecoPromocionalUseCase updateUseCase;
    private final PatchPrecoPromocionalUseCase patchUseCase;
    private final DeletePrecoPromocionalUseCase deleteUseCase;

    public PrecoPromocionalApiController(final CreatePrecoPromocionalUseCase aCreateUseCase,
                                         final ReadPrecoPromocionalUseCase aReadUseCase,
                                         final ReadAllPrecoPromocionalUseCase aReadAllUseCase,
                                         final UpdatePrecoPromocionalUseCase aUpdateUseCase,
                                         final PatchPrecoPromocionalUseCase aPatchUseCase,
                                         final DeletePrecoPromocionalUseCase aDeleteUseCase) {

        this.createUseCase = aCreateUseCase;
        this.readUseCase = aReadUseCase;
        this.readAllUseCase = aReadAllUseCase;
        this.updateUseCase = aUpdateUseCase;
        this.patchUseCase = aPatchUseCase;
        this.deleteUseCase = aDeleteUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreatePrecoPromocionalCommand aCreatePrecoPromocionalCommand) {

        return createUseCase.execute(aCreatePrecoPromocionalCommand)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> readById(@PathVariable Long aId) {

        return readUseCase.execute(ReadPrecoPromocionalCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<?> readByUuid(@PathVariable String aUuid) {

        return readUseCase.execute(ReadPrecoPromocionalCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<?> readAll(@RequestParam(required = false) String aSearch,
                                     @RequestParam(defaultValue = "0") int aPage,
                                     @RequestParam(defaultValue = "10") int aSize,
                                     @RequestParam(defaultValue = "id") String aSort,
                                     @RequestParam(defaultValue = "asc") String aDirection) {

        return readAllUseCase.execute(new ReadAllPrecoPromocionalCommand(new SearchQuery(aSearch, aPage, aSize, aSort, aDirection)))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long aId,
                                        @RequestBody UpdatePrecoPromocionalCommand aUpdatePrecoPromocionalCommand) {

        return updateUseCase.execute(UpdatePrecoPromocionalCommand.from(aId, aUpdatePrecoPromocionalCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/uuid/{uuid}")
    public ResponseEntity<?> updateByUuid(@PathVariable String aUuid,
                                          @RequestBody UpdatePrecoPromocionalCommand aUpdatePrecoPromocionalCommand) {

        return updateUseCase.execute(UpdatePrecoPromocionalCommand.from(aUuid, aUpdatePrecoPromocionalCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<?> patchById(@PathVariable Long aId,
                                       @RequestBody PatchPrecoPromocionalCommand aPatchPrecoPromocionalCommand) {

        return patchUseCase.execute(PatchPrecoPromocionalCommand.from(aId, aPatchPrecoPromocionalCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/uuid/{uuid}")
    public ResponseEntity<?> patchByUuid(@PathVariable String aUuid,
                                         @RequestBody PatchPrecoPromocionalCommand aPatchPrecoPromocionalCommand) {

        return patchUseCase.execute(PatchPrecoPromocionalCommand.from(aUuid, aPatchPrecoPromocionalCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long aId) {

        return deleteUseCase.execute(DeletePrecoPromocionalCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<?> deleteByUuid(@PathVariable String aUuid) {

        return deleteUseCase.execute(DeletePrecoPromocionalCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }
}
