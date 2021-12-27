package edu.kpi.iasa.ka97.request_service.service;

import edu.kpi.iasa.ka97.request_service.model.Status;
import edu.kpi.iasa.ka97.request_service.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {
    private final StatusRepository statusRepository;
    public StatusService(StatusRepository statusRepository){
        this.statusRepository = statusRepository;
    }

    public List<Status> getStatuses() {
        return statusRepository.findAll();
    }


    public Status getStatusByName(String name) {
        return statusRepository.findStatusByName(name).orElseThrow(IllegalArgumentException::new);
    }

}

