package edu.kpi.iasa.ka97.request_service.service;

import edu.kpi.iasa.ka97.request_service.dto.RequestDto;
import edu.kpi.iasa.ka97.request_service.model.Request;
import edu.kpi.iasa.ka97.request_service.repository.RequestRepository;
import edu.kpi.iasa.ka97.request_service.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    private static final String DEFAULT_STATUS = "Registered";
    private final RequestRepository requestRepository;
    private final StatusService statusService;

    public RequestService(RequestRepository requestRepository, StatusService statusService) {
        this.requestRepository = requestRepository;
        this.statusService = statusService;
    }
    public List<Request> getRequests(Long id) {return requestRepository.findAllByIduser(id).orElseThrow(IllegalArgumentException::new);}
    public Request saveRequest(Request newRequest) {
        return requestRepository.save(newRequest);
    }

    public List<Request> getAllRequests() {return requestRepository.findAll();}

    public Request getRequestById(Long id) {
        Optional<Request> request = requestRepository.findById(id);
        if (request.isPresent()) {
            return request.get();
        }
        throw new IllegalArgumentException("Request is not found");
    }

    public Request updateRequestById(Long id, Request updatedRequest) {
        Optional<Request> request = requestRepository.findById(id);
        if (request.isPresent()) {
            Request oldRequest = request.get();
            updateRequest(oldRequest, updatedRequest);
            return requestRepository.save(oldRequest);
        }
        throw new IllegalArgumentException("Request is not found");
    }

    private void updateRequest(Request oldRequest, Request updatedRequest) {
        oldRequest.setId_group(updatedRequest.getId_group());
        oldRequest.setStatus(updatedRequest.getStatus());
        oldRequest.setIduser(updatedRequest.getIduser());
    }

    public void deleteRequestById(Long id) {

        requestRepository.deleteById(id);
    }

    public Request createRequest(RequestDto requestDt) {
        Request request = Request.builder()
                .id_group(requestDt.getId_group())
                .iduser(requestDt.getIduser())
                .build();
        request.setStatus(statusService.getStatusByName(DEFAULT_STATUS));
        return request;
    }
}
