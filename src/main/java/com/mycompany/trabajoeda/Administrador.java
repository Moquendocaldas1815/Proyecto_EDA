/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabajoeda;

/**
 *
 * @author matias
 */
public class Administrador {
    String usuario;
    String contraseña;

    public Administrador(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public boolean autenticar(String user, String pass) {
        return usuario.equals(user) && contraseña.equals(pass);
    }

    public void registrarExpediente(Expediente e, ListaDoble<Expediente> lista) {
        lista.agregar(e);
        System.out.println("Expediente registrado: " + e);
    }

    public void mostrarExpedientes(ListaDoble<Expediente> lista) {
        lista.mostrarAdelante();
    }
}
