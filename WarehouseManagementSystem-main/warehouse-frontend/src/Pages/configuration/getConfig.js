import { listVehicles } from "../services/DealershipService"
import { listEmployees } from "../services/EmployeeService"
import { listLogs } from "../services/AuditLogService"
export const getConfig = {
    vehicles: listVehicles(),
    employees: listEmployees(),
    auditLogs: listLogs()
}