/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabajoeda;

/**
 *
 * @author matias
 */
import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private JTextField usuarioField;
    private JPasswordField contraseñaField;
    private JButton loginButton;
    private JTextArea salidaArea;
    private Administrador admin;
    private ListaDoble<Expediente> listaExpedientes;
    private ArbolExpedientes arbolExpedientes;
    private Cola<Expediente> colaAtencion;

    public GUI() {
        super("Gestión de Trámite Documentario");
        admin = new Administrador("admin", "1234");
        listaExpedientes = new ListaDoble<>();
        arbolExpedientes = new ArbolExpedientes();
        colaAtencion = new Cola<Expediente>(true); // Activar cola con prioridad

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel panelLogin = new JPanel();
        panelLogin.setLayout(new GridLayout(3, 2));

        panelLogin.add(new JLabel("Usuario:"));
        usuarioField = new JTextField();
        panelLogin.add(usuarioField);

        panelLogin.add(new JLabel("Contraseña:"));
        contraseñaField = new JPasswordField();
        panelLogin.add(contraseñaField);

        loginButton = new JButton("Iniciar sesión");
        panelLogin.add(loginButton);

        add(panelLogin, BorderLayout.NORTH);

        salidaArea = new JTextArea();
        salidaArea.setEditable(false);
        add(new JScrollPane(salidaArea), BorderLayout.CENTER);

        loginButton.addActionListener(e -> {
            String user = usuarioField.getText();
            String pass = new String(contraseñaField.getPassword());
            if (admin.autenticar(user, pass)) {
                salidaArea.setText("Bienvenido administrador\n");
                mostrarMenu();
            } else {
                salidaArea.setText("Usuario o contraseña incorrectos\n");
            }
        });

        setVisible(true);
    }

    private void mostrarMenu() {
        JPanel panelFormulario = new JPanel(new GridLayout(11, 2));

        JTextField idField = new JTextField();
        JTextField prioridadField = new JTextField();
        JTextField asuntoField = new JTextField();
        JTextField refField = new JTextField();
        JTextField dniField = new JTextField();
        JTextField nombreField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField telefonoField = new JTextField();
        JTextField buscarIDField = new JTextField();

        panelFormulario.add(new JLabel("ID del Expediente:"));
        panelFormulario.add(idField);
        panelFormulario.add(new JLabel("Prioridad:"));
        panelFormulario.add(prioridadField);
        panelFormulario.add(new JLabel("Asunto:"));
        panelFormulario.add(asuntoField);
        panelFormulario.add(new JLabel("Documento Referencia:"));
        panelFormulario.add(refField);
        panelFormulario.add(new JLabel("DNI Interesado:"));
        panelFormulario.add(dniField);
        panelFormulario.add(new JLabel("Nombre Interesado:"));
        panelFormulario.add(nombreField);
        panelFormulario.add(new JLabel("Teléfono Interesado:"));
        panelFormulario.add(telefonoField);
        panelFormulario.add(new JLabel("Email Interesado:"));
        panelFormulario.add(emailField);

        JButton registrarBtn = new JButton("Registrar Expediente");
        JButton mostrarBtn = new JButton("Mostrar Expedientes");
        JButton finalizarBtn = new JButton("Finalizar Expediente por ID");
        JButton buscarBtn = new JButton("Buscar por ID");
        JButton atenderBtn = new JButton("Atender siguiente expediente");

        panelFormulario.add(registrarBtn);
        panelFormulario.add(mostrarBtn);
        panelFormulario.add(new JLabel("Buscar/Finalizar por ID:"));
        panelFormulario.add(buscarIDField);
        panelFormulario.add(buscarBtn);
        panelFormulario.add(finalizarBtn);
        panelFormulario.add(atenderBtn);

        add(panelFormulario, BorderLayout.SOUTH);
        revalidate();

        registrarBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                int prioridad = Integer.parseInt(prioridadField.getText());
                String asunto = asuntoField.getText();
                String ref = refField.getText();
                String dni = dniField.getText();
                String nombre = nombreField.getText();
                String tel = telefonoField.getText();
                String email = emailField.getText();

                Interesado interesado = new Interesado(dni, nombre, tel, email);
                Expediente exp = new Expediente(id, prioridad, asunto, ref, interesado);
                exp.agregarMovimiento(new Movimiento("2025-07-13 09:00", "Recepción"));

                admin.registrarExpediente(exp, listaExpedientes);
                arbolExpedientes.insertar(exp);
                colaAtencion.encolar(exp);
                salidaArea.append("Expediente registrado exitosamente\n");
            } catch (Exception ex) {
                salidaArea.append("Error en el ingreso de datos: " + ex.getMessage() + "\n");
            }
        });

        mostrarBtn.addActionListener(e -> {
            salidaArea.append("Lista de expedientes:\n");
            salidaArea.append(listaExpedientes.obtenerContenidoAdelante());
        });

        buscarBtn.addActionListener(e -> {
            String textoID = buscarIDField.getText().trim();
            if (textoID.isEmpty()) {
                salidaArea.append("Por favor ingrese un ID para buscar.\n");
                return;
            }
            try {
                int idBuscado = Integer.parseInt(textoID);
                Expediente resultado = arbolExpedientes.buscar(idBuscado);
                if (resultado != null) {
                    salidaArea.append("\n[Resultado búsqueda] " + resultado + "\n");
                } else {
                    salidaArea.append("\nExpediente no encontrado.\n");
                }
            } catch (NumberFormatException ex) {
                salidaArea.append("ID inválido para búsqueda.\n");
            }
        });

        finalizarBtn.addActionListener(e -> {
            String textoID = buscarIDField.getText().trim();
            if (textoID.isEmpty()) {
                salidaArea.append("Por favor ingrese un ID para finalizar.\n");
                return;
            }
            try {
                int idFinalizar = Integer.parseInt(textoID);
                Expediente expediente = arbolExpedientes.buscar(idFinalizar);
                if (expediente != null) {
                    expediente.estado = "Finalizado";
                    salidaArea.append("\nExpediente finalizado exitosamente.\n");
                } else {
                    salidaArea.append("\nExpediente no encontrado para finalizar.\n");
                }
            } catch (NumberFormatException ex) {
                salidaArea.append("ID inválido para finalización.\n");
            }
        });

        atenderBtn.addActionListener(e -> {
            if (colaAtencion.estaVacia()) {
                salidaArea.append("No hay expedientes en espera.\n");
            } else {
                Expediente siguiente = colaAtencion.desencolar();
                salidaArea.append("Atendiendo expediente:\n" + siguiente + "\n");
            }
        });
    }

    public static void main(String[] args) {
        new GUI();
    }
}
