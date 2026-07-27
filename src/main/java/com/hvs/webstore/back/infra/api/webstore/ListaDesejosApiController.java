package com.hvs.webstore.back.infra.api.webstore;

import com.hvs.webstore.back.app.command.webstore.listadesejos.*;
import com.hvs.webstore.back.app.usecase.webstore.listadesejos.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lista-desejos")
public class ListaDesejosApiController {

    private final CriarListaDesejosUseCase criarUseCase;
    private final VisualizarListaDesejosUseCase visualizarUseCase;
    private final ListarListasDesejosUseCase listarUseCase;
    private final AdicionarItemListaUseCase adicionarItemUseCase;
    private final RemoverItemListaUseCase removerItemUseCase;
    private final DeletarListaDesejosUseCase deletarUseCase;

    public ListaDesejosApiController(final CriarListaDesejosUseCase aCriarUseCase,
                                     final VisualizarListaDesejosUseCase aVisualizarUseCase,
                                     final ListarListasDesejosUseCase aListarUseCase,
                                     final AdicionarItemListaUseCase aAdicionarItemUseCase,
                                     final RemoverItemListaUseCase aRemoverItemUseCase,
                                     final DeletarListaDesejosUseCase aDeletarUseCase) {

        this.criarUseCase = aCriarUseCase;
        this.visualizarUseCase = aVisualizarUseCase;
        this.listarUseCase = aListarUseCase;
        this.adicionarItemUseCase = aAdicionarItemUseCase;
        this.removerItemUseCase = aRemoverItemUseCase;
        this.deletarUseCase = aDeletarUseCase;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody CriarListaDesejosCommand aCriarCommand) {

        return criarUseCase.execute(aCriarCommand)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> visualizar(@PathVariable String aUuid) {

        return visualizarUseCase.execute(aUuid)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<?> listar() {

        return listarUseCase.execute(null)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PostMapping("/{uuid}/item")
    public ResponseEntity<?> adicionarItem(@PathVariable String aUuid,
                                           @RequestBody AdicionarItemListaCommand aAdicionarCommand) {

        return adicionarItemUseCase.execute(AdicionarItemListaCommand.from(aUuid, aAdicionarCommand.produtoId()))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/{uuid}/item/{itemUuid}")
    public ResponseEntity<?> removerItem(@PathVariable String aUuid,
                                         @PathVariable String aItemUuid) {

        return removerItemUseCase.execute(RemoverItemListaCommand.from(aUuid, aItemUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deletar(@PathVariable String aUuid) {

        return deletarUseCase.execute(aUuid)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }
}
