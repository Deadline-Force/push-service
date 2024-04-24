import React from 'react'
import { Link } from 'react-router-dom'
import styles from './Register.module.scss'

const Register = () => {
	return (
		<section className={styles.wrapper}>
			<div className={styles.container}>
				<h1>Sign Up</h1>
				<p>Already have an account?<Link to='/login'>Log in</Link></p>
				
				<form action=''>
					<label htmlFor=''>Email</label>
					<input type='email' placeholder='user' className={styles.input} />
					<label htmlFor=''>Full name</label>
					<input
						type='text'
						placeholder='Ivan Ivanov'
						className={styles.input}
					/>
					<label htmlFor=''>Password</label>
					<input type='text' placeholder='*********' className={styles.input} />
					<button>Зарегистрироваться</button>
				</form>
			</div>
		</section>
	)
}

export default Register
