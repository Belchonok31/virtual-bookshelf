import axiosInstance from '../../app/axiosInstanse';
import { fetchShelfLoading,
    fetchShelfAllSuccess,
    fetchShelfMeSuccess,
    fetchShelfOneSuccess,
    createShelfSuccess,
    updateShelfSuccess,
    removeShelfSuccess,
    fetchShelfError } from "./shelfSlice"

export const createShelf = (shelfData) => async (dispatch) => {
    dispatch(fetchShelfLoading())
    try {
        const { data } = await axiosInstance.post('/shelf', shelfData);
        dispatch(createShelfSuccess(data))
    } catch (error) {
        dispatch(fetchShelfError(error.message))
    }
}

export const updateShelf = ( id, shelfData ) => async (dispatch) => {
    dispatch(fetchShelfLoading())
    try {
        const { data } = await axiosInstance.put(`/shelf/${id}`, shelfData);
        dispatch(updateShelfSuccess(data))
    } catch (error) {
        dispatch(fetchShelfError(error.message))
    }
}

export const getShelfsAll = () => async (dispatch) => {
    dispatch(fetchShelfLoading())
    try {
        const { data } =  await axiosInstance.get('/shelf/all');
        dispatch(fetchShelfAllSuccess(data))
    } catch (error) {
        dispatch(fetchShelfError(error.message))
    }
}

export const getShelfs = () => async (dispatch) => {
    dispatch(fetchShelfLoading())
    try {
        const { data } = await axiosInstance.get('/shelf');
        dispatch(fetchShelfMeSuccess(data))
    } catch (error) {
        dispatch(fetchShelfError(error.message))
    }
}

export const getShelfById = (id) => async (dispatch) => {
    dispatch(fetchShelfLoading())
    try {
        const { data } = await axiosInstance.get(`/shelf/${id}`);
        dispatch(fetchShelfOneSuccess(data))
    } catch (error) {
        dispatch(fetchShelfError(error.message))
    }
}

export const removeShelf = (id) => async (dispatch) => {
    dispatch(fetchShelfLoading())
    try {
        if (await axiosInstance.delete(`/shelf/${id}`)) {
            dispatch(removeShelfSuccess(id))
        }
    } catch (error) {
        dispatch(fetchShelfError(error.message))
    }
}