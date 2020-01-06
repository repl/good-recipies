package com.defmacros.goodrecipes.websvc.controller;

import com.defmacros.goodrecipes.websvc.dto.ServiceResponse;
import com.defmacros.goodrecipes.websvc.service.QueryService;
import com.defmacros.goodrecipes.websvc.utils.ServiceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("api/v1/recipes")
public class RecipeController {

    @Autowired
    private QueryService queryService;

    @GetMapping("")
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    ServiceResponse getAll(HttpServletRequest request) {
        try {
            return ServiceResponse.ok(queryService.getAll());
        } /*catch (ServiceException e) {
            if (e.getErrorCode() != null) {
                return ServiceResponse.error(e.getErrorCode(), e.getMessage());
            }
            return ServiceResponse.error(ERROR_CODE_GENERIC_ERROR, e.getMessage());
        } */ catch (RuntimeException e) {
            String errorRefCode = ServiceUtils.generateErrorReferenceCode();
            log.error("Failed to submit query. Error reference code: {}", errorRefCode, e);
            return ServiceResponse.error(ServiceUtils.internalError(errorRefCode));
        }
    }
}
