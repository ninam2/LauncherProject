package de.ninam.projects.launcher.execute.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class NumberPage {

    private List<CulPrits> culprits;
    private String result;


}