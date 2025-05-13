package com.icaropaixao.helpdesk.services;

import com.icaropaixao.helpdesk.domain.Pessoa;
import com.icaropaixao.helpdesk.domain.Cliente;
import com.icaropaixao.helpdesk.domain.dtos.ClienteDTO;
import com.icaropaixao.helpdesk.repositories.PessoaRepository;
import com.icaropaixao.helpdesk.repositories.ClienteRepository;
import com.icaropaixao.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.icaropaixao.helpdesk.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;


    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }


    public Cliente findById(Integer id) {

        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado. ID: " + id)) ; // se n encontrar retuorna null



    }

    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);

        validaPorCpfEEmail(objDTO);

        Cliente newObj = new Cliente(objDTO);
        return clienteRepository.save(newObj);

    }

    // atualizar um Cliente
    public Cliente update(Integer id, @Valid ClienteDTO clienteDTO) {
        clienteDTO.setId(id);
        Cliente objAntigo = findById(id);
        validaPorCpfEEmail(clienteDTO); // verificando se o usuario existe
        objAntigo = new Cliente(clienteDTO);
        return clienteRepository.save(objAntigo);

    }

    public void delete(Integer id) {

        Cliente obj = findById(id);

        // caso tenha um CHAMADO DE SERVIÇO atrelado ao cliente que esta tentando deletar, gera uma exception
        if(obj.getChamados().size() > 0){

            throw new DataIntegrityViolationException("O cliente possui ordens de serviços e não pode ser deletado!!!!");
        }
        clienteRepository.deleteById(id);
    }

    private void validaPorCpfEEmail(ClienteDTO objDTO) {

        // VERIFICA SE O CPF JÁ ESTA EM USO
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("CPF JÁ CADASTRADO NO SISTEMA");

        }

        // VERIFICA SE O EMAIL JA ESTÁ EM USO
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("EMAIL JÁ CADASTRADO NO SISTEMA");

        }

    }



}
