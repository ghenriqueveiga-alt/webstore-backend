package com.hvs.webstore.back.app.output.webstore.listadesejos;

import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejos;

import java.util.ArrayList;
import java.util.List;

public record ReadAllListaDesejosOutput(List<ListaDesejosOutput> listasDesejos) {

    public static ReadAllListaDesejosOutput from(List<ListaDesejos> aListaDesejos) {

        final List<ListaDesejosOutput> list = new ArrayList<>();

        for (ListaDesejos i : aListaDesejos)
            if (i.getStatusCode().getDesc().equals("Active"))
                list.add(ListaDesejosOutput.from(i));

        return new ReadAllListaDesejosOutput(list);
    }
}
