import axios from "axios"

const REST_API_URL = "http://localhost:8081/bank"

export const createAdmin = (admin) => axios.post(REST_API_URL + "/signUp", admin)