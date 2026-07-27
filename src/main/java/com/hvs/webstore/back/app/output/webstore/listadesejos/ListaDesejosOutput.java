package com.hvs.webstore.back.app.output.webstore.listadesejos;

import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ItemListaDesejos;
import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public record ListaDesejosOutput(Long id,
                                 String uuid,
                                 String status,
                                 Long usuarioId,
                                 List<ItemListaDesejosOutput> items,
                                 Instant criadoEm,
                                 Instant atualizadoEm) {

    public static ListaDesejosOutput from(ListaDesejos aListaDesejos) {

        final List<ItemListaDesejosOutput> itemOutputs = new ArrayList<>();

        for (var i : aListaDesejos.getItems()) {
            itemOutputs.add(ItemListaDesejosOutput.from(i));
        }

        return new ListaDesejosOutput(
                aListaDesejos.getId().getValue(),
                aListaDesejos.getUuid().getValue(),
                aListaDesejos.getStatusCode().getDesc(),
                aListaDesejos.getUsuario() != null ? aListaDesejos.getUsuario().getId().getValue() : null,
                itemOutputs,
                aListaDesejos.getCriadoEm(),
                aListaDesejos.getAtualizadoEm());
    }

    public record ItemListaDesejosOutput(String uuid,
                                         String status,
                                         Long produtoId,
                                         Instant adicionadoEm) {

        public static ItemListaDesejosOutput from(ItemListaDesejos aItemListaDesejos) {

            return new ItemListaDesejosOutput(
                    aItemListaDesejos.getUuid().getValue(),
                    aItemListaDesejos.getStatusCode().getDesc(),
                    aItemListaDesejos.getProduto() != null ? aItemListaDesejos.getProduto().getId().getValue() : null,
                    aItemListaDesejos.getAdicionadoEm());
        }
    }
}
