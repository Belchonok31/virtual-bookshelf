import { createAsyncThunk } from '@reduxjs/toolkit';
import axiosInstance from '../../app/axiosInstanse';


export const getShelfsAll = createAsyncThunk('shelf/fetchAll', async () => {
    const response = await axiosInstance.get('/shelf/all');
    return response.data;
});

export const getShelfs = createAsyncThunk('shelf/fetchAllMe', async () => {
    const response = await axiosInstance.get('/shelf');
    return response.data;
});

export const getShelfById = createAsyncThunk('shelf/fetchOne', async (id) => {
    const response = await axiosInstance.get(`/shelf/${id}`);
    return response.data;
  });
  

export const addShelf = createAsyncThunk('shelf/add', async (shelfData) => {
    const response = await axiosInstance.post('/shelf', shelfData);
    return response.data;
});

export const updateShelf = createAsyncThunk('shelf/update', async ({ id, shelfData }) => {
    const response = await axiosInstance.put(`/shelf/${id}`, shelfData);
    return response.data;
});

export const deleteShelf = createAsyncThunk('shelf/delete', async (id) => {
    const response = await axiosInstance.delete(`/shelf/${id}`);
    return response.data;
});