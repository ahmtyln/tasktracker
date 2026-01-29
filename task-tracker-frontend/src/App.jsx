import React from 'react'
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import LoginPage from './pages/LoginPage'
import RegisterPage from './pages/RegisterPage'
import './App.css'
import DashboardPage from './pages/DashboardPage'
import NewTaskListPage from './pages/NewTaskListPage'
import TasksPage from './pages/TasksPage'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/login' element={<LoginPage/>}/>
        <Route path='/register' element={<RegisterPage/>}/>
        <Route path='/dashboard' element={<DashboardPage/>}/>
        <Route path='/new-tasklist' element={<NewTaskListPage/>}/>
        <Route path='/tasklist/task' element={<TasksPage/>}/>
        <Route path='/' element={<Navigate to="/login" />} />
        <Route path="*" element={<Navigate to="/login" />} />
      </Routes>
    </BrowserRouter>
  )
}


export default App
