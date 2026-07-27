package com.hvs.webstore.back.infra.api.webstore;

import com.hvs.webstore.back.app.command.webstore.frete.*;
import com.hvs.webstore.back.app.usecase.webstore.frete.*;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/frete")
public class FreteApiController {

    private final ReadFreteUseCase readUseCase;
    private final ReadAllFreteUseCase readAllUseCase;
    private final CalcularFreteUseCase calcularUseCase;

    public FreteApiController(final ReadFreteUseCase aReadUseCase,
                              final ReadAllFreteUseCase aReadAllUseCase,
                              final CalcularFreteUseCase aCalcularUseCase) {

        this.readUseCase = aReadUseCase;
        this.readAllUseCase = aReadAllUseCase;
        this.calcularUseCase = aCalcularUseCase;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> readById(@PathVariable Long aId) {

        return readUseCase.execute(ReadFreteCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<?> readByUuid(@PathVariable String aUuid) {

        return readUseCase.execute(ReadFreteCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<?> readAll(@RequestParam(required = false) String aSearch,
                                     @RequestParam(defaultValue = "0") int aPage,
                                     @RequestParam(defaultValue = "10") int aSize,
                                     @RequestParam(defaultValue = "id") String aSort,
                                     @RequestParam(defaultValue = "asc") String aDirection) {

        return readAllUseCase.execute(new ReadAllFreteCommand(new SearchQuery(aSearch, aPage, aSize, aSort, aDirection)))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PostMapping("/calcular")
    public ResponseEntity<?> calcular(@RequestBody CalcularFreteCommand aCalcularFreteCommand) {

        return calcularUseCase.execute(aCalcularFreteCommand)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }
}
