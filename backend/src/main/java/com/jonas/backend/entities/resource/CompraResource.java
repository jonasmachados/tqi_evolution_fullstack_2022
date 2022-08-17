package com.jonas.backend.entities.resource;

import com.jonas.backend.entities.Compra;
import com.jonas.backend.entities.Itens;
import com.jonas.backend.entities.services.CompraService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/compra")
public class CompraResource {

    @Autowired
    private CompraService service;
      
    //Metodo que retorna os usuarios
    @GetMapping
    public ResponseEntity<List<Compra>> findAll() {
        List<Compra> list = service.findAll();
        return ResponseEntity.ok().body(list);//Contralador rest
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Compra> findById(@PathVariable Long id) { //ResponseEntity: resposta de aquisicao web
        Compra obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    
    @PostMapping
    public ResponseEntity<Compra> insert(@RequestBody Compra obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
    
    @PostMapping("/itens")
    public ResponseEntity<Itens> insertItens(@RequestBody Itens obj){
        obj = service.insertItens(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getCompra()).toUri();
        
        return ResponseEntity.created(uri).body(obj);
    }

  
}
