/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabajoeda;

/**
 *
 * @author matias
 */
public class ListaDoble<T> {
    private Nodo<T> cabeza, cola;

    private static class Nodo<T> {
        T dato;
        Nodo<T> anterior, siguiente;

        Nodo(T dato) {
            this.dato = dato;
        }
    }

    public void agregar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
    }

    public void mostrarAdelante() {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }

    public String obtenerContenidoAdelante() {
        StringBuilder sb = new StringBuilder();
        Nodo<T> actual = cabeza;
        while (actual != null) {
            sb.append(actual.dato.toString()).append("\n");
            actual = actual.siguiente;
        }
        return sb.toString();
    }
}
