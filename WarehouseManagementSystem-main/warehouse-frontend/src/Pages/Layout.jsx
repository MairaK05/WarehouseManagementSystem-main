import React from "react";
import { Link, Navigate, Outlet, redirect, NavLink, Routes, Route } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { useLocation } from "react-router-dom";
import { useDispatch } from "react-redux";
import { userLogout } from "../Slices/userSlice";
import { useSelector } from "react-redux";
import userIcon from "../images/User.png";
import Login from "./Login";

const Layout = () => {
    const isLoggedIn = useSelector(state => state.login.loggedIn);
    const username = useSelector(state => state.login.user);

    console.log(username);
    console.log("isLoggedin" + isLoggedIn);
    const dispatch = useDispatch();
    const handleLogout = () => { //Handle logout
        dispatch(userLogout());
        localStorage.removeItem("token");
    }
    
    return (
        <div>
        <header id='layout'>
            <h1>
                <button onClick={handleLogout} hidden={!isLoggedIn} id='header-link'>Logout</button>
                <button hidden={isLoggedIn}><Link to={'/login'} id='login-link'>Login</Link></button>
                <Link to={'/'} className={'header-link'}>Dealership Manager</Link>
                <label>Hello {username}!<img src={userIcon}></img></label>
            </h1>
        </header>

        <div id={'main'}>
            <Outlet />
        </div>
        </div>
    )

}

export default Layout;