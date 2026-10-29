import axios from "axios";
import { useDispatch } from "react-redux";
import { userLogout } from "../../Slices/userSlice";

const REST_API_BASE_URL = '/api/sales';
const token = localStorage.getItem("token");
export const listSales = () => {
    return axios.get(REST_API_BASE_URL, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    }).then((response) => {
        console.log("Getting sales....")
        console.log(response);
        return response;
    }).catch(error => {
        if (error.message.includes("403")){
            console.log("Logout....");
            localStorage.removeItem("token");
        }
    })
}

export const api = axios.create({
    baseURL: REST_API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem("token")}`
    },
});