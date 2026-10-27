import React from "react";
import Login from "./Login";
import { NavLink, Route, Routes } from "react-router-dom";
import { useEffect } from "react";
import { api } from "./services/CustomerService";
import { useState } from "react";
import { listReservation } from "./services/ReservationService";
import { getConfig } from "./configuration/getConfig";
import { useSelector } from "react-redux";
const Home = () => {
    const isLoggedIn = useSelector(state => state.login.loggedIn);
    const [reservation, setReservation] = useState([]);
    const[customer, setCustomer] = useState([]);
    useEffect(() => {
        listReservation().then((response) => {
            console.log(response.data);
            setReservation(response.data);
        }).catch((err) => {
            console.log(err);
        });
        api.get().then((response) => {
            console.log(response);
            setCustomer(response?.data);
        })
        .catch((err) => console.log(err));
    }, []);
    console.log(customer);
    return (
        <div className="homePage">
            <div className="select-subject">
                <NavLink to={'manager/employees'} className='manager-link'>Employees</NavLink>
                <NavLink to={'manager/vehicles'} className='manager-link'>Vehicles</NavLink>
                <NavLink to={'manager/parts'} className='manager-link'>Vehicle Parts</NavLink>
                <NavLink to={'/auditLogs'} className='manager-link'>Audit Logs</NavLink>
            </div>
            <div id="expiringRes" hidden={!isLoggedIn}>
                <h3>Expiring Reservations</h3>
                <ul>
                { reservation.map            
                    ( (res) => 
                    {
                        return (
                        <>
                        <li>{customer[res?.customerID - 1]?.phoneNumber + " " + res?.reservationID + " " + res?.expiryDate}</li>
                        </>
                        )
                    }
                    )   
                }
                </ul>
            </div>
        </div>
    )
}

export default Home;