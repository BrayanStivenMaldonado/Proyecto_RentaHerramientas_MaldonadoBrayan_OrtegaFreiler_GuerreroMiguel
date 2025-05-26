package com.alquiler.alquiler_app.domain.DTOs;

public class ReturnDTO {
    private int reservationId;
    private String condition;
    private String purchaseDate;
    private String proofFileName;
    private String productImageName;

    public ReturnDTO(int reservationId, String condition, String purchaseDate, String proofFileName, String productImageName) {
        this.reservationId = reservationId;
        this.condition = condition;
        this.purchaseDate = purchaseDate;
        this.proofFileName = proofFileName;
        this.productImageName = productImageName;
    }

    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public String getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(String purchaseDate) { this.purchaseDate = purchaseDate; }

    public String getProofFileName() { return proofFileName; }
    public void setProofFileName(String proofFileName) { this.proofFileName = proofFileName; }

    public String getProductImageName() { return productImageName; }
    public void setProductImageName(String productImageName) { this.productImageName = productImageName; }
}
