import { listVehicles } from "../services/DealershipService"
import { listEmployees } from "../services/EmployeeService"
import { listLogs } from "../services/AuditLogService"
import { listCustomers } from "../services/CustomerService"
import { listReservation } from "../services/ReservationService"
export const getConfig = {
    vehicles: listVehicles(),
    employees: listEmployees(),
    auditLogs: listLogs(),
    customers: listCustomers(),
    reservations: listReservation()
}