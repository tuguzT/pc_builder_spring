package io.github.tuguzt.pcbuilder.backend.spring.controller

import io.github.tuguzt.pcbuilder.backend.spring.model.ComponentEntity
import io.github.tuguzt.pcbuilder.backend.spring.service.ComponentService
import io.github.tuguzt.pcbuilder.domain.model.component.Component
import mu.KotlinLogging
import org.springframework.web.bind.annotation.*

private val logger = KotlinLogging.logger {}

/**
 * REST API controller of the server for [PC components][Component].
 */
@RestController
@RequestMapping("components")
class ComponentController(private val service: ComponentService) {
    /**
     * GET request which returns all PC components.
     */
    @GetMapping
    suspend fun index() = service.getAll()

    /**
     * GET request which returns component found by [id], if any.
     */
    @GetMapping("id/{id}")
    suspend fun findById(@PathVariable id: String): ComponentEntity? {
        logger.info { "Requested component with ID $id" }
        return service.findById(id).apply {
            this?.let {
                logger.info { "Found component with ID $id" }
                return@apply
            }
            logger.info { "Component with ID $id not found" }
        }
    }

    /**
     * POST request which inserts [entity] into the server repository.
     */
    @PostMapping("save")
    suspend fun save(@RequestBody entity: ComponentEntity) {
        service.save(entity)
        logger.info { "Inserted component with ID ${entity.id}" }
    }
}
