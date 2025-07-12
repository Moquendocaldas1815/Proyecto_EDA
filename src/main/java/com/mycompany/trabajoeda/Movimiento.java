/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabajoeda;

/**
 *
 * @author matias
 */
public class Movimiento {
    String fechaHora;
    String dependencia;

    public Movimiento(String fechaHora, String dependencia) {
        this.fechaHora = fechaHora;
        this.dependencia = dependencia;
    }

    @Override
    public String toString() {
        return fechaHora + " -> " + dependencia;
    }
}