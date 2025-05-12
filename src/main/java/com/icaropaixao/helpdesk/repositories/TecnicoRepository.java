package com.icaropaixao.helpdesk.repositories;


import com.icaropaixao.helpdesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {


    List<Tecnico> id(Integer id);
}
