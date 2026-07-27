package com.hvs.webstore.back.infra.api.webstore;

import com.hvs.webstore.back.app.command.webstore.historicopedido.*;
import com.hvs.webstore.back.app.usecase.webstore.historicopedido.*;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/historico-pedido")
public class HistoricoPedidoApiController {

    private final CreateHistoricoPedidoUseCase createUseCase;
    private final ReadHistoricoPedidoUseCase readUseCase;
    private final ReadAllHistoricoPedidoUseCase readAllUseCase;
    private final ReadHistoricoPedidoByPedidoIdUseCase readByPedidoIdUseCase;

    public HistoricoPedidoApiController(final CreateHistoricoPedidoUseCase aCreateUseCase,
                                        final ReadHistoricoPedidoUseCase aReadUseCase,
                                        final ReadAllHistoricoPedidoUseCase aReadAllUseCase,
                                        final ReadHistoricoPedidoByPedidoIdUseCase aReadByPedidoIdUseCase) {

        this.createUseCase = aCreateUseCase;
        this.readUseCase = aReadUseCase;
        this.readAllUseCase = aReadAllUseCase;
        this.readByPedidoIdUseCase = aReadByPedidoIdUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateHistoricoPedidoCommand aCreateHistoricoPedidoCommand) {

        return createUseCase.execute(aCreateHistoricoPedidoCommand)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> readById(@PathVariable Long aId) {

        return readUseCase.execute(ReadHistoricoPedidoCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<?> readByUuid(@PathVariable String aUuid) {

        return readUseCase.execute(ReadHistoricoPedidoCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<?> readAll(@RequestParam(required = false) String aSearch,
                                     @RequestParam(defaultValue = "0") int aPage,
                                     @RequestParam(defaultValue = "10") int aSize,
                                     @RequestParam(defaultValue = "id") String aSort,
                                     @RequestParam(defaultValue = "asc") String aDirection) {

        return readAllUseCase.execute(new ReadAllHistoricoPedidoCommand(new SearchQuery(aSearch, aPage, aSize, aSort, aDirection)))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PostMapping("/pedido/{pedidoId}")
    public ResponseEntity<?> readByPedidoId(@PathVariable Long aPedidoId) {

        return readByPedidoIdUseCase.execute(ReadHistoricoPedidoByPedidoIdCommand.from(aPedidoId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }
}
