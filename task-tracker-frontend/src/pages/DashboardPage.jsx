import React from 'react'
import { PlusIcon } from '@heroicons/react/24/outline'
import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { authApi, taskListApi } from '../services/authApi'

const DashboardPage = () => {

  const navigate = useNavigate();

  const [clicked, setClicked] = useState(false)
  const [tasklists,setTasklists] = useState([])


  useEffect(() =>{
    const fetchTaskLists = async() =>{
    const response = await taskListApi.getAllTaskList()
    console.log('Backendden gelen:', response.data) 
    setTasklists(response.data)
  }
    fetchTaskLists();
  },[])

  

  const createNewKartAndTaskList = () =>{
    setClicked(true);
  }


  const createNewTaskList = () =>{
    navigate("/new-tasklist")
  }

  return (
    
    <div className='min-h-screen bg-slate-200 '>
    
     <header className="bg-white shadow-lg border-transparent fixed top-0 left-0 w-full z-50">
        <div className="max-w-6xl mx-auto px-6 py-4">
          <div className="flex justify-between items-center">
            <h1 className="text-2xl font-bold text-gray-800">TaskTracker</h1>
            <button className="px-4 py-2 bg-red-500 hover:cursor-pointer hover:bg-red-600 text-white rounded-lg">
              Logout
            </button>
          </div>
        </div>
      </header>


  <main className="pt-20">  {/* Header boyu kadar padding */}
    
    {/* ORTADA ŞIK KART */}
    <div className="flex items-center justify-center py-12 px-6">
      <div className="bg-slate-600 backdrop-blur-xl p-10 rounded-3xl shadow-2xl max-w-lg w-full mx-auto flex flex-col items-center text-center text-white space-y-6">
        <h1 className="text-3xl font-bold">MY TASKLISTS</h1>
        <button onClick={createNewKartAndTaskList} className="bg-slate-950 hover:cursor-pointer hover:bg-slate-900 px-8 py-4 w-full max-w-sm flex items-center justify-center text-amber-50 rounded-2xl font-bold transition-all duration-200 hover:scale-[1.02] shadow-xl group">
          <PlusIcon className="h-6 w-6 mr-3 group-hover:text-red-400 transition-colors" />
          CREATE NEW TASKLIST
        </button>
      </div>
    </div>
      
        {/* TASKLIST GRID */}
    <div className="max-w-4xl mx-auto px-6 pb-12">
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
        
   
        {tasklists.map((tasklist) => (
         
            <div key={tasklist.id} onClick={() => navigate(`/tasklists/${tasklist.id}/tasks`)} className="group cursor-pointer bg-white rounded-2xl p-8 shadow-lg hover:shadow-2xl hover:-translate-y-2 transition-all duration-300">
              <h3 className="text-xl font-bold text-gray-800 mb-3 line-clamp-2">
                Title: {tasklist.title}
              </h3>
              <div className="text-sm font-normal text-gray-600 mb-3 line-clamp-1">
                {tasklist.description}
              </div>
              <div className="flex items-center text-sm text-gray-500 mb-2 pt-5">
                <div className="w-2 h-2 bg-orange-500 rounded-full mr-2"></div>
                12 Tasks • In Progress
              </div>
            </div>
           
))}
       
        {clicked ? (<div onClick={createNewTaskList} className="bg-gradient-to-br from-gray-50 to-gray-100 rounded-2xl p-8 border-2 border-dashed border-gray-300 hover:border-blue-300 transition-all cursor-pointer hover:bg-blue-50">
          <div className="flex flex-col items-center justify-center h-full">
            <PlusIcon className="h-12 w-12 text-gray-400 mb-4" />
            <h3 className="text-lg font-semibold text-gray-600 mb-2">New List</h3>
            <p className="text-sm text-gray-500">Click to create</p>
          </div>
        </div>) : null}
        

      </div>
    </div>
    </main>
    </div>
  )
}

export default DashboardPage