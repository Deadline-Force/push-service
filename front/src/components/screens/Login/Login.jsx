import React from 'react'
import { useForm } from 'react-hook-form'
import { useDispatch } from 'react-redux'
import { fetchUserData } from '../../../redux/slices/auth'
import styles from './Login.module.scss'
import { Link } from 'react-router-dom'

const Login = () => {
	const {
		register,
		handleSubmit,
		setError,
		formState: { errors, isValid },
	} = useForm({
		defaultValues: {
			login: '',
			password: '',
		},
		mode: 'onChange',
	})
	const dispatch = useDispatch()
	const onSubmit = (values) => {
		dispatch(fetchUserData(values))
	}

	return (
		<section className={styles.wrapper}>
			<div className={styles.container}>
				<h1>Log in</h1>
				<p>
					Don't have an account yet?<Link to='/register'>Sign up</Link>
				</p>
				<form onSubmit={handleSubmit(onSubmit)}>
					<label htmlFor=''>Login/Email</label>
					<input
						type='login'
						placeholder='login'
						{...register('login', { required: 'Укажите почту' })}
						className={styles.input}
					/>
					<label htmlFor=''>Password</label>
					<input
						type='text'
						placeholder='Пароль'
						{...register('password', { required: 'Укажите почту' })}
						className={styles.input}
					/>
					<button type='submit'>Войти</button>
				</form>
			</div>
		</section>
	)
}

export default Login
