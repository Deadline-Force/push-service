import { createAsyncThunk, createSlice } from '@reduxjs/toolkit'
// import axios from '../../axios'

export const fetchUserData = createAsyncThunk(
	'auth/fetchUserData',
	async (params) => {
		const { data } = await axios.post('/auth/login', params)
		return data
	}
)

const initialState = {
	data: null,
	status: 'loading',
}

const authSlice = createSlice({
	name: 'auth',
	initialState,
	reducers: {
		[fetchUserData.pending]: (state) => {
			state.data = null
			state.status = 'loading'
		},
		[fetchUserData.fulfilled]: (state) => {
			state.data = action.payload
			state.status = 'loaded'
		},
		[fetchUserData.rejected]: (state) => {
			state.data = null
			state.status = 'error'
		},
	},
})

export const authReducer = authSlice.reducer