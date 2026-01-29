import axios from "axios"

const API_BASE = "/api"

const api = axios.create({
  baseURL: API_BASE,
  withCredentials: true  // Cookie
})

// AUTOMATIC TOKEN
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

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

export const taskListApi = {
    getAll: () => api.get('/tasklists'),
    create: (formData) => api.post('/tasklists', formData)
}

