import axios from "axios"

const API_BASE = "/api"

// AUTH

export const authApi = {

    login : async (credentials) => {
        const response = await axios.post(`${API_BASE}/auth/login`,credentials)
        return response.data
    },

    register : async (userData) =>{
        const response = await axios.post(`${API_BASE}/auth/register`, userData)
        return response.data
    }
}

