import React, { useState } from 'react'
import { ArrowUturnLeftIcon,FlagIcon } from '@heroicons/react/24/outline'
import { useNavigate, useParams } from 'react-router-dom'
import { taskApi } from '../services/authApi';


const NewTaskPage = () => {

    const navigate = useNavigate();
    const {taskListId} = useParams();
    const[title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [priority, setPriority] = useState("MEDIUM");
    const [status, setStatus] = useState("OPEN");
    const [endDate, setEndDate] = useState("");


    const handleNewTaskSubmit = async (e) =>{
        e.preventDefault();
        try {
            const response = await taskApi.createTask(taskListId,{title,description,priority,status,endDate})
            console.log(response)
            navigate(`/tasklists/${taskListId}/tasks`)
        } catch (error) {
             console.log(error)
        }
        
    }


  return (
    <div className='min-h-screen bg-slate-200'>
        <div className="max-w-2xl mx-auto p-8 ">
      {/* Back Button */}
      <button 
        onClick={() => navigate(`/tasklists/${taskListId}/tasks`)}
        className="flex items-center text-gray-600 hover:text-gray-900 hover:cursor-pointer mb-8 transition-colors"
      >
        <ArrowUturnLeftIcon className="h-5 w-5 mr-2" />
        To TaskPage
      </button>

      {/* Form Card */}
      <form onSubmit={handleNewTaskSubmit} className="bg-white rounded-2xl shadow-xl p-8 border">
        <h2 className="text-2xl font-bold text-gray-900 mb-6">New Task</h2>
        
        {/* Title Input */}
        <div className="mb-6">
          <label className="block text-sm font-medium text-gray-700 mb-3">
            Task Name
          </label>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
            className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-1 focus:ring-blue-300 focus:border-blue-500 transition-all"
            placeholder="For example: 'Do Shopping'"
          />
        </div>

        {/* Description Input */}
        <div className="mb-6">
          <label className="block text-sm font-medium text-gray-700 mb-3">
            Description
          </label>
          <textarea
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
            rows={4}
            className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-1 focus:ring-blue-300 focus:border-blue-500 transition-all resize-vertical"
            placeholder="For what is this list used?"
          />
        </div>

         <div className="mb-6">
            <label className="block text-sm font-medium text-gray-700 mb-2">
                Task Priority
            </label>
            <div className="relative">
                <select
                value={priority}
                onChange={(e) => setPriority(e.target.value)}
                className="w-full appearance-none px-4 py-3 pr-10 border border-gray-300 rounded-xl focus:outline-none focus:ring-1 focus:ring-blue-300 focus:border-blue-500 transition-all bg-white"
                >
                <option value="LOW">Low</option>
                <option value="MEDIUM">Medium</option>
                <option value="HIGH">High</option>
                </select>
                <div className="pointer-events-none absolute inset-y-0 right-3 flex items-center text-gray-400">
                <FlagIcon className="h-5 w-5" />
                </div>
            </div>
        </div>

        <div className="mb-6">
            <label className="block text-sm font-medium text-gray-700 mb-2">
                Task Status
            </label>
            <div className="relative">
                <select
                value={status}
                onChange={(e) => setStatus(e.target.value)}
                className="w-full appearance-none px-4 py-3 pr-10 border border-gray-300 rounded-xl focus:outline-none focus:ring-1 focus:ring-blue-300 focus:border-blue-500 transition-all bg-white"
                >
                <option value="OPEN">Open</option>
                <option value="CLOSE">Close</option>
                </select>
                <div className="pointer-events-none absolute inset-y-0 right-3 flex items-center text-gray-400">
                    <FlagIcon className="h-5 w-5" />
                </div>
            </div>
        </div>

        <div className="mb-6">
            <label className="block text-sm font-medium text-gray-700 mb-2">
                End Date
            </label>

            <input
                type="date"
                name="endDate"
                value={endDate}
                onChange={(e) => setEndDate(e.target.value)}
                className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-1 focus:ring-blue-300 focus:border-blue-500 transition-all"
                min={new Date().toISOString().split('T')[0]}
            />
        </div>


        {/* Submit Button */}
        <button
          type="submit"
          className="w-full bg-blue-600 text-white py-4 px-6 rounded-xl font-semibold text-lg hover:cursor-pointer hover:bg-blue-700 focus:ring-4 focus:ring-blue-200 disabled:opacity-50 disabled:cursor-not-allowed transition-all"
        >
         Create New Task
        </button>
      </form>
    </div>

    </div>
  )
}

export default NewTaskPage