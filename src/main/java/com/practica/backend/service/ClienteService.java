package com.practica.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.practica.backend.document.Foto;
import com.practica.backend.entity.Cliente;
import com.practica.backend.model.ClienteModel;
import com.practica.backend.model.FotoModel;
import com.practica.backend.repository.ClienteRepository;
import com.practica.backend.repository.FotoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private FotoRepository fotoRepo;

    public List<ClienteModel> leerClientes(){
        List<ClienteModel> obj = new ArrayList<ClienteModel>();
        List<Cliente> clienteLista = new ArrayList<Cliente>();

        try {
            clienteLista = clienteRepo.findAll(); //Fetch all the Students from the database.
        }catch (Exception e){
            throw e;
        }

        if (clienteLista.size() > 0){ 
            clienteLista.stream().forEach(s -> { 
                ClienteModel clienteModel = new ClienteModel();

                // Datos Mysql
                clienteModel.setId(s.getId());
                clienteModel.setNombres(s.getNombres());
                clienteModel.setApellidos(s.getApellidos());
                clienteModel.setTipo(s.getTipo());
                clienteModel.setNumero(s.getNumero());
                clienteModel.setCiudad(s.getCiudad());
                clienteModel.setEdad(s.getEdad());

                List<Foto> fotoLista = new ArrayList<Foto>();
                List<FotoModel> fotoModelLista = new ArrayList<FotoModel>();
                try {
                    fotoLista = fotoRepo.findByIdCliente(s.getId()); //Fetch all the courses by email ID.
                }catch (Exception e){
                    throw e;
                }

                if (fotoLista.size() > 0){
                    fotoLista.stream().forEach(c -> {
                        FotoModel fotoModel = new FotoModel();
                        BeanUtils.copyProperties(c, fotoModel);
                        fotoModelLista.add(fotoModel);
                    });
                }
                
                clienteModel.setFotos(fotoModelLista);
                obj.add(clienteModel);
            });
        }

        return obj;
        
    }

    public ClienteModel leerClientePorId(Integer id){
        ClienteModel obj = new ClienteModel();
        Cliente cliente = new Cliente();

        try {
            cliente = clienteRepo.findById(id).orElse(cliente); //Fetch all the Students from the database.
        }catch (Exception e){
            throw e;
        }

        if (cliente.getId() != null){ 
            

            // Datos Mysql
            obj.setId(cliente.getId());
            obj.setNombres(cliente.getNombres());
            obj.setApellidos(cliente.getApellidos());
            obj.setTipo(cliente.getTipo());
            obj.setNumero(cliente.getNumero());
            obj.setCiudad(cliente.getCiudad());
            obj.setEdad(cliente.getEdad());

            List<Foto> fotoLista = new ArrayList<Foto>();
            List<FotoModel> fotoModelLista = new ArrayList<FotoModel>();
            try {
                fotoLista = fotoRepo.findByIdCliente(cliente.getId()); //Fetch all the courses by email ID.
            }catch (Exception e){
                throw e;
            }

            if (fotoLista.size() > 0){
                    fotoLista.stream().forEach(c -> {
                    FotoModel fotoModel = new FotoModel();
                    BeanUtils.copyProperties(c, fotoModel);
                    fotoModelLista.add(fotoModel);
                });
            }
                
            obj.setFotos(fotoModelLista);
        }

        return obj;
        
    }

    @Transactional
    public ClienteModel crearCliente(ClienteModel obj){

        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(obj, cliente);
        try {
                cliente = clienteRepo.save(cliente);
                obj.setId(cliente.getId());
                obj.getFotos().stream().forEach(c -> {
                    Foto foto = new Foto();
                    c.setIdCliente(obj.getId());
                    BeanUtils.copyProperties(c, foto);
                    try {
                        c.setId(fotoRepo.save(foto).getId());
                    }catch (Exception e){
                        throw e;
                    }

                });
        }catch (Exception e){
            throw e;
        }  
        

        return obj;
    }
    
}
