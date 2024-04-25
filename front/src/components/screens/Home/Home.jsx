import React from 'react'
import { useSelector } from 'react-redux'
import { Navigate } from 'react-router-dom'
import { selectIsAuth } from '../../../redux/slices/auth'

const Home = () => {
	// const isAuth = useSelector(selectIsAuth)
	// if (!isAuth) {
	// 	// return <Navigate to='/login' />
	// }
	return <div>Home</div>
}

export default Home
