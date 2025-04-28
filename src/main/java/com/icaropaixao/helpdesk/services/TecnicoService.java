package com.icaropaixao.helpdesk.services;

import com.icaropaixao.helpdesk.domain.Tecnico;
import com.icaropaixao.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id) {

        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElse(null); // se n encontrar retuorna null



    }

}
