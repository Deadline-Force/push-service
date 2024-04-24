import React from 'react'
import styles from './Admin.module.scss'

const Admin = () => {
	
	return (
		
		<section>
			<div className={styles.block}>
				<input type='checkbox' />
				<input type='text' placeholder='Write text' />
				<div className={styles.info}>
					<span>username</span>
					<span>role</span>
				</div>
				<div>Date</div>
			</div>
			<button>Отправить выделенным</button>
			<button>Отправить всем</button>
		</section>
	)
}

export default Admin
