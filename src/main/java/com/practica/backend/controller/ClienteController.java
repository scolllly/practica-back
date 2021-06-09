package com.practica.backend.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import com.practica.backend.model.ClienteModel;
import com.practica.backend.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping()
    public List<ClienteModel> leerClientes(){
        return clienteService.leerClientes();
    }

    @GetMapping("/{id}")
    public ClienteModel leerClientePorId(@PathVariable(name = "id") Integer id){
        return clienteService.leerClientePorId(id);
    }

    @PostMapping()
    public ClienteModel crearCliente(@RequestBody ClienteModel c){
        return clienteService.crearCliente(c);
    }
    
}
