package com.fast_food_drone_delivery_system.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "drones")
public class Drone implements Serializable {
    private static final long serialVersionUID = 7980135901401334589L;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @ColumnDefault("'AVAILABLE'")
    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "current_lat", precision = 10, scale = 6)
    private BigDecimal currentLat;

    @Column(name = "current_lng", precision = 10, scale = 6)
    private BigDecimal currentLng;

    @ColumnDefault("100.00")
    @Column(name = "battery_level", nullable = false, precision = 5, scale = 2)
    private BigDecimal batteryLevel;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "last_updated", nullable = false)
    private Instant lastUpdated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLat(BigDecimal currentLat) {
        this.currentLat = currentLat;
    }

    public BigDecimal getCurrentLng() {
        return currentLng;
    }

    public void setCurrentLng(BigDecimal currentLng) {
        this.currentLng = currentLng;
    }

    public BigDecimal getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(BigDecimal batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}