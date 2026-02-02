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
        const response = await api.post(`/auth/login`,credentials)
        localStorage.setItem('token', response.data.token)
        return response.data
    },

    register : async (userData) =>{
        const response = await api.post(`/auth/register`, userData)
        return response.data
    }
}

export const taskListApi = {
    getAllTaskList: () => api.get('/tasklists'),
    createTaskList: (formData) => api.post('/tasklists', formData)
}

export const taskApi = {
    getAllTask: (taskListId) => api.get(`/tasklists/${taskListId}/tasks`),
    getOneTask: (taskListId,id) => api.get(`/tasklists/${taskListId}/tasks/${id}`),
    createTask: (taskListId,data) => api.post(`/tasklists/${taskListId}/tasks`, data),
    deleteTask: (taskListId,id) => api.delete(`/tasklists/${taskListId}/tasks/${id}`),
    updateTask: (taskListId,id,data) => api.put(`/tasklists/${taskListId}/tasks/${id}`,data)
}

