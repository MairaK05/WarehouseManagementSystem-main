import React from "react";
import Login from "./Login"
import { NavLink, Route, Routes } from "react-router-dom";

const Home = () => {
    return (
        <div>
            <div className="select-subject">
            <NavLink to={'manager/employees'} className='manager-link'>Employees</NavLink>
            <NavLink to={'manager/vehicles'} className='manager-link'>Vehicles</NavLink>
            <NavLink to={'manager/parts'} className='manager-link'>Vehicle Parts</NavLink>
            <NavLink to={'/auditLogs'} className='manager-link'>Audit Logs</NavLink>
            </div>
            <Routes>
                <Route path="/login" Component={Login}></Route>
            </Routes>
        </div>
    )
}

export default Home;