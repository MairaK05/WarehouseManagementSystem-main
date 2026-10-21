import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { addLog } from "./services/AuditLogService";
import { useSelector } from "react-redux";

import { fieldConfig } from "./configuration/fieldConfig";
import { apiConfig } from "./configuration/apiConfig";
import { idConfig } from "./configuration/idConfig";

export default function UpdateEntity() {
    const username = useSelector(state => state.login.user);
    const { entity } = useParams();
    const { id } = useParams();
    let nav = useNavigate();
    console.log(id);

    const fields = fieldConfig[entity];

    const [ent, setEntity] = useState(
        Object.fromEntries(
            fields.map((field) => [field.name, ""])
        )
    , []);

    useEffect(() => {
        (apiConfig[entity]).get(`/${id}`).then((response) => {
            setEntity(
                Object.fromEntries(
                fields.map((field) => [field.name, response.data[field.name]])
                )
            );
            console.log(response.data);
        }).catch(error => {
            console.error(error);
        })
    }, [])

    const handleSubmit = (e) => {
        e.preventDefault();

        const updatedEntity = {
            ...ent,
            [idConfig[entity]]: Number(id)
        };

        (apiConfig[entity]).put(`/${id}`, updatedEntity).then(async (response) => {
            console.log(response);
            await addLog(username, 
                "UPDATE", entity, id, new Date());
            alert(`${entity} updated successfully!`);
        }).catch(error => {
            console.error(error);
        })
    }
    return (
        <>
        <div>
        <button onClick={() => nav(-1)} id="return-button">Return</button>
        <h2 className="form-header">Update {entity}</h2>
        </div>
        <div>
        <form onSubmit={handleSubmit} id="form">
            {fields.map(field => (
            <>
                <label>{field.label}</label>
                <input type={field.type || "text"} name={field.name} value={ent[field.name]} onChange={(e) => setEntity({...ent, [field.name]: e.target.value})} />
            </>
            ))}
            <input type="submit" id="submit"/>
        </form>
        </div>
        </>
    );
}