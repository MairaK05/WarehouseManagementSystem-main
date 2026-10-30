import React from "react";
import Login from "./Login";
import { NavLink, Route, Routes } from "react-router-dom";
import { useEffect } from "react";
import { api } from "./services/CustomerService";
import { useState } from "react";
import { listReservation } from "./services/ReservationService";
import { listSales } from "./services/salesService";
import { useSelector } from "react-redux";
import {Chart as ChartJS} from "chart.js/auto";
import { CategoryScale } from "chart.js/auto";
import { Pie, Line } from "react-chartjs-2";

const Home = () => {
    const isLoggedIn = useSelector(state => state.login.loggedIn);
    const [reservation, setReservation] = useState([]);
    const[customer, setCustomer] = useState([]);
    const[sales, setSales] = useState([]);
    useEffect(() => {
        listReservation().then((response) => { //get reservation data 
            console.log(response.data);
            setReservation(response.data);
        }).catch((err) => {
            console.log(err);
        });
        api.get().then((response) => { //get customer data
            console.log(response);
            setCustomer(response?.data);
        })
        .catch((err) => console.log(err));
        GetSales({setSales});
        if (isLoggedIn){
            document.getElementById("dashboard").style.display = "flex";
        }
    }, [isLoggedIn]);
    return (
        <div className="homePage" id="homePage">
            <div className="select-subject">
                <NavLink to={'manager/employees'} className='manager-link'>Employees</NavLink>
                <NavLink to={'manager/vehicles'} className='manager-link'>Vehicles</NavLink>
                <NavLink to={'manager/parts'} className='manager-link'>Vehicle Parts</NavLink>
                <NavLink to={'manager/auditLogs'} className='manager-link'>Audit Logs</NavLink>
            </div>
            <div className="dashboard" id="dashboard">
            <div id="expiringRes">
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
            <div id="carStats">
                <h3>Car Statistics</h3>
                
                <Pie data={{
                    
                    labels: sales.map((sale) => [sale.employeeID]),
                    datasets: [
                        {
                            label: "Sales",
                            data: [13, 12, 3],
                        },
                    ]
                }}/>
            </div>
            <div id="totalSales">
                <h3>Total Sales</h3>
                    <p>£{sales.reduce((total, sale) => total + sale.saleAmount, 0)}</p>
                <Line data={{
                    labels: sales.map((sale) => [sale.saleTime]),
                    datasets: [
                        {
                            axis: 'x',
                            label: "Sale earnings",
                            data: sales.map(sale => sale.saleAmount),
                        }
                    ]

                }}/>
            </div>
            </div>
        </div>
    )
}

const GetSales = ({setSales}) => {
    listSales().then((res) => {
        setSales(res.data);
        console.log(res.data);
        return res.data;
    }).catch((err) => {return err});
}

export default Home;