package com.warehouse_backend.wh_backend.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.warehouse_backend.wh_backend.entities.AuditLogEntity;
import com.warehouse_backend.wh_backend.repositories.AuditLogRepository;
import com.warehouse_backend.wh_backend.web.models.AuditLog;

@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository){
        this.auditLogRepository = auditLogRepository;
    }

    public AuditLogEntity translateWebToDb(AuditLog log){
        AuditLogEntity entity = new AuditLogEntity();
        entity.setLogID(log.getLogID());
        entity.setLogAction(log.getLogAction());
        entity.setLogRecipient(log.getLogRecipient());
        entity.setLogRecipientID(log.getLogRecipientID());
        entity.setLogUser(log.getLogUser());
        entity.setLogTimestamp(log.getLogTimestamp());
        return entity;
    }

    public AuditLog translateDbToWeb(AuditLogEntity auditLogEntity){
        return new AuditLog(auditLogEntity.getLogID(), auditLogEntity.getLogUser(), auditLogEntity.getLogAction(), auditLogEntity.getLogRecipient(), 
        auditLogEntity.getLogRecipientID(), auditLogEntity.getLogTimestamp());
    }

    public List<AuditLog> getAllLogs() {
        Iterable<AuditLogEntity> logEntities = this.auditLogRepository.findAll();
        List<AuditLog> logs = new ArrayList<>();

        logEntities.forEach(logEntity -> {
            logs.add(translateDbToWeb(logEntity));
        });

        return logs;
    }

    public AuditLog createLog(AuditLog log) {
        AuditLogEntity entity = this.translateWebToDb(log);
        entity = this.auditLogRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

}
