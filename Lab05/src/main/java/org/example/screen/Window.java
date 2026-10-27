package org.example.screen;

import org.example.Connection;
import org.example.screen.components.Buttons;
import org.example.screen.components.PanelResult;
import org.example.screen.components.TextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.StringSelection;

/** Ventana principal: arma los componentes y conecta la interfaz con el motor Prolog. */
public class Window extends JFrame {

    private static final String[][] EJEMPLOS = {
            {"12", "4"}, {"7", "2"}, {"-5", "3"}, {"2.5", "4"}
    };

    private final TextField txtA = new TextField("Ej: 12");
    private final TextField txtB = new TextField("Ej: 4");
    private final PanelResult panelResultado = new PanelResult();
    private final JLabel lblEstado = new JLabel(" ");

    public Window(Connection motor) {
        super("Calculadora básica en Prolog");

        txtA.addActionListener(e -> ejecutar(motor, "suma"));
        txtB.addActionListener(e -> ejecutar(motor, "suma"));

        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setOpaque(false);
        agregar(contenido, etiqueta("Número A"), 6);
        agregar(contenido, txtA, 8);
        agregar(contenido, etiqueta("Número B"), 6);
        agregar(contenido, txtB, 8);
        agregar(contenido, crearEjemplos(), 18);
        agregar(contenido, crearBotones(motor), 22);
        agregar(contenido, panelResultado, 10);
        agregar(contenido, crearPie(), 0);

        JPanel raiz = new JPanel(new BorderLayout(0, 20));
        raiz.setBackground(Theme.BG);
        raiz.setBorder(new EmptyBorder(24, 28, 24, 28));
        raiz.add(crearEncabezado(), BorderLayout.NORTH);
        raiz.add(contenido, BorderLayout.CENTER);

        setContentPane(raiz);
        setMinimumSize(new Dimension(660, 600));
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    private JPanel crearEncabezado() {
        JLabel titulo = new JLabel("Calculadora básica");
        titulo.setFont(Theme.sans(Font.BOLD, 24));
        titulo.setForeground(Theme.TEXT);

        JLabel subtitulo = new JLabel("Suma, resta, multiplicación y división resueltas con reglas en Prolog");
        subtitulo.setFont(Theme.sans(Font.PLAIN, 13));
        subtitulo.setForeground(Theme.MUTED);

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setOpaque(false);
        p.add(titulo);
        p.add(Box.createVerticalStrut(4));
        p.add(subtitulo);
        return p;
    }
    private JPanel crearEjemplos() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        p.setOpaque(false);

        JLabel lbl = new JLabel("Ejemplos:");
        lbl.setForeground(Theme.MUTED);
        lbl.setFont(Theme.sans(Font.PLAIN, 12));
        p.add(lbl);

        for (String[] ej : EJEMPLOS) {
            Buttons chip = new Buttons(ej[0] + " y " + ej[1], Theme.CARD, Theme.TEXT, 12, 6, 11);
            chip.addActionListener(e -> {
                txtA.setText(ej[0]);
                txtB.setText(ej[1]);
                txtA.requestFocusInWindow();
            });
            p.add(chip);
        }
        return p;
    }
    private JPanel crearBotones(Connection motor) {
        Buttons btnSumar = new Buttons("Sumar", Theme.BLUE, Theme.BG, 26, 11, 14);
        Buttons btnRestar = new Buttons("Restar", Theme.GREEN, Theme.BG, 26, 11, 14);
        Buttons btnMultiplicar = new Buttons("Multiplicar", Theme.AMBER, Theme.BG, 26, 11, 14);
        Buttons btnDividir = new Buttons("Dividir", Theme.RED, Theme.BG, 26, 11, 14);
        btnSumar.addActionListener(e -> ejecutar(motor, "suma"));
        btnRestar.addActionListener(e -> ejecutar(motor, "resta"));
        btnMultiplicar.addActionListener(e -> ejecutar(motor, "multiplicacion"));
        btnDividir.addActionListener(e -> ejecutar(motor, "division"));

        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        p.setOpaque(false);
        p.add(btnSumar);
        p.add(btnRestar);
        p.add(btnMultiplicar);
        p.add(btnDividir);
        return p;
    }

    private JPanel crearPie() {
        lblEstado.setFont(Theme.sans(Font.PLAIN, 12));
        lblEstado.setForeground(Theme.MUTED);

        Buttons btnCopiar = new Buttons("Copiar", Theme.CARD, Theme.TEXT, 16, 7, 12);
        btnCopiar.addActionListener(e -> copiarResultado());

        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        p.add(lblEstado, BorderLayout.WEST);
        p.add(btnCopiar, BorderLayout.EAST);
        return p;
    }
    private void ejecutar(Connection motor, String pred) {
        String a = txtA.getText().trim();
        String b = txtB.getText().trim();
        String nombre = switch (pred) {
            case "suma" -> "Suma";
            case "resta" -> "Resta";
            case "multiplicacion" -> "Multiplicación";
            default -> "División";
        };
        panelResultado.setTitulo(nombre + " de A y B");

        if (a.isEmpty() || b.isEmpty()) {
            mostrar("", "Escribí los dos números primero.", Theme.AMBER);
            return;
        }
        try {
            String resultado = motor.consultar(pred, a, b);
            if (resultado.startsWith("Sin solución")) {
                mostrar("", resultado, Theme.AMBER);
            } else {
                mostrar(resultado, "Listo", Theme.GREEN);
            }
        } catch (Exception ex) {
            mostrar("", "Expresión inválida: " + ex.getMessage(), Theme.RED);
        }
    }
    private void mostrar(String resultado, String estado, Color colorEstado) {
        panelResultado.setTexto(resultado);
        lblEstado.setText(estado);
        lblEstado.setForeground(colorEstado);
    }
    private void copiarResultado() {
        String t = panelResultado.getTexto();
        if (t.isEmpty()) {
            return;
        }
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(t), null);
        lblEstado.setText("Copiado al portapapeles");
        lblEstado.setForeground(Theme.BLUE);
    }
    private static void agregar(JPanel panel, JComponent c, int espacioDespues) {
        c.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(c);
        if (espacioDespues > 0) {
            panel.add(Box.createVerticalStrut(espacioDespues));
        }
    }
    private static JLabel etiqueta(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(Theme.sans(Font.BOLD, 12));
        l.setForeground(Theme.MUTED);
        return l;
    }
}