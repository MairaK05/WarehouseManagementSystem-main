import { vehicleFields } from "../entities/VehicleEntity";
import { employeeFields } from "../entities/EmployeeEntity";
import { logFields } from "../entities/AuditLogEntity";
export const fieldConfig = {
    vehicles: vehicleFields,
    employees: employeeFields,
    auditLogs: logFields
};