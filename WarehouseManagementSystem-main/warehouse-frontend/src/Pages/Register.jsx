import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import { registerApi } from "./services/LoginService";
import { useSelector } from "react-redux";

export const Register = () => {
    const isLoggedIn = useSelector(state => state.login.loggedIn);
    const navigate = useNavigate();
    console.log(isLoggedIn);
    useEffect(() => {
      console.log(isLoggedIn);
      if (isLoggedIn) {
        console.log("Redirecting...");
        navigate("/");
      }
    }, [isLoggedIn]);

    const handleSubmit = async (e) => { //handle the registration form being submitted
      e.preventDefault();
      console.log(registrationForm);
      await registerApi
        .post("", JSON.stringify(registrationForm))
        .then((response) => {
          console.log("Response data: " + response.data);
          navigate("/login");
          return response.data;
        })
        .catch((err) => {
          alert("Something went wrong! Try again");
        });
    }

    const [registrationForm, setRegistrationForm] = useState({ username: "", password: "", employeeID: "", email: ""});
    const [repeatPassword, setRepeatPassword] = useState("");
    const { username, password, employeeID, email } = registrationForm; //this is the destructured object
    const onInputChange = (e) => {
      setRegistrationForm({ ...registrationForm, [e.target.name]: e.target.value });
    };
    return (
        <>
            <form onSubmit={handleSubmit} id="form">
                <label>
                Username
                <input
                    type="text"
                    name="username"
                    value={username}
                    onChange={(e) => onInputChange(e)}
                    required
                />
                </label>

                <label>
                Password
                <input
                    type="password"
                    name="password"
                    value={password}
                    onChange={(e) => onInputChange(e)}
                    required
                />
                </label>
                <label>
                Repeat Password
                <input
                    type="password"
                    name="repeatPassword"
                    value={repeatPassword}
                    onChange={(e) => {setRepeatPassword(e.target.value);}}
                    required
                />
                </label>
                <label hidden={repeatPassword == "" || repeatPassword == password}>Passwords dont match!</label>
                <label>
                Employee ID
                <input
                    type="text"
                    name="employeeID"
                    value={employeeID}
                    onChange={(e) => onInputChange(e)}
                    required
                />
                </label>
                <label>
                Employee Email
                <input
                    type="email"
                    name="email"
                    value={email}
                    onChange={(e) => onInputChange(e)}
                    required
                />
                </label>


                <input type="Submit" id="submit" disabled={repeatPassword == "" || repeatPassword != password}></input>
            </form>
        </>
    );
}

export default Register;