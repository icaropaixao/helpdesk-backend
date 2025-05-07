package com.icaropaixao.helpdesk.services;

import com.icaropaixao.helpdesk.domain.Tecnico;
import com.icaropaixao.helpdesk.repositories.TecnicoRepository;
import com.icaropaixao.helpdesk.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;


    public List<Tecnico> findAll(){
        return tecnicoRepository.findAll();
    }


    public Tecnico findById(Integer id) {

        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado. ID: " + id)) ; // se n encontrar retuorna null



    }



}
