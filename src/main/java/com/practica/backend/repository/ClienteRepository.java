package com.practica.backend.repository;
import java.util.List;
import com.practica.backend.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

    // public List<Cliente> findByEdadGreaterThanEqual(short edad);
    // public Cliente findByTipoIdentificacionAndNmroIdentificacion(String tipo, String numero);
    
}
