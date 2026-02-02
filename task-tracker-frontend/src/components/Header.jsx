import React from 'react'
import { useNavigate } from 'react-router-dom'


const Header = () => {
    const navigate = useNavigate();
    const handleLogout = () =>{
        localStorage.removeItem("token")
        navigate("/login")
    }
  return (
    <header className="bg-white shadow-lg border-transparent fixed top-0 left-0 w-full z-50">
        <div className="max-w-6xl mx-auto px-6 py-4">
          <div className="flex justify-between items-center">
            <h1 onClick={() => navigate("/dashboard")} className="text-2xl font-bold text-gray-800 hover:cursor-pointer">TaskTracker</h1>
            <button onClick={handleLogout} className="px-4 py-2 bg-red-500 hover:cursor-pointer hover:bg-red-600 text-white rounded-lg">
              Logout
            </button>
          </div>
        </div>
      </header>
  )
}

export default Header