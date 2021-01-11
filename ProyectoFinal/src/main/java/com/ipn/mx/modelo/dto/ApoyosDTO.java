/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Apoyos;

/**
 *
 * @author Ricardo Alberto
 */
public class ApoyosDTO {
    
        private Apoyos entidad;
    
    public ApoyosDTO() {
        entidad = new Apoyos();
    }

    public Apoyos getEntidad() {
        return entidad;
    }

    public void setEntidad(Apoyos entidad) {
        this.entidad = entidad;
    }
    
}
