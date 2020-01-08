package com.defmacros.goodrecipes.websvc.service;

import java.util.Arrays;
import java.util.List;

import com.defmacros.goodrecipes.websvc.dto.Recipe;
import com.defmacros.goodrecipes.websvc.dto.ServiceResponse;
import com.defmacros.goodrecipes.websvc.exception.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class QueryService {
    private Logger logger = LoggerFactory.getLogger(QueryService.class);

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    public List<Recipe> getAll() throws ServiceException {
        Recipe recipe = new Recipe();
        recipe.setId(123L);
        recipe.setTitle("Channa Masala");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json, application/xml");
        headers.add("Content-Type", "application/json");
        HttpEntity<List<Recipe>> request = new HttpEntity<List<Recipe>>(Arrays.asList(recipe), headers);
        ResponseEntity<ServiceResponse<List<Recipe>>> responseEntity =
            restTemplate.exchange("http://localhost:8081/api/v1/translate", HttpMethod.POST, request,
                                       new ParameterizedTypeReference<ServiceResponse<List<Recipe>>>() {});
        if (responseEntity != null && responseEntity.hasBody() &&
                responseEntity.getStatusCode() != null &&
                responseEntity.getStatusCode().is2xxSuccessful()) {
            ServiceResponse<List<Recipe>> svcResponse = (ServiceResponse<List<Recipe>>) responseEntity.getBody();
            if (svcResponse.isSuccess()) {
                return svcResponse.getData();
            } else {
                logger.error("Error returned in response of Backend API ${svcResponse.errors}");
                throw new ServiceException("Backend API returned error response");
            }
        } else {
            logger.error("Error returned in response of Backend API ${responseEntity?.body}");
            throw new ServiceException("Backend API returned error response");
        }
    }
}
