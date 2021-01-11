/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.VariantesApoyos;

/**
 *
 * @author Ricardo Alberto
 */
public class VariantesApoyosDTO {
    private VariantesApoyos entidad;
    
    public VariantesApoyosDTO() {
        entidad = new VariantesApoyos();
    }

    public VariantesApoyos getEntidad() {
        return entidad;
    }

    public void setEntidad(VariantesApoyos entidad) {
        this.entidad = entidad;
    }
}
