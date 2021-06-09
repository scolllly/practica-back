package com.practica.backend.repository;

import java.util.List;

import com.practica.backend.document.Foto;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepository extends MongoRepository<Foto, String>{

    public List<Foto> findByIdCliente(Integer idCliente);
    
}
