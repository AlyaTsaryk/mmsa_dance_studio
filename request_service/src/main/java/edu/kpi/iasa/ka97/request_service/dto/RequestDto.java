package edu.kpi.iasa.ka97.request_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RequestDto {
    private Long id_group;
    private Long iduser;
    public RequestDto() {
    }
}