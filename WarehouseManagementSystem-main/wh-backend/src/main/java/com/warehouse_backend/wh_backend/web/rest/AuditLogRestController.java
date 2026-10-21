package com.warehouse_backend.wh_backend.web.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse_backend.wh_backend.services.AuditLogService;
import com.warehouse_backend.wh_backend.web.models.AuditLog;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/audit-log")
public class AuditLogRestController {
    private final AuditLogService auditLogService;

    public AuditLogRestController(AuditLogService auditLogService){
        this.auditLogService = auditLogService;
    }

    @GetMapping
    public List<AuditLog> getAll(){
        return this.auditLogService.getAllLogs();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuditLog addLog(@RequestBody AuditLog log){
        return this.auditLogService.createLog(log);
    }

}
