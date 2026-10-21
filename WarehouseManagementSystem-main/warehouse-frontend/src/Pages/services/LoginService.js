import axios from "axios";

const REST_API_BASE_URL_LOGIN = '/api/auth/login';
const REST_API_BASE_URL_LOGOUT = '/api/logout';
export const loginApi = axios.create({
    baseURL: REST_API_BASE_URL_LOGIN,
    headers: {
        'Content-Type': 'application/json',
    },
});

export const logoutApi = axios.create({
    baseURL: REST_API_BASE_URL_LOGOUT,
    headers: {
        'Content-Type': 'application/json',
    },
});

