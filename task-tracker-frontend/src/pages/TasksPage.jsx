import React, { useEffect, useState } from 'react'
import { PlusIcon,ArrowUturnLeftIcon, ClockIcon, CalendarIcon,ExclamationTriangleIcon,CircleStackIcon} from '@heroicons/react/24/outline'
import { isToday, format } from 'date-fns';
import { tr } from 'date-fns/locale'; 
import { useNavigate, useParams } from 'react-router-dom'
import { taskApi } from '../services/authApi';
import Header from '../components/Header';

const TasksPage = () => {
    const navigate = useNavigate();
    const {taskListId} = useParams();
    const [tasks,setTasks] = useState([])

    useEffect(() =>{
        const fetchTask = async () =>{
            const response = await taskApi.getAllTask(taskListId);
            console.log(response)
            setTasks(response.data)
        }
        fetchTask();
    },[taskListId])

    const getPriorityColor = (priority) => {
        switch(priority) {
            case 'HIGH': return 'text-red-500';
            case 'MEDIUM': return 'text-amber-500';
            default: return 'text-gray-400';
        }
    };

    const getStatusColor = (status) => 
        status === 'OPEN' ? 'text-emerald-500' : 'text-gray-400';



    const deleteTaskFromTaskList = async (id) =>{
        try {
            await taskApi.deleteTask(taskListId,id);
            setTasks(prev => prev.filter(task => task.id !== id));
        } catch (error) {
             console.log(error)
        }
    }


  return (
    <div className='min-h-screen bg-slate-200 '>
        <Header/>

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
                <button onClick={() => navigate(`/tasklists/${taskListId}/tasks/new-task`)} className="bg-slate-950 hover:cursor-pointer hover:bg-slate-900 px-8 py-4 w-full max-w-sm flex items-center justify-center text-amber-50 rounded-2xl font-bold transition-all duration-200 hover:scale-[1.02] shadow-xl group">
                <PlusIcon className="h-6 w-6 mr-3 group-hover:text-red-400 transition-colors" />
                CREATE NEW TASK
                </button>
            </div>
        </div>


        {tasks.map((task) => (
            <div key={task.id} className="group mt-1 bg-white rounded-xl p-5 shadow-md hover:shadow-xl transition-all duration-300 border hover:border-indigo-200">
            {/* Task checkbox */}
                <div className="flex items-center justify-center mb-1">
                    <input 
                    type="checkbox" 
                    className="mt-1 h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded mr-3"
                    />
                    <h4 className="text-lg font-semibold text-gray-900 line-clamp-2 flex-1">
                        {task.title}
                    </h4>
                </div>

                <div className='p-3'>
                    <p>
                        {task.description}
                    </p>
                </div>
                
                {/* Task alt info */}
                <div className="flex items-center justify-between text-xs text-gray-500 mb-3">
                {/* Priority */}
                    <div className="flex items-center">
                        <ExclamationTriangleIcon className={`h-3 w-3 ${getPriorityColor(task.priority)}`} />
                        <span className='mr-2'>{task.priority}</span>
                        
                        <CircleStackIcon className={`h-3 w-3 ${getStatusColor(task.status)}`} />
                        <span>{task.status}</span>
                    
                    </div>
                    
                    {/* Status */}
                

                    <div className="flex items-center">
                    <span className="flex items-center">
                        <CalendarIcon className="h-3 w-3 mr-1" />
                        {isToday(new Date(task.endDate)) ? 'Today' : format(new Date(task.endDate), 'dd.MM.yyyy', { locale: tr })}
                    </span>
                    </div>
                </div>
                
                
                <div className="flex items-center space-x-3">
                    <div className="bg-red-100 px-2 py-1 rounded-sm  flex items-center">
                        <button onClick={() => deleteTaskFromTaskList(task.id)} className="text-xs hover:cursor-pointer font-medium text-red-800">Delete</button>
                    </div>
                    <div className="bg-blue-100 px-2 py-1 rounded-sm  flex items-center">
                        <button onClick={() => navigate(`/tasklists/${taskListId}/tasks/${task.id}`)} className="text-xs hover:cursor-pointer font-medium text-blue-800">Update</button>
                    </div>
                </div>
            </div>


        ))}


        

    </div>
  )
}

export default TasksPage