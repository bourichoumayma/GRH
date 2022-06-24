package com.resource.hrm.controller.json;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Builder
@AllArgsConstructor @NoArgsConstructor @Data
public class AbsanceJsonRespance {
    private String uid;
    private String emploeeID;
    private String absanceID;
    private String discreption;
    private String start;
    private String end;
}
