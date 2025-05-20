package com.icaropaixao.helpdesk.resources;


import com.icaropaixao.helpdesk.domain.Chamado;
import com.icaropaixao.helpdesk.domain.dtos.ChamadoDTO;
import com.icaropaixao.helpdesk.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService chamadoService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {

        Chamado obj = chamadoService.findById(id);
        return ResponseEntity.ok(new ChamadoDTO(obj));

    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll() {
        List<Chamado> list = chamadoService.findAll();

        // transformando em list em DTO
        List<ChamadoDTO> listDTO = list.stream().map(obj -> new ChamadoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok(listDTO);

    }

@PostMapping
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO objDTO) {
    Chamado obj = chamadoService.create(objDTO);

    URI uri = ServletUriComponentsBuilder.
            fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

    return ResponseEntity.created(uri).body(new ChamadoDTO(obj));


}



}
