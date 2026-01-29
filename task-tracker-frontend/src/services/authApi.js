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
    getAllTaskList: () => api.get('/tasklists'),
    createTaskList: (formData) => api.post('/tasklists', formData)
}

export const taskApi = {
    getAllTask: (taskListId) => api.get(`/tasklists/${taskListId}/tasks`),
    createTask: (taskListId,data) => api.post(`/tasklists/${taskListId}/tasks`, data)
}

