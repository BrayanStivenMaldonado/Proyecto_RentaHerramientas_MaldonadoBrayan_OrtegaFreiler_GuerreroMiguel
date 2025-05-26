package com.alquiler.alquiler_app.application.service;

import com.alquiler.alquiler_app.domain.entities.Reservation;
import com.alquiler.alquiler_app.domain.entities.Return;
import com.alquiler.alquiler_app.infrastructure.repository.Reservation.ReservationRepository;
import com.alquiler.alquiler_app.infrastructure.repository.Returns.ReturnRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ReturnService {

    private final Path uploadDir = Paths.get("uploads/returns");

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReturnRepository returnRepository;
    
    public List<Return> getAllReturns() {
        return returnRepository.findAll();
    }

    public Return handleReturnUpload(Long reservationId, String condition, 
                                     MultipartFile proofFile, MultipartFile productImage,
                                     String purchaseDate) throws IOException {
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        String proofFileName = UUID.randomUUID() + "_" + proofFile.getOriginalFilename();
        Path proofFilePath = uploadDir.resolve(proofFileName);
        Files.copy(proofFile.getInputStream(), proofFilePath, StandardCopyOption.REPLACE_EXISTING);

        String productImageName = UUID.randomUUID() + "_" + productImage.getOriginalFilename();
        Path productImagePath = uploadDir.resolve(productImageName);
        Files.copy(productImage.getInputStream(), productImagePath, StandardCopyOption.REPLACE_EXISTING);

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        LocalDate date = LocalDate.parse(purchaseDate);

        Return returnObj = new Return();
        returnObj.setReservation(reservation);
        returnObj.setCondition(condition);
        returnObj.setDate(date);
        returnObj.setProofFilePath(proofFilePath.toString());
        returnObj.setProductImagePath(productImagePath.toString());

        return returnRepository.save(returnObj);
    }
}