import { NavLink, useNavigate, useParams } from "react-router-dom";

import { useState } from "react";
import axios from "axios";
import { addLog } from "./services/AuditLogService";
import { useSelector } from "react-redux";

import { fieldConfig } from "./configuration/fieldConfig";
import { apiConfig } from "./configuration/apiConfig";
import { idConfig } from "./configuration/idConfig";

const AddRowForm = () => {
    const username = useSelector(state => state.login.user);
    const { entity } = useParams(); //name of the entity
    let nav = useNavigate();
    console.log(entity);

    const fields = fieldConfig[entity];
    console.log(fields);
    const [ent, setEntity] = useState(
        Object.fromEntries(
            fields.map((field) => [field.name, ""])
        )
    , []);
    async function handleSubmit(e) { //Handle a new employee being submitted
        e.preventDefault();
        await apiConfig[entity].post("", JSON.stringify(ent)).then(async (response) => {
            await addLog(username, 
            "CREATE", entity, response.data[idConfig[entity]], new Date());
            return response;
        })
            .catch(error => {
                console.error(error);
            })
    }
    const onInputChange = (e) => {
        setEntity({...ent, [e.target.name]: e.target.value}); 
    };

    return (
        <div>
            <button onClick={() => nav(-1)} id="return-button">Return</button>
            <h2 className="form-header">Create new {entity}</h2>
        <div>
        <form onSubmit={handleSubmit} id="form">
            {fields.map(field => (
            <>
                <label>{field.label}</label>
                <input type={field.type || "text"} name={field.name} onChange={onInputChange}/>
            </>
            ))}
            <input type="submit" id="submit"/>
        </form>
        </div>
        </div>
    );
}

export default AddRowForm