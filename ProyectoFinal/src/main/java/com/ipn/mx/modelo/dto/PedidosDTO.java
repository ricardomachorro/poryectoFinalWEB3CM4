/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Pedidos;

/**
 *
 * @author Ricardo Alberto
 */
public class PedidosDTO {
    
    private Pedidos entidad;
    
    public PedidosDTO() {
        entidad = new Pedidos();
    }

    public Pedidos getEntidad() {
        return entidad;
    }

    public void setEntidad(Pedidos entidad) {
        this.entidad = entidad;
    } 
    
}
