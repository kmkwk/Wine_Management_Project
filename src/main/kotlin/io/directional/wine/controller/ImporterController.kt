package io.directional.wine.controller

import io.directional.wine.payload.request.CreateImporterRequest
import io.directional.wine.payload.response.ImporterNamesResponse
import io.directional.wine.payload.response.ImporterWithWineResponse
import io.directional.wine.service.ImporterService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class ImporterController(
    private val importerService: ImporterService
) {

    @PostMapping("/importers")
    fun createImporter(
        @RequestBody createImporterRequest: CreateImporterRequest
    ): ResponseEntity<Unit> {

        importerService.createImporter(createImporterRequest)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .build()
    }

    @PutMapping("/{importerId}/importers")
    fun updateImporter(
        @PathVariable importerId: Long,
        @RequestBody createImporterRequest: CreateImporterRequest
    ): ResponseEntity<Unit> {

        importerService.updateImporter(importerId, createImporterRequest)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    @DeleteMapping("/{importerId}/importers")
    fun deleteImporter(
        @PathVariable importerId: Long
    ): ResponseEntity<Unit> {

        importerService.deleteImporter(importerId)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    @GetMapping("/importers")
    fun findImporterNameWithWine(
        @RequestParam importerName: String,
    ): ResponseEntity<ImporterWithWineResponse> {

        return ResponseEntity
            .ok(importerService.findImporterNameWithWine(importerName))
    }

    @GetMapping("/importers/all")
    fun findImporterNames(
        @RequestParam importerName: String
    ): ResponseEntity<List<ImporterNamesResponse>> {

        return ResponseEntity
            .ok(importerService.findImporterNames(importerName))
    }
}