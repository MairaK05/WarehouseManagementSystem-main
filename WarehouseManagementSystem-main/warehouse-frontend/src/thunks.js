import axios from "axios"

import { apiConfig } from "./Pages/configuration/apiConfig";
import { loadingConfig } from "./Pages/configuration/loadingConfig";


const vehicleAPI = axios.create({
    baseURL: '/api/vehicles',
    headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem("token")}`
    },
});

export const loadEntity = (name) => async (dispatch) => {
    dispatch(loadingConfig[name][0]);
    console.log(dispatch(loadingConfig[name][0]));
    try {
        const response = await apiConfig[name].get();
        const entities = response.data;
        console.log(entities);
        dispatch(loadingConfig[name][1](entities));
    } catch (e) {
        loadingConfig[name][2];
    }
}

export const addVehicle = (newVehicle) => async (dispatch, getState) => {
    try {
        const response = await vehicleAPI.post('', newVehicle);
        const newVehicleData = response.data;
        const updatedVehicles = getState().vehicles.value.concat(newVehicleData);
        dispatch(updateVehicles(updatedVehicles));
    } catch (err) {
        console.log(e);
    }
}

