package org.example;

import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Theory;

import java.io.InputStream;

public class Connection {
    private final Prolog engine = new Prolog();

    public Connection() throws Exception {
        try (InputStream in = getClass().getResourceAsStream("/aritmetica.pl")) {
            if (in == null) throw new IllegalStateException("No se encontró aritmetica.pl");
            engine.setTheory(new Theory(in));
        }
    }

    public String consultar(String predicado, String a, String b) throws Exception {
        SolveInfo info = engine.solve(predicado + "(" + a + ", " + b + ", R).");
        return info.isSuccess() ? info.getTerm("R").toString()
                : "Sin solución para esos valores";
    }
}
