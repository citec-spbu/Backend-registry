package org.spburegistry.backend.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@PropertySource("classpath:application.properties")
@Component
public class PropertiesParser {

    @Value("${paths.permitted}")
    private String permittedPaths;

    public List<String> getPermittedPaths() {
        return Arrays.stream(permittedPaths.split(";")).toList();
    }
}
