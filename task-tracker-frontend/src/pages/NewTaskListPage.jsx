import React, { useState } from 'react'
import { ArrowUturnLeftIcon } from '@heroicons/react/24/outline'
import { useNavigate } from 'react-router-dom'
import { taskListApi } from '../services/authApi';

const NewTaskListPage = () => {
    const navigate = useNavigate();
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");

    const handleTasklistSubmit = async(e) =>{
        e.preventDefault();
        try {
            const response = await taskListApi.createTaskList({title,description});
            console.log(response);
            navigate('/dashboard');
        } catch (error) {
            console.log(error)
        }
        
    }

  return (
    <div className='min-h-screen bg-slate-200'>
        <div className="max-w-2xl mx-auto p-8 ">
      {/* Back Button */}
      <button 
        onClick={() => navigate('/dashboard')}
        className="flex items-center text-gray-600 hover:text-gray-900 hover:cursor-pointer mb-12 transition-colors"
      >
        <ArrowUturnLeftIcon className="h-5 w-5 mr-2" />
        To Dashboard
      </button>

      {/* Form Card */}
      <form onSubmit={handleTasklistSubmit} className="bg-white rounded-2xl shadow-xl p-8 border">
        <h2 className="text-2xl font-bold text-gray-900 mb-8">New Tasklist</h2>
        
        {/* Title Input */}
        <div className="mb-8">
          <label className="block text-sm font-medium text-gray-700 mb-3">
            Tasklist Name
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
        <div className="mb-12">
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

        {/* Submit Button */}
        <button
          type="submit"
          className="w-full bg-blue-600 text-white py-4 px-6 rounded-xl font-semibold text-lg hover:cursor-pointer hover:bg-blue-700 focus:ring-4 focus:ring-blue-200 disabled:opacity-50 disabled:cursor-not-allowed transition-all"
        >
         Create New List
        </button>
      </form>
    </div>

    </div>
     
      
  )
}

export default NewTaskListPage