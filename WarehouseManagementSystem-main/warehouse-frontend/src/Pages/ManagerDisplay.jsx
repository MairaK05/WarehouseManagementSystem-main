import React, { useEffect, useState } from "react";
import { Link, NavLink, UNSAFE_AwaitContextProvider } from "react-router-dom";
import deleteIcon from "../images/delete.png";
import editIcon from "../images/Edit.png";
import addCircle from "../images/add_circle.png";
import { useSelector } from "react-redux";
import { addLog } from "./services/AuditLogService";
import { useParams } from "react-router-dom";
import { addReservation } from "./services/ReservationService";
import { fieldConfig } from "./configuration/fieldConfig";
import { apiConfig } from "./configuration/apiConfig";
import { idConfig } from "./configuration/idConfig";
import { getConfig } from "./configuration/getConfig";
import {getEmployeeID} from "./services/LoginService"
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
  const [focusID, setFocusID] = useState();
  const [search, setSearch] = useState("");
  const handleSearch = () => {
    
  }
  const onSearchChange = (e) => {
    let lowerCase = e.target.value.toLowerCase();
    setSearch(lowerCase);
  }
  let fields = fieldConfig[entity]; // we use the correct fields

  function displayModal(model, brand) {
    console.log(brand + " " + model);
    let modal = document.getElementById("reservationModal");
    document.getElementById("reservationHeader").innerHTML =
      "Reserve " + brand + " " + model;
    modal.style.display = "block";
  }

  useEffect(() => {
    getConfig[entity]
      .then((response) => {
        setEntity(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [username]);

  return (
    <>
      <div className="table-container">
        <label>Search</label><input id="searchBar" type="text" onChange={(e) => {onSearchChange(e)}}/>
        <label>{search}</label>
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
              console.log();
              return ( //Only show the ones that include some of the search, if there is one
                <tr key={row[idConfig[entity]]} hidden={!Object.values(row)
                .some((r) => String(r).toLowerCase().includes(search.toLowerCase()))}>
                  <td>{row[idConfig[entity]]}</td>
                  {fields.map((field) => (
                    <>
                      <td>{String(row[field.name])}</td >
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
                  <button
                    hidden={entity !== "vehicles"}
                    id="reserveButton"
                    onClick={() => {
                      displayModal(row["vehicleModel"], row["vehicleBrand"]);
                      setFocusID(row[idConfig[entity]]);
                    }}
                  >
                    Reserve
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
      <ReservationModal vehicleID={focusID} username={username}/>
    </>
  );
};

const ReservationModal = ({vehicleID, username}) => {
  const [custID, setCustID] = useState();
  const [employeeID, setEmployeeID] = useState();

  useEffect(() => {
    getEmployeeID(username).then((response) => {
      console.log(response);
      setEmployeeID(response);
    }).catch((err) => console.log(err))
  }, []);

  console.log("ID TO BE SENT " + typeof employeeID);
  const date = new Date();
  date.setDate(date.getDate() + 7);

  window.onclick = function (event) {
    let modal = document.getElementById("reservationModal");
    if (event.target == modal) {
      modal.style.display = "none";
    }
  }
  return (
    <div className="modal" id="reservationModal">
    <div className="modal-content">
      <h3 id="reservationHeader">Reserve a vehicle</h3>
      <p id="errorMsg"></p>
          <div>
            <form
              onSubmit={async (e) => {
                e.preventDefault();
                await addReservation(vehicleID, date, "Reserved", custID.customerID, employeeID);
              }}
            >
              <label>Customer ID<input type="text" name="customerID" onChange={(e) => setCustID({...custID, ["customerID"]: e.target.value})}></input></label>
              <input type="submit"></input>
              <p>Reserve until {(date).toDateString()}</p>
            </form>
          </div>
    </div>
  </div>
  );
}

export default Manager;
