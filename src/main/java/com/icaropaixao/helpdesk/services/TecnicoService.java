package com.icaropaixao.helpdesk.services;

import com.icaropaixao.helpdesk.domain.Pessoa;
import com.icaropaixao.helpdesk.domain.Tecnico;
import com.icaropaixao.helpdesk.domain.dtos.TecnicoDTO;
import com.icaropaixao.helpdesk.repositories.PessoaRepository;
import com.icaropaixao.helpdesk.repositories.TecnicoRepository;
import com.icaropaixao.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.icaropaixao.helpdesk.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {


    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;


    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }


    public Tecnico findById(Integer id) {

        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado. ID: " + id)); // se n encontrar retuorna null


    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));

        validaPorCpfEEmail(objDTO);

        Tecnico newObj = new Tecnico(objDTO);
        return tecnicoRepository.save(newObj);

    }

    // atualizar um Tecnico
    public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
        objDTO.setId(id);
        Tecnico oldObj = findById(id);

        if (!objDTO.getSenha().equals(oldObj.getSenha()))
            objDTO.setSenha(encoder.encode(objDTO.getSenha()));

        validaPorCpfEEmail(objDTO);
        oldObj = new Tecnico(objDTO);
        return tecnicoRepository.save(oldObj);
    }


    public void delete(Integer id) {
        Tecnico obj = findById(id);

        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
        }
        tecnicoRepository.deleteById(id);
    }

    private void validaPorCpfEEmail(TecnicoDTO objDTO) {

        // VERIFICA SE O CPF JÁ ESTA EM USO
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF JÁ CADASTRADO NO SISTEMA");

        }

        // VERIFICA SE O EMAIL JA ESTÁ EM USO
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("EMAIL JÁ CADASTRADO NO SISTEMA");

        }

    }


}
