import { employeeLoading , vehicleLoading, logLoading } from "../../Slices/entityLoadingSlice";

export const loadingConfig = {
    vehicles: [vehicleLoading.loadingStarted, vehicleLoading.loadingCompleted, vehicleLoading.loadingFailed],
    employees: [employeeLoading.loadingStarted, employeeLoading.loadingCompleted, employeeLoading.loadingFailed],
    auditLogs: [logLoading.loadingStarted, logLoading.loadingCompleted, logLoading.loadingFailed]
}