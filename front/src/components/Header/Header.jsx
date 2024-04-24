import React from 'react'
import { IoIosNotifications } from 'react-icons/io'
import { Link } from 'react-router-dom'
import styles from './Header.module.scss'

const Header = () => {
	let isAuth = true
	return (
		<header>
			{isAuth ? (
				<div className={styles.block}>
					<button>Log out</button>
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
