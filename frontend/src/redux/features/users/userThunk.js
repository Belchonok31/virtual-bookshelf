import { createAsyncThunk } from '@reduxjs/toolkit';
import axiosInstance from '../../app/axiosInstanse';


export const getUsersAll = createAsyncThunk('user/fetchAllAdmin', async () => {
    const response = await axiosInstance.get('/user/all');
    return response.data;
});

export const getUsers = createAsyncThunk('user/fetchAll', async () => {
    const response = await axiosInstance.get('/user');
    return response.data;
});

export const getUserById = createAsyncThunk('user/fetchOne', async (id) => {
    const response = await axiosInstance.get(`/user/${id}`);
    return response.data;
  });
  

export const addUser = createAsyncThunk('user/add', async (userData) => {
    const response = await axiosInstance.post('/user', userData);
    return response.data;
});

export const updateUser = createAsyncThunk('user/update', async ({ id, userData }) => {
    const response = await axiosInstance.put(`/user/${id}`, userData);
    return response.data;
});

export const deleteUser = createAsyncThunk('user/delete', async (id) => {
    const response = await axiosInstance.delete(`/user/${id}`);
    return response.data;
});