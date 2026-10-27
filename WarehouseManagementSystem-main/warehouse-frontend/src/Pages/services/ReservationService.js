import axios from "axios";

const REST_API_BASE_URL = '/api/reservations';

export const api = axios.create({
    baseURL: REST_API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem("token")}`
    },
});

export const listReservation = () => {
    return api.get()
    .then((response) => {
        console.log(response);
        return response;
    }).catch((err) => {
        console.log(err);
    })
}

export const addReservation = async (vehicle_id, expiry_date, status, customer_id, employee_id) => {
    const reservation = {
        vehicleID: vehicle_id, 
        expiryDate: expiry_date, 
        status: status, 
        customerID: customer_id, 
        employeeID: employee_id
    }
    console.log(reservation);
    try {
        const response = await api.post("", JSON.stringify(reservation));
        document.getElementById("errorMsg").innerHTML = "Success!"
        console.log(response);
    } catch (err) {
        document.getElementById("errorMsg").innerHTML = "Customer not found";
    }
}