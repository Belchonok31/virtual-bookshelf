import axiosInstance from '../../app/axiosInstanse';
import { fetchBookOriginalLoading,
    fetchBookOriginalAllSuccess,
    fetchBookOriginalOneSuccess,
    createBookOriginalSuccess,
    updateBookOriginalSuccess,
    removeBookOriginalSuccess,
    fetchBookOriginalError } from './bookOriginalSlice'

export const createBookOriginal = (bookOriginalData) => async (dispatch) => {
    dispatch(fetchBookOriginalLoading())
    try {
        const { data } = await axiosInstance.post('/bookOriginal', bookOriginalData);
        dispatch(createBookOriginalSuccess(data))
    } catch (error) {
        dispatch(fetchBookOriginalError(error.message))
    }
}

export const updateBookOriginal = ( id, bookOriginalData ) => async (dispatch) => {
    dispatch(fetchBookOriginalLoading())
    try {
        const { data } = await axiosInstance.put(`/bookOriginal/${id}`, bookOriginalData);
        dispatch(updateBookOriginalSuccess(data))
    } catch (error) {
        dispatch(fetchBookOriginalError(error.message))
    }
}

export const getBookOriginalsAll = () => async (dispatch) => {
    dispatch(fetchBookOriginalLoading())
    try {
        const { data } =  await axiosInstance.get('/bookOriginal');
        dispatch(fetchBookOriginalAllSuccess(data))
    } catch (error) {
        dispatch(fetchBookOriginalError(error.message))
    }
}

export const getBookOriginalById = (id) => async (dispatch) => {
    dispatch(fetchBookOriginalLoading())
    try {
        const { data } = await axiosInstance.get(`/bookOriginal/${id}`);
        dispatch(fetchBookOriginalOneSuccess(data))
    } catch (error) {
        dispatch(fetchBookOriginalError(error.message))
    }
}

export const removeBookOriginal = (id) => async (dispatch) => {
    dispatch(fetchBookOriginalLoading())
    try {
        if (await axiosInstance.delete(`/bookOriginal/${id}`)) {
            dispatch(removeBookOriginalSuccess(id))
        }
    } catch (error) {
        dispatch(fetchBookOriginalError(error.message))
    }
}