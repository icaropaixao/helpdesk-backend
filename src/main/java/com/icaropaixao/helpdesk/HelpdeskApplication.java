package com.icaropaixao.helpdesk;

import com.icaropaixao.helpdesk.domain.Chamado;
import com.icaropaixao.helpdesk.domain.Cliente;
import com.icaropaixao.helpdesk.domain.Tecnico;
import com.icaropaixao.helpdesk.domain.enums.Perfil;
import com.icaropaixao.helpdesk.domain.enums.Prioridade;
import com.icaropaixao.helpdesk.domain.enums.Status;
import com.icaropaixao.helpdesk.repositories.ChamadoRepository;
import com.icaropaixao.helpdesk.repositories.ClienteRepository;
import com.icaropaixao.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelpdeskApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelpdeskApplication.class, args);
    }

}
