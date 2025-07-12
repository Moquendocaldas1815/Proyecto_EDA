/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabajoeda;

/**
 *
 * @author matias
 */
import java.time.LocalDateTime;

public class Expediente implements Comparable<Expediente> {
    public int id;
    public int prioridad;
    public String asunto;
    public String referencia;
    public Interesado interesado;
    public String estado;
    public ListaSimple<Movimiento> movimientos;
    public Pila<DocumentoFinal> documentos;
    public LocalDateTime fechaInicio;
    public LocalDateTime fechaFinalizacion;

    public Expediente(int id, int prioridad, String asunto, String referencia, Interesado interesado) {
        this.id = id;
        this.prioridad = prioridad;
        this.asunto = asunto;
        this.referencia = referencia;
        this.interesado = interesado;
        this.estado = "En proceso";
        this.movimientos = new ListaSimple<>();
        this.documentos = new Pila<>();
        this.fechaInicio = LocalDateTime.now();
        this.fechaFinalizacion = null;
    }

    public void agregarMovimiento(Movimiento movimiento) {
        movimientos.agregar(movimiento);
    }

    public void agregarDocumento(DocumentoFinal doc) {
        documentos.apilar(doc);
    }

    public void finalizarTramite() {
        this.estado = "Finalizado";
        this.fechaFinalizacion = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Expediente ID: " + id +
               ", Prioridad: " + prioridad +
               ", Asunto: " + asunto +
               ", Estado: " + estado +
               ", Fecha Inicio: " + fechaInicio +
               ", Fecha Finalización: " + (fechaFinalizacion != null ? fechaFinalizacion : "En proceso") +
               ", Interesado: " + interesado.getNombre();
    }

    @Override
    public int compareTo(Expediente otro) {
        int cmp = Integer.compare(this.prioridad, otro.prioridad);
        if (cmp == 0) {
            return this.fechaInicio.compareTo(otro.fechaInicio);
        }
        return cmp;
    }
}
