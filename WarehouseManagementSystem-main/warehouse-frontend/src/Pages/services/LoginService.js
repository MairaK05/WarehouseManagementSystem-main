import axios from "axios";


const REST_API_BASE_URL = '/api/auth';

export const loginApi = axios.create({
    baseURL: REST_API_BASE_URL + "/login",
    headers: {
        'Content-Type': 'application/json',
    },
});
export const registerApi = axios.create({
    baseURL: REST_API_BASE_URL + "/register",
    headers: {
        'Content-Type': 'application/json',
    },
});

export const authApi = axios.create({
    baseURL: REST_API_BASE_URL + '/getEmployeeID',
    headers: {
        'Content-Type': 'application/json',
    },
});
export const getEmployeeID = async (username) => {
    console.log("sending username...");
    try {
        const response = await authApi.get("", {
            params: {
                username: username
            }
        });
        console.log(response.data);
        return response.data;
    } catch (error) {
        console.log(error);
        return error;
    }
};

