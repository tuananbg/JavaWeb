package com.laptopshop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject {

    private Object data;
    private Map<String, String> errorMessages = null;
    private String status;

}
