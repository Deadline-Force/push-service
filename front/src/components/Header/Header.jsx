import React, { useEffect } from 'react'
import { IoIosNotifications } from 'react-icons/io'
import { useDispatch, useSelector } from 'react-redux'
import { Link, Navigate } from 'react-router-dom'
import { selectIsAuth } from '../../redux/slices/auth'
import styles from './Header.module.scss'

const Header = () => {
	const dispatch = useDispatch()
	const logOut = () => {
		dispatch(logout())
		window.localStorage.removeItem('token')
	}
	const isAuth = useSelector(selectIsAuth)
	if (!isAuth) {
		return <Navigate to='/login' />
	}
	
	return (
		<header>
			{isAuth ? (
				<div className={styles.block}>
					<button onClick={logOut}>Log out</button>
					<Link to='/notification' className={styles.btn_notification}>
						<IoIosNotifications size={25} />
					</Link>
				</div>
			) : (
				<div className={styles.block}>
					<Link to='/register'>Create account</Link>
					<Link to='/login'>Log In</Link>
				</div>
			)}
		</header>
	)
}

export default Header
