import React from 'react'
import { useState } from 'react'
import { authApi } from '../services/authApi'
import { useNavigate } from 'react-router-dom'

const RegisterPage = () => {

    const [username, setUsername] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState("")
    const navigate = useNavigate();

    const handleRegisterSubmit = async (e) =>{
        e.preventDefault();
        setLoading(true)
        try {
            const response = await authApi.register({username,email,password})
            localStorage.setItem("token",response.token)
            setUsername("")
            setEmail("")
            setPassword("")
            console.log("Register und login geschafft!", response)
            navigate("/dashboard");
        } catch (error) {
            setError("Register fehlgeschlagen")
        }finally{
            setLoading(false)
        }
    }



  return (
    <div className='min-h-screen flex items-center justify-center bg-gradient-to-br from-blue-400 via-indigo-300 to-violet-200'>
        <div className="bg-white/90 backdrop-blur-xl p-10 rounded-3xl shadow-2xl max-w-md w-full mx-6">
            <h1 className='text-3xl text-center font-bold bg-gradient-to-r from-gray-800 to-gray-500 bg-clip-text text-transparent pb-1 mb-5 '>
                Task Tracker
            </h1>

            <form onSubmit={handleRegisterSubmit} className='space-y-6'>
                <div>
                    <label className='block text-sm font-medium text-gray-700 mb-2'>
                        Username
                    </label>
                    <input value={username} onChange={(e)=>setUsername(e.target.value)} type="username" className=' w-full border rounded-xl px-4 py-3 border-gray-300 transition-all duration-200 focus:outline-none focus:ring-2  focus:ring-blue-500  focus:border-transparent' placeholder='Username'/>
                </div>

                  <div>
                    <label className='block text-sm font-medium text-gray-700 mb-2'>
                        Email
                    </label>
                    <input value={email} onChange={(e) => setEmail(e.target.value)} type="email" className=' w-full border rounded-xl px-4 py-3 border-gray-300 transition-all duration-200 focus:outline-none focus:ring-2  focus:ring-blue-500  focus:border-transparent' placeholder='email@example.com'/>
                </div>

                 <div>
                    <label className='block text-sm font-medium text-gray-700 mb-2'>
                        Password
                    </label>
                    <input value={password} onChange={(e) => setPassword(e.target.value)} type="password" className=' w-full border rounded-xl px-4 py-3 border-gray-300 transition-all duration-200 focus:outline-none focus:ring-2  focus:ring-blue-500  focus:border-transparent' placeholder='••••••••'/>
                </div>

                <button type='submit' className=' w-full border-none rounded-xl transition-all duration-200 transform bg-gradient-to-r  from-rose-400 to-rose-300 hover:scale-[1.02] hover:cursor-pointer hover:from-rose-500 hover:to-indigo-700 text-white font-bold py-4 px-6 rounded-2xl shadow-xl'>
                    Register
                </button>
               
            </form>
        </div>

    </div>
  )
}

export default RegisterPage
