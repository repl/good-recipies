package com.defmacros.goodrecipes.websvc.service

import com.defmacros.goodrecipes.websvc.dto.Recipe
import com.defmacros.goodrecipes.websvc.dto.ServiceResponse
import com.defmacros.goodrecipes.websvc.exception.ServiceException
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import java.util.*


@Slf4j
@Component
public class QueryService {
    private val logger = LoggerFactory.getLogger(QueryService::class.java)

    @Autowired
    @Qualifier("restTemplate")
    private val restTemplate: RestTemplate? = null

    fun getAll(): List<Recipe> {
        val recipe = Recipe()
        recipe.id = "123"
        recipe.name = "Channa Masala"
        val headers = LinkedMultiValueMap<String, String>()
        headers.add("Accept", "application/json, application/xml")
        headers.add("Content-Type", "application/json")
        val request = HttpEntity<List<Recipe>>(Arrays.asList(recipe), headers)
        val responseEntity = restTemplate?.postForEntity(
                "http://localhost:8081/api/v1/translate", request, ServiceResponse::class.java)
        if (responseEntity != null && responseEntity.hasBody() &&
                responseEntity.getStatusCode() != null &&
                responseEntity.getStatusCode().is2xxSuccessful()) {
            val svcResponse = responseEntity.body as ServiceResponse<List<Recipe>>
            if (svcResponse.isSuccess) {
                return svcResponse.data
            } else {
                logger.error("Error returned in response of Backend API ${svcResponse.errors}");
                throw ServiceException("Backend API returned error response");
            }
        } else {
            logger.error("Error returned in response of Backend API ${responseEntity?.body}");
            throw ServiceException("Backend API returned error response");
        }
    }
}