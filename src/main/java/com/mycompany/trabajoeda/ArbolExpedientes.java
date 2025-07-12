/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabajoeda;

/**
 *
 * @author matias
 */
public class ArbolExpedientes {

    private class Nodo {
        Expediente expediente;
        Nodo izquierdo, derecho;

        Nodo(Expediente expediente) {
            this.expediente = expediente;
        }
    }

    private Nodo raiz;

    public void insertar(Expediente expediente) {
        raiz = insertarRec(raiz, expediente);
    }

    private Nodo insertarRec(Nodo actual, Expediente expediente) {
        if (actual == null) {
            return new Nodo(expediente);
        }

        if (expediente.id < actual.expediente.id) {
            actual.izquierdo = insertarRec(actual.izquierdo, expediente);
        } else if (expediente.id > actual.expediente.id) {
            actual.derecho = insertarRec(actual.derecho, expediente);
        } else {
            // ID duplicado: puede ignorarse o sobreescribirse
        }

        return actual;
    }

    public Expediente buscar(int id) {
        return buscarRec(raiz, id);
    }

    private Expediente buscarRec(Nodo actual, int id) {
        if (actual == null) return null;

        if (id == actual.expediente.id) {
            return actual.expediente;
        }

        if (id < actual.expediente.id) {
            return buscarRec(actual.izquierdo, id);
        } else {
            return buscarRec(actual.derecho, id);
        }
    }
}
