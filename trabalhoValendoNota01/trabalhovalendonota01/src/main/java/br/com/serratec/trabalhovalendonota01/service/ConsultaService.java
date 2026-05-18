package br.com.serratec.trabalhovalendonota01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import br.com.serratec.trabalhovalendonota01.model.Consulta;
import br.com.serratec.trabalhovalendonota01.model.Medico;
import br.com.serratec.trabalhovalendonota01.model.Paciente;
import br.com.serratec.trabalhovalendonota01.repository.ConsultaRepository;
import br.com.serratec.trabalhovalendonota01.repository.MedicoRepository;
import br.com.serratec.trabalhovalendonota01.repository.PacienteRepository;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public Consulta salvar(Consulta consulta) {
        
        System.out.println("MEDICO: " + consulta.getMedico());

        if (consulta.getMedico() == null || consulta.getMedico().getId() == null) {
            throw new RuntimeException("Médico é obrigatório");
        }

        if (consulta.getPaciente() == null || consulta.getPaciente().getId() == null) {
            throw new RuntimeException("Paciente é obrigatório");
        }

        Medico medico = medicoRepository.findById(consulta.getMedico().getId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        Paciente paciente = pacienteRepository.findById(consulta.getPaciente().getId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        consulta.setMedico(medico);
        consulta.setPaciente(paciente);

        return consultaRepository.save(consulta);
    }

    // ✅ ADICIONE ISSO
    public Optional<Consulta> buscar(Long id) {
        return consultaRepository.findById(id);
    }

    public boolean existe(Long id) {
        return consultaRepository.existsById(id);
    }

    public void deletar(Long id) {
        consultaRepository.deleteById(id);
    }
}