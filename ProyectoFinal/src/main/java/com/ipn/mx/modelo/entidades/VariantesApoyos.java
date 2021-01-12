/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Ricardo Alberto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="VariantesApoyos",schema="public")
public class VariantesApoyos implements Serializable {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IDVarianteApoyo;
    private int IDMunicipio;
    private int IDApoyo;
    private String Laboratorio;
    private String NombreComercial;
    
}
