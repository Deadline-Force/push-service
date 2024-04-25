import React from 'react'
import { useForm } from 'react-hook-form'
import { useDispatch } from 'react-redux'
import { Link } from 'react-router-dom'
import { fetchRegister } from '../../../redux/slices/auth'
import styles from './Register.module.scss'

const Register = () => {
	const {
		register,
		handleSubmit,
		setError,
		formState: { errors, isValid },
	} = useForm({
		defaultValues: {
			username: 'Vasya Guryanov',
			login: 'test@test.ru',
			password: 'test',
		},
		mode: 'onChange',
	})
	const dispatch = useDispatch()
	const onSubmit = (values) => {
		dispatch(fetchRegister(values))
		console.log(values)
	}
	
	return (
		<section className={styles.wrapper}>
			<div className={styles.container}>
				<h1>Sign Up</h1>
				<p>
					Already have an account?<Link to='/login'>Log in</Link>
				</p>

				<form onSubmit={handleSubmit(onSubmit)}>
					<label htmlFor=''>Email</label>
					<input
						type='email'
						placeholder='user'
						className={styles.input}
						{...register('login', { required: 'Укажите почту' })}
					/>
					<label htmlFor=''>Full name</label>
					<input
						type='text'
						placeholder='Ivan Ivanov'
						className={styles.input}
						{...register('username', { required: 'Укажите полное имя' })}
					/>
					<label htmlFor=''>Password</label>
					<input
						type='text'
						placeholder='*********'
						className={styles.input}
						{...register('password', { required: 'Укажите пароль' })}
					/>
					<button type='submit'>Зарегистрироваться</button>
				</form>
			</div>
		</section>
	)
}

export default Register
