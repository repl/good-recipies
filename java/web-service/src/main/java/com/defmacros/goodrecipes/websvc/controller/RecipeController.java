package com.defmacros.goodrecipes.websvc.controller;

import javax.servlet.http.HttpServletRequest;

import com.defmacros.goodrecipes.websvc.dto.ServiceResponse;
import com.defmacros.goodrecipes.websvc.exception.ServiceException;
import com.defmacros.goodrecipes.websvc.payload.RecipeCreateRequest;
import com.defmacros.goodrecipes.websvc.service.QueryService;
import com.defmacros.goodrecipes.websvc.service.RecipeService;
import com.defmacros.goodrecipes.websvc.utils.ServiceUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import static com.defmacros.goodrecipes.websvc.utils.ErrorConstants.ERROR_CODE_GENERIC_ERROR;

@Slf4j
@RestController
@RequestMapping("api/v1/recipes")
public class RecipeController {

    @Autowired
    private QueryService queryService;
    @Autowired
    private RecipeService recipeService;

    @GetMapping("")
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    ServiceResponse getAll(HttpServletRequest request) {
        try {
            return ServiceResponse.ok(queryService.getAll());
        } catch (ServiceException e) {
            if (e.getErrorCode() != null) {
                return ServiceResponse.error(e.getErrorCode(), e.getMessage());
            }
            return ServiceResponse.error(ERROR_CODE_GENERIC_ERROR, e.getMessage());
        } catch (RuntimeException e) {
            String errorRefCode = ServiceUtils.generateErrorReferenceCode();
            log.error("Failed to submit query. Error reference code: {}", errorRefCode, e);
            return ServiceResponse.error(ServiceUtils.internalError(errorRefCode));
        }
    }

    @PostMapping("")
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    ServiceResponse createRecipe(HttpServletRequest request, @RequestBody RecipeCreateRequest input) {
        try {
            return ServiceResponse.ok(recipeService.createRecipe(input));
        } catch (ServiceException e) {
            if (e.getErrorCode() != null) {
            return ServiceResponse.error(e.getErrorCode(), e.getMessage());
            }
            return ServiceResponse.error(ERROR_CODE_GENERIC_ERROR, e.getMessage());
        } catch (RuntimeException e) {
            String errorRefCode = ServiceUtils.generateErrorReferenceCode();
            log.error("Failed to create recipe. Error reference code: {}", errorRefCode, e);
            return ServiceResponse.error(ServiceUtils.internalError(errorRefCode));
        }
    }

}
