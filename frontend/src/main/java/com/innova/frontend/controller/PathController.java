package com.innova.frontend.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Base controller class for path error operations.
 *
 * @author Ahmet AKAN
 */

@Controller
public class PathController implements ErrorController {

    @RequestMapping(method = {RequestMethod.GET}, path = {"/**", "/"})
    public String forwardAngularPaths() {
        return "redirect:new-customer";
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/something-went-wrong";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/something-went-wrong";
            }
        }
        return "error/something-went-wrong";
    }

}
