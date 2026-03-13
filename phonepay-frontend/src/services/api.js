import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8080"
});

export const registerUser = (data) =>
  API.post("/user/register", data);

export const loginUser = (data) =>
  API.post("/user/login", data);

export const addMoney = (data) =>
  API.post("/wallet/add", data);

export const sendMoney = (data) =>
  API.post("/transaction/send", data);

export const getHistory = (upiId) =>
  API.get(`/transaction/history/${upiId}`);

export const getBalance = (upiId) =>
  API.get(`/wallet/balance/${upiId}`);