package com.icaropaixao.helpdesk.resources;

import com.icaropaixao.helpdesk.domain.Tecnico;
import com.icaropaixao.helpdesk.domain.dtos.TecnicoDTO;
import com.icaropaixao.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResources {


    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        List<Tecnico> list = tecnicoService.findAll();
        List<TecnicoDTO> listDTO = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    // GET POR ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        Tecnico obj = tecnicoService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }


    // anotação request pois vai "Precisar" receber um tecnicoDTO no corpo da função
    // anotação @Valid VALIDA se os campos obrigatorios foram preenchidos
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecnicoDTO) {
        Tecnico newObj = tecnicoService.create(tecnicoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }


    // UPDATE
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id,@Valid @RequestBody TecnicoDTO tecnicoDTO) {

        Tecnico obj = tecnicoService.update(id,tecnicoDTO);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));

    }

    // DELETE
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();

    }


}
