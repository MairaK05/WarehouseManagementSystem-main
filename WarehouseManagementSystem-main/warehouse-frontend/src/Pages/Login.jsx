import React from "react";
import { Form, useLocation, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import { loginApi } from "./services/LoginService";
import { useDispatch, useSelector } from "react-redux";
import { userLogin, loginSuccessful, loginUnsuccessful } from "../Slices/userSlice";

export const Login = () => { //Login form
    const isLoggedIn = useSelector(state => state.login.loggedIn);
    const navigate = useNavigate();
    const location = useLocation();
    useEffect(() => {
        if (isLoggedIn && location.state?.from){
            navigate(location.state.from);
        }
    },[]);
    const dispatch = useDispatch(); //We use this to dispatch our login actions and induce state change via redux
    dispatch(userLogin()); //login process begins
    async function handleSubmit(e) { //when user presses submit
        e.preventDefault();
        await loginApi.post("", JSON.stringify(loginForm)).then((response) => {
            console.log("token" + response.data);
            localStorage.setItem("token", response.data);
            console.log("Token Stored");
            console.log(localStorage.getItem("token"));
            dispatch(
                loginSuccessful({
                    user: username,
                    loggedIn:true,
                    token: localStorage.getItem("token")
                })
            );
            if (location.state?.from) {
                navigate(location.state.from);
            }
            else {
                navigate("/");
            }
            return response.data;
        }).catch((err) => {
            alert("Login Unsuccessful");
            dispatch(loginUnsuccessful({err: err}));
        })
    }
    const [loginForm, setLoginForm] = useState({username: "", password: ""});

    const {username, password} = loginForm; //this is the object that will be posted with axios
    
    const onInputChange = (e) => {
        setLoginForm({...loginForm, [e.target.name]: e.target.value});
    }
    return (
        <>
        <h1 className="form-header">LOGIN</h1>
        <form onSubmit={handleSubmit} id="form">
            <label>Username<input type="text" name="username" onChange={(e) => onInputChange(e)} required/></label>
            
            <label>Password<input type="password" name="password" onChange={(e) => onInputChange(e)} required/></label>

            <input type="Submit" id="submit"></input>
        </form>
        </>
    ); 
}


export default Login;