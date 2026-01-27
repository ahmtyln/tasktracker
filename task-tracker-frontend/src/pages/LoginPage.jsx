import React, {useState} from 'react'
import { Link } from 'react-router-dom'
import { authApi } from '../services/authApi'
import { AxiosError } from 'axios'


const LoginPage = () => {
const [email,setEmail] = useState("")
const [password, setPassword] = useState("")
const [loading, setLoading] = useState(false)
const [error, setError] = useState("")


const handleLoginSubmit = async (e) =>{
  e.preventDefault()
  setLoading(true)
  setError("")

  try {
    const response = await authApi.login({email,password})
    localStorage.setItem("token", response.token)
    setEmail("")
    setPassword("")
    console.log('✅ Login geschafft!', response)
  } catch (err) {
    setError("Login fehlgeschlagen")
  }finally {
    setLoading(false)
  }
}

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-blue-400 via-indigo-300 to-violet-200">
      <div className="bg-white/90 backdrop-blur-xl p-10 rounded-3xl shadow-2xl max-w-md w-full mx-6">
        <h1 className="text-3xl font-bold bg-gradient-to-r from-gray-800 to-gray-500 bg-clip-text text-transparent pb-1 mb-5 text-center">
          Task Tracker
        </h1>
        
        <form className="space-y-6" onSubmit={handleLoginSubmit}>
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">Email</label>
            <input onChange={(e) => setEmail(e.target.value)}
              value={email}
              type="email" 
              className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200"
              placeholder="email@example.com"
            />
          </div>
          
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">Password</label>
            <input onChange={(e) => setPassword(e.target.value)}
              value={password}
              type="password" 
              className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200"
              placeholder="••••••••"
            />
          </div>
          
          <button 
            type="submit"
            className="w-full bg-gradient-to-r from-rose-400 to-rose-300 hover:from-rose-500 hover:to-indigo-700 text-white font-bold py-4 px-6 rounded-2xl transition-all duration-200 transform hover:scale-[1.02] hover:cursor-pointer shadow-xl"
          >
            Login
          </button>
        </form>
        <p className='flex-direction column text-end mt-6 text-sm text-emerald-400'>
            <p>Don't you have an account?</p>
            <Link to="/register" className='transition-all duration-200 transform hover:scale-[2] hover:text-emerald-950'>
              <p>Register</p>
            </Link>
        </p>
      </div>
    </div>
  )
}

export default LoginPage
