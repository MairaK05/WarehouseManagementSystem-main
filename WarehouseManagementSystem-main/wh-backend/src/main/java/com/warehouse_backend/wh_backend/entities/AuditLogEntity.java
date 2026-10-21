package com.warehouse_backend.wh_backend.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="AUDIT_LOG")
@ToString
@Data
public class AuditLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="LOG_ID")
    Long logID;
    @Column(name="LOG_USER")
    String logUser;
    @Column(name="LOG_ACTION")
    String logAction;
    @Column(name="LOG_RECIPIENT")
    String logRecipient;
    @Column(name="LOG_RECIPIENT_ID")
    Long logRecipientID;
    @Column(name="LOG_TIMESTAMP")
    Instant logTimestamp;
}
