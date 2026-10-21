import React, { useEffect, useState } from "react";
import { Link, NavLink } from "react-router-dom";
import deleteIcon from "../images/delete.png";
import editIcon from "../images/Edit.png";
import addCircle from "../images/add_circle.png";
import { useSelector } from "react-redux";
import { addLog } from "./services/AuditLogService";
import { useParams } from "react-router-dom";

import { fieldConfig } from "./configuration/fieldConfig";
import { apiConfig } from "./configuration/apiConfig";
import { idConfig } from "./configuration/idConfig";
import { getConfig } from "./configuration/getConfig";

async function handleDelete(id, username, api, entity) {
  //Handle an existing row being deleted
  await api
    .delete(`/${id}`) //send a delete request to the backend using the id of the entity
    .then(async (response) => {
      await addLog(username, "DELETE", entity, id, new Date()); //record this event in the audit log
      return response; //return the response from our backend
    })
    .then(() => {
      window.location.reload(); //reload the window after deleting so that user can see updated window
    })
    .catch((error) => {
      console.log(error); //catch error and log it
    });
}

const Manager = () => {
  const username = useSelector((state) => state.login.user); //we get the username from the redux store
  const [ent, setEntity] = useState([]); //ent will store all of our table values, setEntity will set them
  const { entity } = useParams(); //we obtain the type of entity (e.g, vehicles) that we are dealing with, and handle the table accordingly 

  let fields = fieldConfig[entity]; // we use the correct fields 

  useEffect(() => {
    getConfig[entity]
      .then((response) => {
        setEntity(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <>
      <div className="table-container">
        <table>
          <thead>
            <th>ID</th>
            {fields.map((field) => (
              <th>{field.label}</th>
            ))}
            <th />
          </thead>
          <tbody>
            {ent.map((row) => {
              return (
                <tr key={row[idConfig[entity]]}>
                  <td>{row[idConfig[entity]]}</td>
                  {fields.map((field) => (
                    <>
                      <td>{row[field.name]}</td>
                    </>
                  ))}
                  <button
                    onClick={() =>
                      handleDelete(
                        row[idConfig[entity]],
                        username,
                        apiConfig[entity],
                        entity
                      )
                    }
                  >
                    <img src={deleteIcon} />
                  </button>
                  <button>
                    <Link to={`/update/${entity}/${row[idConfig[entity]]}`}>
                      <img src={editIcon} />
                    </Link>
                  </button>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
      <div>
        <NavLink to={`/addRow/${entity}`}>
          <img src={addCircle} />
          Add Row
        </NavLink>
      </div>
    </>
  );
};

export default Manager;
