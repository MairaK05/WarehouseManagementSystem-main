import { createSlice } from "@reduxjs/toolkit";
import { loadingConfig } from '../Pages/configuration/loadingConfig';
function makeEntitySlice(name){ 
    return createSlice({
        name: name,
        initialState: { value: []},
        reducers: {
            addEntity: (state, action) => {
                state.value = [...state.value, action.payload]
            },
            updateEntities: (state, action) => {
                const updatedEntity = action.payload;
                state.value = updatedEntity;
            },
            deleteEntity: (state, action) => {
                const entity = action.payload;
                state.value = state.value.filter(e => e.entity !== entity);
            },
        },
        extraReducers: (builder) => {
            console.log(loadingConfig[name][1]);
            builder.addCase(loadingConfig[name][1], (state, action) => {
                state.value = action.payload;
            })
        }
    })
}

export const employeeSlice = makeEntitySlice('employees');
export const vehicleSlice = makeEntitySlice('vehicles');
export const auditSlice = makeEntitySlice('auditLogs');

export const vehicleActions = vehicleSlice.actions;
export const employeeActions = employeeSlice.actions;
export const logActions = auditSlice.actions;

