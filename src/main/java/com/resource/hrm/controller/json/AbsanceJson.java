package com.resource.hrm.controller.json;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder @Data @AllArgsConstructor @NoArgsConstructor
public class AbsanceJson {
    private Long id;
    private String start;
    private String end;
    private String title;
    private String description;
}
