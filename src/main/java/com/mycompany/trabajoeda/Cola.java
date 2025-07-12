/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabajoeda;

/**
 *
 * @author matias
 */
public class Cola<T> {
    private Nodo<T> frente, fin;
    private final boolean usarPrioridad;

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
        }
    }

    public Cola() {
        this.usarPrioridad = false;
    }

    public Cola(boolean usarPrioridad) {
        this.usarPrioridad = usarPrioridad;
    }

    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);

        if (!usarPrioridad) {
            // FIFO normal
            if (fin == null) {
                frente = fin = nuevo;
            } else {
                fin.siguiente = nuevo;
                fin = nuevo;
            }
            return;
        }

        // Validación para casos con prioridad
        if (!(dato instanceof Comparable)) {
            throw new IllegalArgumentException("El tipo debe implementar Comparable para usar prioridad.");
        }

        Comparable<T> comparable = (Comparable<T>) dato;

        if (frente == null || comparable.compareTo(frente.dato) < 0) {
            nuevo.siguiente = frente;
            frente = nuevo;
            if (fin == null) fin = nuevo;
            return;
        }

        Nodo<T> actual = frente;
        while (actual.siguiente != null && comparable.compareTo(actual.siguiente.dato) >= 0) {
            actual = actual.siguiente;
        }

        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;

        if (nuevo.siguiente == null) {
            fin = nuevo;
        }
    }

    public T desencolar() {
        if (frente == null) return null;
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        return dato;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public T verFrente() {
        return (frente != null) ? frente.dato : null;
    }
}
