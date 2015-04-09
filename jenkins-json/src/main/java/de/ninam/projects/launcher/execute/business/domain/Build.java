package de.ninam.projects.launcher.execute.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by nmuelle2 on 12.03.2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Build implements Comparable<Build> {

    private final Integer number;

    private final String url;


    @Override
    public int compareTo(Build o) {
        return (-1) * number.compareTo(o.getNumber());
    }
}
