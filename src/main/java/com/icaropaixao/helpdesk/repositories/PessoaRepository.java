package com.icaropaixao.helpdesk.repositories;

import com.icaropaixao.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {



}
