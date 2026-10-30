import { useState } from 'react'
import {BrowserRouter, Navigate, Outlet, Route, Routes, useLocation} from 'react-router-dom'
import Home from './Pages/Home'
import Login from './Pages/Login'
import ManagerDisplay from './Pages/ManagerDisplay'
import Layout from './Pages/Layout'
import './App.css'
import { useEffect } from 'react'
import { useDispatch } from 'react-redux'
import {loginSuccessful} from './Slices/userSlice'
import {jwtDecode} from 'jwt-decode';
import AddRowForm from './Pages/AddRowForm';
import UpdateEntityForm from './Pages/UpdateEntityForm';
import { useSelector } from 'react-redux';
import Register from './Pages/Register';
import { loadEntity } from './thunks'

function App() {
  const dispatch = useDispatch();
  useEffect(() => { //this sets the user status to logged in if a token exists in local storage, regardless of validity
    dispatch(loadEntity('vehicles'));
    dispatch(loadEntity('employees'));
    dispatch(loadEntity('auditLogs'));
    console.log("Loaded state values");
    const token = localStorage.getItem("token");
    if (token) { 
      try{
      const decoded = jwtDecode(token);
      dispatch(loginSuccessful({user: decoded?.sub, loggedIn: true, token: token}))
      } catch (err) {
        console.log("No token");
      }
    }
  }, [])

  return (
    <div className='App'>
      <BrowserRouter>
      <Routes>
        <Route path={'/'} element={<Layout />}>
          <Route index element={<Home/>}/>
          <Route element={<ProtectedRoutes/>}>
            <Route path={'manager/:entity'} Component={ManagerDisplay}/>
            <Route path={'addRow/:entity'} Component={AddRowForm}></Route>
            <Route path={'update/:entity/:id'} Component={UpdateEntityForm}/>
          </Route>
          <Route path={'login'} Component={Login} element={<Login />}/>
          <Route path={'register'} Component={Register} element={<Register />}/>
        </Route>
      </Routes>
      </BrowserRouter>
    </div>
  )
}

function ProtectedRoutes() {
  const location = useLocation();
  const isLoggedIn = useSelector(state => state.login.loggedIn);
  console.log("login status:" + isLoggedIn);
  return isLoggedIn ? (
    <>
    <Outlet/>
    </>
  ) : <Navigate to="/login" replace state={{from: location}}/>
}


export default App