package com.practica.backend.document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "foto")
@Data
public class Foto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private Integer idCliente;
    private String foto;
}
