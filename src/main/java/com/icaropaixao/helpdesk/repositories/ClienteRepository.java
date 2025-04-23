package com.icaropaixao.helpdesk.repositories;

import com.icaropaixao.helpdesk.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {



}
