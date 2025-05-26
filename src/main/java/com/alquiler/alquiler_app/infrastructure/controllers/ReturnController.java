package com.alquiler.alquiler_app.infrastructure.controllers;

import com.alquiler.alquiler_app.application.service.ReturnService;
import com.alquiler.alquiler_app.domain.entities.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/returns")
public class ReturnController {

    @Autowired
    private ReturnService returnService;

    @PostMapping
    public ResponseEntity<String> procesarDevolucion(
            @RequestParam("reservationId") Long reservationId,
            @RequestParam("condition") String condition,
            @RequestParam("purchaseDate") String purchaseDate,
            @RequestParam("proofFile") MultipartFile proofFile,
            @RequestParam("productImage") MultipartFile productImage
    ) {
        try {
            if (proofFile == null || proofFile.isEmpty()) {
                return ResponseEntity.badRequest().body("El comprobante de pago es obligatorio");
            }
            if (productImage == null || productImage.isEmpty()) {
                return ResponseEntity.badRequest().body("La imagen del producto es obligatoria");
            }
            returnService.handleReturnUpload(reservationId, condition, proofFile, productImage, purchaseDate);

            return ResponseEntity.ok("Devolución registrada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar devolución: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodasLasDevoluciones() {
        try {
            List<Return> devoluciones = returnService.getAllReturns();
            if (devoluciones.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay devoluciones registradas");
            }
            return ResponseEntity.ok(devoluciones);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener devoluciones: " + e.getMessage());
        }
    }

}