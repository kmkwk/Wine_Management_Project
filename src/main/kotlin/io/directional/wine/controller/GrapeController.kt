package io.directional.wine.controller

import io.directional.wine.dto.CreateGrapeRequest
import io.directional.wine.service.GrapeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class GrapeController(
    private val grapeService: GrapeService
) {

    @PostMapping("/grapes")
    fun createGrape(
        @RequestBody createGrapeRequest: CreateGrapeRequest
    ): ResponseEntity<Unit> {

        grapeService.createGrape(createGrapeRequest)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .build()
    }

    @PutMapping("/{grapeId}/grapes")
    fun updateGrape(
        @PathVariable grapeId: Long,
        @RequestBody createGrapeRequest: CreateGrapeRequest
    ): ResponseEntity<Unit> {

        grapeService.updateGrape(grapeId, createGrapeRequest)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    @DeleteMapping("/{grapeId}/grapes")
    fun deleteGrape(
        @PathVariable grapeId: Long
    ): ResponseEntity<Unit> {

        grapeService.deleteGrape(grapeId)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}