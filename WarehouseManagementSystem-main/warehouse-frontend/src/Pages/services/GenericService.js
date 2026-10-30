import axios from "axios";

const REST_API_BASE_URL = '/api/';

export const api = (url) => axios.create({
    baseURL: REST_API_BASE_URL + url,
    headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem("token")}`
    },
});

export const listEntity = () => {
    return api.get()
    .then((response) => {
        console.log(response);
        return response;
    }).catch((err) => {
        console.log(err);
    })
}

export const addEntity = async (fields, values) => {
    //need fields to create object + values
    fields.forEach(element => {
        
    });
    console.log(entity);
    try {
        const response = await api.post("", JSON.stringify(entity));
        document.getElementById("errorMsg").innerHTML = "Success!"
        console.log(response);
    } catch (err) {
        document.getElementById("errorMsg").innerHTML = "Customer not found";
    }
}