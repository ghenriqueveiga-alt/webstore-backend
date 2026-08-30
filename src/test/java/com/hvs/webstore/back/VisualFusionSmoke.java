package com.hvs.webstore.back;

import com.hvs.webstore.back.infra.media.VisualFusion;

import java.util.ArrayList;
import java.util.List;

public class VisualFusionSmoke {

    public static void main(String[] args) {

        final VisualFusion fusion = new VisualFusion();

        final String tj41 = "F:\\Tom e Jerry - Incompleto\\Ep.41.Tom&Jerry.CP.PradO.avi";
        final String tj99 = "F:\\Tom e Jerry - Incompleto\\Ep.99.Tom&Jerry.CP.PradO.avi";
        final String av5 = "F:\\Avatar\\2_Korra\\2\u00aa Temporada\\05_Mantenedores da Paz.mp4";
        final String av6 = "F:\\Avatar\\2_Korra\\2\u00aa Temporada\\06_Opera\u00e7\u00e3o Disfar\u00e7ada.mp4";
        final String cd2 = "F:\\Os Cavaleiros do Zod\u00edaco\\6_Hades\\1_Santu\u00e1rio\\02_O Lamento dos Tr\u00eas.mp4";
        final String cd9 = "F:\\Os Cavaleiros do Zod\u00edaco\\6_Hades\\1_Santu\u00e1rio\\09_Al\u00e9m do orgulho.mp4";

        System.out.println("== Caso A: Tom e Jerry (trilha incidental, deve FALHAR) ==");
        testar(fusion, List.of(new VisualFusion.VisualCandidato(tj41, 3L, 238.0, 0.92, true),
                new VisualFusion.VisualCandidato(tj99, 4L, 238.0, 0.91, true)));

        System.out.println("== Caso B: Avatar Korra ep5/ep6 (duplicatas, deve FALHAR) ==");
        testar(fusion, List.of(new VisualFusion.VisualCandidato(av5, 0L, 242.0, 0.95, true),
                new VisualFusion.VisualCandidato(av6, 0L, 242.0, 0.94, true)));

        System.out.println("== Caso C: Cavaleiros Hades (legitimo, deve PASSAR) ==");
        testar(fusion, List.of(new VisualFusion.VisualCandidato(cd2, 8L, 202.0, 0.90, true),
                new VisualFusion.VisualCandidato(cd9, 8L, 202.0, 0.88, true)));

        System.out.println("== Caso D: One Piece 239/244 (op legita com inicios variados, deve PASSAR) ==");
        testar(fusion, List.of(
                new VisualFusion.VisualCandidato("F:\\One Piece - Incompleto\\one-piece-dublado-ep-239.mp4", 185L, 58.0, 0.85, true),
                new VisualFusion.VisualCandidato("F:\\One Piece - Incompleto\\one-piece-dublado-ep-244.mp4", 203L, 58.0, 0.88, true)));
    }

    private static void testar(final VisualFusion aFusion, final List<VisualFusion.VisualCandidato> aIn) {

        final List<VisualFusion.VisualCandidato> out = aFusion.aplicar(new ArrayList<>(aIn));
        for (VisualFusion.VisualCandidato c : out) {
            System.out.println("  " + c.caminho() + (c.detectado()
                    ? "  -> DETECTADO inicio=" + c.inicio() + " dur=" + Math.round(c.dur()) + "s conf=" + String.format("%.2f", c.conf())
                    : "  -> REJEITADO (nao detectado)"));
        }
    }
}