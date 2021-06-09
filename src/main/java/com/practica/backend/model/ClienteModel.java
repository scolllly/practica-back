package com.practica.backend.model;

import java.util.List;

import lombok.Data;

@Data
public class ClienteModel {

    private Integer id;
    private String nombres;
    private String apellidos;
    private String tipo;
    private String numero;
    private short edad;
    private String ciudad;

    private List<FotoModel> fotos;
    
}
