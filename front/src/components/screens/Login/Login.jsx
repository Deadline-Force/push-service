import React, { useEffect } from 'react'
import { useForm } from 'react-hook-form'
import { useDispatch, useSelector } from 'react-redux'
import { fetchAuth, selectIsAuth } from '../../../redux/slices/auth'
import styles from './Login.module.scss'
import { Link, Navigate } from 'react-router-dom'

const Login = () => {
	const isAuth = useSelector(selectIsAuth)
	const {
		register,
		handleSubmit,
		setError,
		formState: { errors, isValid },
	} = useForm({
		defaultValues: {
			login: 'employeetest1@gmail.com',
			password: '123',
		},
		mode: 'onChange',
	})
	const dispatch = useDispatch()



		const onSubmit = async(values) => {
			const data = await dispatch(fetchAuth(values))
			if(!data.payload){
				return alert('Не удалось авторизоваться')
			}
			if('token' in data.payload) {
				window.localStorage.setItem('token', data.payload.token)
				console.log(data)
				
			}
		}

	
	console.log('isAuth',isAuth)

	if(isAuth) {
		return <Navigate to='/'/>
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
						{...register('password', { required: 'Укажите пароль' })}
						className={styles.input}
					/>
					<button type='submit'>Войти</button>
				</form>
			</div>
		</section>
	)
}

export default Login
