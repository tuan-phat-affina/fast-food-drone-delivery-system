package com.fast_food_drone_delivery_system.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "delivery_tasks")
public class DeliveryTask implements Serializable {
    private static final long serialVersionUID = 2851050709728106410L;
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "drone_id")
    private Drone drone;

    @Column(name = "pickup_lat", nullable = false, precision = 10, scale = 6)
    private BigDecimal pickupLat;

    @Column(name = "pickup_lng", nullable = false, precision = 10, scale = 6)
    private BigDecimal pickupLng;

    @Column(name = "dropoff_lat", nullable = false, precision = 10, scale = 6)
    private BigDecimal dropoffLat;

    @Column(name = "dropoff_lng", nullable = false, precision = 10, scale = 6)
    private BigDecimal dropoffLng;

    @ColumnDefault("'PENDING'")
    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "assigned_at", nullable = false)
    private Instant assignedAt;

    @Column(name = "completed_at")
    private Instant completedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public BigDecimal getPickupLat() {
        return pickupLat;
    }

    public void setPickupLat(BigDecimal pickupLat) {
        this.pickupLat = pickupLat;
    }

    public BigDecimal getPickupLng() {
        return pickupLng;
    }

    public void setPickupLng(BigDecimal pickupLng) {
        this.pickupLng = pickupLng;
    }

    public BigDecimal getDropoffLat() {
        return dropoffLat;
    }

    public void setDropoffLat(BigDecimal dropoffLat) {
        this.dropoffLat = dropoffLat;
    }

    public BigDecimal getDropoffLng() {
        return dropoffLng;
    }

    public void setDropoffLng(BigDecimal dropoffLng) {
        this.dropoffLng = dropoffLng;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(Instant assignedAt) {
        this.assignedAt = assignedAt;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Instant completedAt) {
        this.completedAt = completedAt;
    }

}