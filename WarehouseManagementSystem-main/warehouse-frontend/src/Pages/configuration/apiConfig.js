import { dealershipApi } from "../services/DealershipService";
import { api } from "../services/EmployeeService";
import { logApi } from "../services/AuditLogService";

export const apiConfig = {
    vehicles: dealershipApi,
    employees: api,
    auditLogs: logApi
};