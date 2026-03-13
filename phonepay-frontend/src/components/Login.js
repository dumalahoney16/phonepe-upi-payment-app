import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { FaIdBadge, FaLock } from "react-icons/fa";
import "./Auth.css";

function Login() {

  const navigate = useNavigate();

  const [user, setUser] = useState({
    upiId: "",
    pin: ""
  });

  const [showPin, setShowPin] = useState(false);
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const login = async (e) => {

    e.preventDefault();
    setLoading(true);

    const res = await fetch("http://localhost:8080/user/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(user)
    });

    const msg = await res.text();
    alert(msg);

    setLoading(false);

    if (res.ok) {
      localStorage.setItem("upiId", user.upiId);
      navigate("/dashboard");
    }
  };

  return (

    <div className="auth-container">

      <form className="auth-form" onSubmit={login}>
        <h1>PhonePe</h1>
        <h2>Login</h2>

        <div className="input-group">
          <FaIdBadge className="icon"/>
          <input
            name="upiId"
            placeholder="UPI ID"
            onChange={handleChange}
            required
          />
        </div>

        <div className="input-group">
          <FaLock className="icon"/>
          <input
            type={showPin ? "text" : "password"}
            name="pin"
            placeholder="PIN"
            onChange={handleChange}
            required
          />
          <span
            className="show-pin"
            onClick={() => setShowPin(!showPin)}
          >
            {showPin ? "Hide" : "Show"}
          </span>
        </div>

        <button type="submit" disabled={loading}>
          {loading ? "Logging in..." : "Login"}
        </button>

        <p>
          New user? <Link to="/register">Register</Link>
        </p>

      </form>

    </div>

  );
}

export default Login;