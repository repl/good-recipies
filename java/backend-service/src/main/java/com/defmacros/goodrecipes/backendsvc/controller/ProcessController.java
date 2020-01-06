package com.defmacros.goodrecipes.backendsvc.controller;

import com.defmacros.goodrecipes.backendsvc.dto.RecipeDto;
import com.defmacros.goodrecipes.backendsvc.dto.ServiceResponse;
import com.defmacros.goodrecipes.backendsvc.service.TranslatorService;
import com.defmacros.goodrecipes.backendsvc.utils.ServiceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/translate")
public class ProcessController {

    @Autowired
    private TranslatorService service;

    @RequestMapping(method = RequestMethod.POST, value = "")
    @ResponseBody
    ServiceResponse translate(HttpServletRequest request,
                              @Valid @RequestBody List<RecipeDto> input) {
        try {
            return ServiceResponse.ok(service.translate(input));
        } /*catch (ServiceException e) {
            if (e.getErrorCode() != null) {
                return ServiceResponse.error(e.getErrorCode(), e.getMessage());
            }
            return ServiceResponse.error(ERROR_CODE_GENERIC_ERROR, e.getMessage());
        } */ catch (RuntimeException e) {
            String errorRefCode = ServiceUtils.generateErrorReferenceCode();
            log.error("Failed to translate. Error reference code: {}", errorRefCode, e);
            return ServiceResponse.error(ServiceUtils.internalError(errorRefCode));
        }
    }
}
