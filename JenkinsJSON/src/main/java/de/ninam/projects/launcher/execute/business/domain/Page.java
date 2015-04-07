package de.ninam.projects.launcher.execute.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Page extends NumberPage {

    private String name;

    private String color;

    private String fullDisplayName;

    private List<Build> builds;

    private Build lastFailedBuild;

    private Build lastSuccessfulBuild;

}