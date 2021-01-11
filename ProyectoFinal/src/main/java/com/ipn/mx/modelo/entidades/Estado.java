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
@Table(name="Estado",schema="public")
public class Estado implements Serializable{
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IDEstado;
    private String Nombre;
    private String Clave;
    private String Abrev;
    private String NombreUsuarioEncargado;
    private String Contra;
    
}
