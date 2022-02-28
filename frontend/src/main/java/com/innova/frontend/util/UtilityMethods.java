package com.innova.frontend.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindingResult;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class UtilityMethods {

    public static String logErrors(BindingResult bindingResult, String form){
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach((e)-> errors.put(e.getField(),e.getDefaultMessage()));
            errors.forEach((e, c) -> log.warn(e+" "+c));
            return form;
        }
        return null;
    }
}
