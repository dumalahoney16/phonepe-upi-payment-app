import React, { useState } from "react";
import { Link } from "react-router-dom";
import { FaUser, FaPhone, FaIdBadge, FaLock } from "react-icons/fa";
import "./Auth.css";

function Register() {

  const [user, setUser] = useState({
    name: "",
    phoneNumber: "",
    upiId: "",
    pin: ""
  });

  const [showPin, setShowPin] = useState(false);

  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const register = async (e) => {

    e.preventDefault();

    const res = await fetch("http://localhost:8080/user/register", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(user)
    });

    const msg = await res.text();
    alert(msg);
  };

  return (

    <div className="auth-container">

      <form className="auth-form" onSubmit={register}>
        <h1>PhonePe</h1>
        <h2>Create Account</h2>

        <div className="input-group">
          <FaUser className="icon"/>
          <input
            name="name"
            placeholder="Name"
            onChange={handleChange}
          />
        </div>

        <div className="input-group">
          <FaPhone className="icon"/>
          <input
            name="phoneNumber"
            placeholder="Phone Number"
            onChange={handleChange}
          />
        </div>

        <div className="input-group">
          <FaIdBadge className="icon"/>
          <input
            name="upiId"
            placeholder="UPI ID"
            onChange={handleChange}
          />
        </div>

        <div className="input-group">
          <FaLock className="icon"/>
          <input
            type={showPin ? "text" : "password"}
            name="pin"
            placeholder="PIN"
            onChange={handleChange}
          />
          <span className="show-pin" onClick={() => setShowPin(!showPin)}>
            {showPin ? "Hide" : "Show"}
          </span>
        </div>

        <button>Register</button>

        <p>
          Already have account? <Link to="/">Login</Link>
        </p>

      </form>

    </div>

  );
}

export default Register;