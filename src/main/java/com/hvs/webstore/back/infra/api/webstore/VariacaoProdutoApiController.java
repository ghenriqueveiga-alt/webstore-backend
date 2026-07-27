package com.hvs.webstore.back.infra.api.webstore;

import com.hvs.webstore.back.app.command.webstore.variacaoproduto.*;
import com.hvs.webstore.back.app.usecase.webstore.variacaoproduto.*;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/variacao-produto")
public class VariacaoProdutoApiController {

    private final CreateVariacaoProdutoUseCase createUseCase;
    private final ReadVariacaoProdutoUseCase readUseCase;
    private final ReadAllVariacaoProdutoUseCase readAllUseCase;
    private final UpdateVariacaoProdutoUseCase updateUseCase;
    private final PatchVariacaoProdutoUseCase patchUseCase;
    private final DeleteVariacaoProdutoUseCase deleteUseCase;

    public VariacaoProdutoApiController(final CreateVariacaoProdutoUseCase aCreateUseCase,
                                        final ReadVariacaoProdutoUseCase aReadUseCase,
                                        final ReadAllVariacaoProdutoUseCase aReadAllUseCase,
                                        final UpdateVariacaoProdutoUseCase aUpdateUseCase,
                                        final PatchVariacaoProdutoUseCase aPatchUseCase,
                                        final DeleteVariacaoProdutoUseCase aDeleteUseCase) {

        this.createUseCase = aCreateUseCase;
        this.readUseCase = aReadUseCase;
        this.readAllUseCase = aReadAllUseCase;
        this.updateUseCase = aUpdateUseCase;
        this.patchUseCase = aPatchUseCase;
        this.deleteUseCase = aDeleteUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateVariacaoProdutoCommand aCreateVariacaoProdutoCommand) {

        return createUseCase.execute(aCreateVariacaoProdutoCommand)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> readById(@PathVariable Long aId) {

        return readUseCase.execute(ReadVariacaoProdutoCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<?> readByUuid(@PathVariable String aUuid) {

        return readUseCase.execute(ReadVariacaoProdutoCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<?> readAll(@RequestParam(required = false) String aSearch,
                                     @RequestParam(defaultValue = "0") int aPage,
                                     @RequestParam(defaultValue = "10") int aSize,
                                     @RequestParam(defaultValue = "id") String aSort,
                                     @RequestParam(defaultValue = "asc") String aDirection) {

        return readAllUseCase.execute(new ReadAllVariacaoProdutoCommand(new SearchQuery(aSearch, aPage, aSize, aSort, aDirection)))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long aId,
                                        @RequestBody UpdateVariacaoProdutoCommand aUpdateVariacaoProdutoCommand) {

        return updateUseCase.execute(UpdateVariacaoProdutoCommand.from(aId, aUpdateVariacaoProdutoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/uuid/{uuid}")
    public ResponseEntity<?> updateByUuid(@PathVariable String aUuid,
                                          @RequestBody UpdateVariacaoProdutoCommand aUpdateVariacaoProdutoCommand) {

        return updateUseCase.execute(UpdateVariacaoProdutoCommand.from(aUuid, aUpdateVariacaoProdutoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<?> patchById(@PathVariable Long aId,
                                       @RequestBody PatchVariacaoProdutoCommand aPatchVariacaoProdutoCommand) {

        return patchUseCase.execute(PatchVariacaoProdutoCommand.from(aId, aPatchVariacaoProdutoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/uuid/{uuid}")
    public ResponseEntity<?> patchByUuid(@PathVariable String aUuid,
                                         @RequestBody PatchVariacaoProdutoCommand aPatchVariacaoProdutoCommand) {

        return patchUseCase.execute(PatchVariacaoProdutoCommand.from(aUuid, aPatchVariacaoProdutoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long aId) {

        return deleteUseCase.execute(DeleteVariacaoProdutoCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<?> deleteByUuid(@PathVariable String aUuid) {

        return deleteUseCase.execute(DeleteVariacaoProdutoCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }
}
