import axios from "axios";

const REST_API_BASE_URL = '/api/employees';
const token = localStorage.getItem("token");
console.log(localStorage.getItem("token"));
export const listEmployees = () => {
    return axios.get(REST_API_BASE_URL, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    }).then((response) => {
        console.log("Getting employees....")
        console.log(response);
        return response;
    }).catch(error => {
        console.error(error);
    })
}

export const api = axios.create({
    baseURL: REST_API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem("token")}`
    },
});