package com.systems.demo.apnewsdemo.datastructures.nodebased;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CacheVal<T> {
       private final T data;
       private LocalDateTime dateTime = LocalDateTime.now();

    public CacheVal(T data) {
        this.data = data;
    }
}
