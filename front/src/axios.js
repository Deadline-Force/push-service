import axios from 'axios'

const instance = axios.create({
	baseURL: 'http://localhost',
})

axios.get('/users')


export default instance