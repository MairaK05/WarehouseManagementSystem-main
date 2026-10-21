import axios from "axios";

const REST_API_BASE_URL = '/api/vehicles';

export const listVehicles = () => {
    return axios.get(REST_API_BASE_URL, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`
        }
    });
}

export const dealershipApi = axios.create({
    baseURL: REST_API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem("token")}`
    },
});