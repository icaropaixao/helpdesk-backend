package com.icaropaixao.helpdesk.services;

import com.icaropaixao.helpdesk.domain.Chamado;
import com.icaropaixao.helpdesk.domain.Cliente;
import com.icaropaixao.helpdesk.repositories.ChamadoRepository;
import com.icaropaixao.helpdesk.services.exceptions.ObjectnotFoundException;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado. ID: " + id)) ; // se n encontrar retuorna null


    }

    // FindALL
    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }



}
