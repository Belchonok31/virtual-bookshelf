import { createAsyncThunk } from '@reduxjs/toolkit';
import axiosInstance from '../../app/axiosInstanse';

export const getBooksAll = createAsyncThunk('book/fetchAllAdmin', async () => {
    const response = await axiosInstance.get('/book');
    return response.data;
});

export const getBooks = createAsyncThunk('book/fetchAllMe', async () => {
    const response = await axiosInstance.get(`/shelf/${shelfId}/book`);
    return response.data;
});

export const getBookById = createAsyncThunk('book/fetchOne', async (id) => {
    const response = await axiosInstance.get(`/book/${id}`);
    return response.data;
  });
  

export const addBook = createAsyncThunk('book/add', async ({ bookData, params }) => {
    const response = await axiosInstance.post('/book', bookData, { params });
    return response.data;
});

export const updateBook = createAsyncThunk('book/update', async ({ id, shelfData }) => {
    const response = await axiosInstance.put(`/book/${id}`, shelfData);
    return response.data;
});

export const deleteShelf = createAsyncThunk('shelf/delete', async (id) => {
    const response = await axiosInstance.delete(`/shelf/${id}`);
    return response.data;
});