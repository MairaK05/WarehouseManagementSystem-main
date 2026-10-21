import axios from "axios";

const REST_API_BASE_URL = '/api/audit-log';

export const listLogs = () => {
    return axios.get(REST_API_BASE_URL, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`
        }
    });
}


export const logApi = axios.create({
    baseURL: REST_API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem("token")}`
    },
});

export const addLog = async (userResponsible, action, recipient, recipientID, date) => {
    
    const entry = {
        logAction: action, 
        logRecipient: recipient, 
        logRecipientID: recipientID, 
        logUser: userResponsible, 
        logTimestamp: date
    }

    try {
        const response = await logApi.post("", JSON.stringify(entry));
        console.log(response);
    } catch (err) {
        console.log(err);
    }
}