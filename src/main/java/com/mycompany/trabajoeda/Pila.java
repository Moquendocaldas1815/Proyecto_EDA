/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabajoeda;

/**
 *
 * @author matias
 */
public class Pila<T> {
    private Nodo<T> cima;

    private static class Nodo<T> {
        T dato;
        Nodo<T> anterior;

        Nodo(T dato) {
            this.dato = dato;
        }
    }

    public void apilar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.anterior = cima;
        cima = nuevo;
    }

    public T desapilar() {
        if (cima == null) return null;
        T dato = cima.dato;
        cima = cima.anterior;
        return dato;
    }

    public boolean estaVacia() {
        return cima == null;
    }
}