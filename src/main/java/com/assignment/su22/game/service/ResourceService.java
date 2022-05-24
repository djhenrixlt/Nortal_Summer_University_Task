package com.assignment.su22.game.service;


import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Component
public class ResourceService {
    private  int i = 0;

    public Map<Integer, List<String>> readAs2DMap(Resource resource){
        try (Reader reader = new InputStreamReader(resource.getInputStream())){

            Map<Integer, List<String>> listMap=
                    FileCopyUtils.copyToString(reader)
                            .lines()
                            .collect(Collectors.toMap(key-> i++, line-> Arrays.asList(line.split(""))));
        i=0;
        return listMap;
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }

    public Map<Integer, List<String>> readFileToMap(String path){
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(path);

        return readAs2DMap(resource);
    }
}
