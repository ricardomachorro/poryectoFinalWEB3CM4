/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
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
@Table(name="Pedidos",schema="public")
public class Pedidos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IDPedido;
    private int IDBeneficiado;
    private String NombreComercial;
    private String Laboratorio;
    private int Cantidad;
    private String MesEntrega;
  /*  private Date FechaPedido;
    private Date FechaEntrega;*/
}
