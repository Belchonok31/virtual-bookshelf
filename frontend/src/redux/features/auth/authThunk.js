import { createAsyncThunk } from '@reduxjs/toolkit';
import axiosInstance from '../../app/axiosInstanse';
import Cookies from 'js-cookie';

export const registerUser = createAsyncThunk('auth/register', async (userData, { rejectWithValue }) => {
  try {
    const { firstName, middleName, username, email, password, role = 'user' } = userData;
    const response = await axiosInstance.post('/auth/signUp', {
      firstName,
      middleName,
      email,
      password,
    });
    const token = response.data.token;
    if (token) {
      Cookies.set('token', token);
    }
    return response.data;
  } catch (error) {
    return rejectWithValue(error.response.data);
  }
});

export const loginUser = createAsyncThunk('auth/login', async (userData, { rejectWithValue }) => {
  try {
    const { email, password } = userData;
    const response = await axiosInstance.post('auth/signIn', {
      email,
      password,
    });
    const token = response.data.token;
    if (token) {
      Cookies.set('token', token);
    }
    return response.data;
  } catch (error) {
    return rejectWithValue(error.response.data);
  }
});

