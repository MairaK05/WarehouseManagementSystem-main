package com.warehouse_backend.wh_backend.web.models;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuditLog {
    private Long logID;
    private String logUser;
    private String logAction;
    private String logRecipient;
    private Long logRecipientID;
    private LocalDateTime logTimestamp;
}