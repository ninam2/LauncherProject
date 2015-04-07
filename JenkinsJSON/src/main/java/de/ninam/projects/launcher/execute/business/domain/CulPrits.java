package de.ninam.projects.launcher.execute.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by nmuelle2 on 12.03.2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CulPrits {

    private final String fullName;


}
