package com.defmacros.goodrecipes.backendsvc.service

import com.defmacros.goodrecipes.backendsvc.dto.RecipeDto
import com.defmacros.goodrecipes.backendsvc.dto.ServiceResponse
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import kotlin.streams.toList

@Slf4j
@Component
public class TranslatorService {
    private val logger = LoggerFactory.getLogger(TranslatorService::class.java)

    fun translate(recipeList: List<RecipeDto>): List<RecipeDto> {
        logger.info("Invoking translate method")
        return recipeList.stream().toList()
    }
}