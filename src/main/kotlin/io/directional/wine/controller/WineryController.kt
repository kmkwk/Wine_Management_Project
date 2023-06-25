package io.directional.wine.controller

import io.directional.wine.dto.CreateWineryRequest
import io.directional.wine.dto.UpdateWineryRequest
import io.directional.wine.service.WineryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class WineryController(
    private val wineryService: WineryService
) {

    @PostMapping("/{regionId}/winerys")
    fun createWinery(
        @PathVariable regionId: Long,
        @RequestBody createWineryRequest: CreateWineryRequest
    ): ResponseEntity<Unit> {
        wineryService.createWinery(regionId, createWineryRequest)

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build()
    }

    @PutMapping("/{wineryId}/winerys")
    fun updateWinery(
        @PathVariable wineryId: Long,
        @RequestBody updateWineryRequest: UpdateWineryRequest
    ): ResponseEntity<Unit> {
        wineryService.updateWinery(wineryId, updateWineryRequest)

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
    }

    @DeleteMapping("/{wineryId}/winerys")
    fun deleteWinery(
        @PathVariable wineryId: Long
    ): ResponseEntity<Unit> {
        wineryService.deleteWinery(wineryId)

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
    }
}