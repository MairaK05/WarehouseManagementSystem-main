import { createSlice } from "@reduxjs/toolkit";

function makeLoadingSlice(name){
  return createSlice({
    name: name,
    initialState: {
        value: {
            completed: true,
            successful: false,
        },
    },
    reducers: {
        loadingStarted: (state) => {
            state.value.completed = false;
        },
        loadingCompleted: (state) => {
            state.value.completed = true;
            state.value.successful = true;
        },
        loadingFailed: (state) => {
            state.value.completed = true;
            state.value.successful = false;
        }
    }
});
}

export const vehicleLoadingSlice = makeLoadingSlice('vehicleLoadingSlice');
export const employeeLoadingSlice = makeLoadingSlice('employeeLoadingSlice');
export const auditLoadingSlice = makeLoadingSlice('auditLoadingSlice');

export const vehicleLoading = vehicleLoadingSlice.actions;
export const employeeLoading = employeeLoadingSlice.actions;
export const logLoading = auditLoadingSlice.actions;