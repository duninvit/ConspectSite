package com.example.ConspectSite.services.dto;

import com.example.ConspectSite.model.Conspect;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ConspectResponseDTO {

    private int totalRecords;
    private List<ConspectDTO> conspects;

    public ConspectResponseDTO(List<Conspect> conspects){
        this.conspects = StreamSupport.stream(conspects.spliterator(), false)
                .map(ConspectDTO::new)
                .collect(Collectors.toList());
        this.totalRecords = conspects.size();
    }

    public ConspectResponseDTO(int totalRecords, List<ConspectDTO> conspects) {
        this.totalRecords = totalRecords;
        this.conspects = conspects;
    }

    public ConspectResponseDTO() {
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<ConspectDTO> getConspects() {
        return conspects;
    }

    public void setConspects(List<ConspectDTO> conspects) {
        this.conspects = conspects;
    }
}
