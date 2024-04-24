import React from 'react'
import { Route, Routes } from 'react-router-dom'
import './App.css'
import Header from './components/Header/Header'
import Admin from './components/screens/Admin/Admin'
import Employee from './components/screens/Emloyee/Employee'
import Home from './components/screens/Home/Home'
import Login from './components/screens/Login/Login'
import NotFound from './components/screens/NotFound/NotFound'
import Notification from './components/screens/Notification/Notification'
import Register from './components/screens/Register/Register'

const App = () => {
	return (
		<>
			<Header />

			<Routes>
				<Route path='*' element={<NotFound />} />

				{/* public routes */}
				<Route path='/login' element={<Login />} />
				<Route path='/register' element={<Register />} />

				{/* protected routes */}
				<Route path='/admin' element={<Admin />} />
				<Route path='/notification' element={<Notification />} />
				<Route path='/employee' element={<Employee />} />
				<Route path='/' element={<Home />} />
			</Routes>
		</>
	)
}

export default App
