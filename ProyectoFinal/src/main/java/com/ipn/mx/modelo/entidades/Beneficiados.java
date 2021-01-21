/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name="Beneficiados",schema="public")
public class Beneficiados implements Serializable {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IDBeneficiado;
    private String NombreUsuario;
    private String Contra;
    private String Calle;
    private int Edad;
    private String Correo;
    private int IDMunicipio;
    //private byte[] Imagen;
    private String imagen;
    
}
