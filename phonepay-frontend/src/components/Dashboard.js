import React, { useEffect, useState } from "react";
import "./Dashboard.css";

function Dashboard() {
  const [balance, setBalance] = useState(0);
  const [screen, setScreen] = useState("home");

  const [receiverUpi, setReceiverUpi] = useState("");
  const [amount, setAmount] = useState("");
  const [pin, setPin] = useState("");

  const [transactions, setTransactions] = useState([]);

  const upiId = localStorage.getItem("upiId");

  useEffect(() => {
    fetch(`http://localhost:8080/wallet/balance/${upiId}`)
      .then(res => res.json())
      .then(data => setBalance(data.balance));
  }, [upiId]);

  const logout = () => {
    localStorage.removeItem("upiId");
    window.location.href = "/";
  };

const sendMoney = async () => {

  if (!receiverUpi) {
    alert("Enter Receiver UPI ID");
    return;
  }

  if (!amount) {
    alert("Enter Amount");
    return;
  }

  if (!pin) {
    alert("Enter PIN");
    return;
  }

  const res = await fetch("http://localhost:8080/transaction/send", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      senderUpi: upiId,
      receiverUpi: receiverUpi,
      amount: parseFloat(amount),
      pin: pin
    })
  });

  const msg = await res.text();
  alert(msg);
};

const addMoney = async () => {

  if (!amount) {
    alert("Enter Amount");
    return;
  }

  const res = await fetch("http://localhost:8080/wallet/add", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      upiId: upiId,
      amount: parseFloat(amount)
    })
  });

  const msg = await res.text();
  alert(msg);
};

  const loadTransactions = () => {
    fetch(`http://localhost:8080/transaction/history/${upiId}`)
      .then(res => res.json())
      .then(data => setTransactions(data));

    setScreen("history");
  };

  if (screen === "home") {
    return (
      <div className="dashboard">
        <div className="navbar">
          <h2>PhonePe Wallet</h2>
          <button onClick={logout}>Logout</button>
        </div>

        <div className="balance-card">
          <p>Wallet Balance</p>
          <h1>₹ {balance}</h1>
        </div>

        <div className="actions">
          <div className="action-card" onClick={() => setScreen("send")}>
            Send Money
          </div>
          <div className="action-card" onClick={() => setScreen("add")}>
            Add Money
          </div>
          <div className="action-card" onClick={loadTransactions}>
            Transactions
          </div>
        </div>
      </div>
    );
  }

  if (screen === "send") {
    return (
      <div className="form-page">
        <h2>Send Money</h2>

        <input
          placeholder="Receiver UPI"
          onChange={(e) => setReceiverUpi(e.target.value)}
        />

        <input
          type="number"
          placeholder="Amount"
          onChange={(e) => setAmount(e.target.value)}
        />

        <input
          type="password"
          placeholder="PIN"
          onChange={(e) => setPin(e.target.value)}
        />

        <button onClick={sendMoney}>Send</button>
        <button onClick={() => setScreen("home")}>Back</button>
      </div>
    );
  }

  if (screen === "add") {
    return (
      <div className="form-page">
        <h2>Add Money</h2>

        <input
          type="number"
          placeholder="Amount"
          onChange={(e) => setAmount(e.target.value)}
        />

        <button onClick={addMoney}>Add Money</button>
        <button onClick={() => setScreen("home")}>Back</button>
      </div>
    );
  }

  if (screen === "history") {
    return (
      <div className="history-page">
        <h2>Transaction History</h2>

        {transactions.map((t, i) => (
  <div 
    key={i} 
    className={`transaction-card ${t.status === "SUCCESS" ? "success" : "failed"}`}
  >
    <p>{t.senderName} → {t.receiverName}</p>
    <span>₹ {t.amount}</span>
    <div className="transaction-meta">
      <small>Status: {t.status}</small><br />
      <small>Date: {new Date(t.transactionDate).toLocaleString()}</small>
    </div>
  </div>
))}


        <button onClick={() => setScreen("home")}>Back</button>
      </div>
    );
  }
}

export default Dashboard;
