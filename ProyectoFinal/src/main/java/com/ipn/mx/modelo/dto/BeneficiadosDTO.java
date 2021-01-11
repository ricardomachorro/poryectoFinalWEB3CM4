/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Beneficiados;

/**
 *
 * @author Ricardo Alberto
 */
public class BeneficiadosDTO {
    
        private Beneficiados entidad;
    
    public BeneficiadosDTO() {
        entidad = new Beneficiados();
    }

    public Beneficiados getEntidad() {
        return entidad;
    }

    public void setEntidad(Beneficiados entidad) {
        this.entidad = entidad;
    }
    
}
