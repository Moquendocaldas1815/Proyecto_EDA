/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabajoeda;

/**
 *
 * @author matias
 */
public class DocumentoFinal {
    String nombre;
    String tipo;
    String contenido;

    public DocumentoFinal(String nombre, String tipo, String contenido) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return tipo + ": " + nombre;
    }
}