package com.systems.demo.apnewsdemo.problem.solving;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LocationsAndTags {
    private List<City> cites;
    public LocationsAndTags(List<City> cities) {
        this.cites = cities;
    }

    public void printCitiesAndTags() {
        cites.forEach(city -> {
          String value = city.getName();
            value += city.getTags().stream().map(Tag::getTagName).distinct().collect(Collectors.joining(", "));
            log.info("city with tags: {}",value);
        });
    }



}

@Getter
@Setter
class City {
    private String name;
    private List<Tag> tags;
}

@Getter
@Setter
class Tag {
    private String tagName;
}
