package com.wanderhungerbuhler.microwh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private Long id;
    private String name;
    private String description;

}
