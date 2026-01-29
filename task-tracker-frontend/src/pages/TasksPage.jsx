import React from 'react'
import { PlusIcon,ArrowUturnLeftIcon,CheckIcon, ClockIcon, CalendarIcon } from '@heroicons/react/24/outline'

import { useNavigate } from 'react-router-dom'

const TasksPage = () => {
    const navigate = useNavigate();
  return (
    <div className='min-h-screen bg-slate-200 '>
        <header className="bg-white shadow-lg border-transparent fixed top-0 left-0 w-full z-50">
            <div className="max-w-6xl mx-auto px-6 py-4">
                <div className="flex justify-between items-center">
                    <h1 onClick={() => navigate('/dashboard')} className="text-2xl font-bold text-gray-800 hover:text-gray-950 hover:cursor-pointer">TaskTracker</h1>
                    <button className="px-4 py-2 bg-red-500 hover:cursor-pointer hover:bg-red-600 text-white rounded-lg">
                    Logout
                    </button>
                </div>
            </div>
        </header>

        <div className="flex items-center justify-center mt-12 py-12 px-6">
            <div className="bg-slate-600 backdrop-blur-xl p-10 rounded-3xl shadow-2xl max-w-lg w-full mx-auto flex flex-col items-center text-center text-white space-y-6">
                <div className="flex items-center justify-center">
                    <button 
                        onClick={() => navigate('/dashboard')}
                        className="text-sm text-white hover:text-gray-900 hover:cursor-pointer transition-colors"
                    >
                        <ArrowUturnLeftIcon className="block w-6 mr-6" />
                        
                    </button>
                    <h1 className='text-3xl font-bold mr-6'>TASKS</h1>
                </div>
                <button className="bg-slate-950 hover:cursor-pointer hover:bg-slate-900 px-8 py-4 w-full max-w-sm flex items-center justify-center text-amber-50 rounded-2xl font-bold transition-all duration-200 hover:scale-[1.02] shadow-xl group">
                <PlusIcon className="h-6 w-6 mr-3 group-hover:text-red-400 transition-colors" />
                CREATE NEW TASK
                </button>
            </div>
        </div>


        <div className="group bg-white rounded-xl p-6 shadow-md hover:shadow-xl transition-all duration-300 border hover:border-indigo-200">
            {/* Task checkbox */}
            <div className="flex items-start mb-4">
                <input 
                type="checkbox" 
                className="mt-1 h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded mr-3"
                />
                <h4 className="text-lg font-semibold text-gray-900 line-clamp-2 flex-1">
                    Buy milk, eggs, bread - come back from the market immediately.
                </h4>
            </div>
            
            {/* Task alt info */}
            <div className="flex items-center justify-between text-xs text-gray-500 mb-3">
                <span className="flex items-center">
                <ClockIcon className="h-3 w-3 mr-1" />
                    2 minutes ago
                </span>
                <span className="flex items-center">
                <CalendarIcon className="h-3 w-3 mr-1" />
                Today
                </span>
            </div>
            
            {/* Priority + Category */}
            <div className="flex items-center space-x-2">
                <div className="bg-red-100 px-2 py-1 rounded-full">
                <span className="text-xs font-medium text-red-800">Delete</span>
                </div>
                <div className="bg-blue-100 px-2 py-1 rounded-full">
                <span className="text-xs font-medium text-blue-800">Update</span>
                </div>
            </div>
        </div>


    </div>
  )
}

export default TasksPage