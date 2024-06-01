import axiosInstance from '../../app/axiosInstanse';
import { fetchReviewsLoading,
    fetchReviewsAllSuccess,
    fetchReviewsMeSuccess,
    fetchReviewsOneSuccess,
    createReviewsSuccess,
    updateReviewsSuccess,
    removeReviewsSuccess,
    fetchReviewsError } from "./reviewsSlice"

export const createReviews = ( bookOriginalId, ReviewsData) => async (dispatch) => {
    dispatch(fetchReviewsLoading())
    try {
        const { data } = await axiosInstance.post(`/bookOriginal/${bookOriginalId}/review`, ReviewsData);
        dispatch(createReviewsSuccess(data))
    } catch (error) {
        dispatch(fetchReviewsError(error.message))
    }
}

export const updateReviews = ( bookOriginalId, id, ReviewsData ) => async (dispatch) => {
    dispatch(fetchReviewsLoading())
    try {
        const { data } = await axiosInstance.put(`/bookOriginal/${bookOriginalId}/review/${id}`, ReviewsData);
        dispatch(updateReviewsSuccess(data))
    } catch (error) {
        dispatch(fetchReviewsError(error.message))
    }
}

export const getReviewssAll = () => async (dispatch) => {
    dispatch(fetchReviewsLoading())
    try {
        const { data } =  await axiosInstance.get('/review/all');
        dispatch(fetchReviewsAllSuccess(data))
    } catch (error) {
        dispatch(fetchReviewsError(error.message))
    }
}

export const getReviews = () => async (dispatch) => {
    dispatch(fetchReviewsLoading())
    try {
        const { data } = await axiosInstance.get('/review');
        dispatch(fetchReviewsMeSuccess(data))
    } catch (error) {
        dispatch(fetchReviewsError(error.message))
    }
}

export const getReviewsById = (id) => async (dispatch) => {
    dispatch(fetchReviewsLoading())
    try {
        const { data } = await axiosInstance.get(`/review/${id}`);
        dispatch(fetchReviewsOneSuccess(data))
    } catch (error) {
        dispatch(fetchReviewsError(error.message))
    }
}

export const getReviewsByBookId = (id) => async (dispatch) => {
    dispatch(fetchReviewsLoading())
    try {
        const { data } = await axiosInstance.get(`bookOriginal/${id}/review`);
        dispatch(fetchReviewsAllSuccess(data))
    } catch (error) {
        dispatch(fetchReviewsError(error.message))
    }
}

export const removeReviews = (id) => async (dispatch) => {
    dispatch(fetchReviewsLoading())
    try {
        if (await axiosInstance.delete(`/review/${id}`)) {
            dispatch(removeReviewsSuccess(id))
        }
    } catch (error) {
        dispatch(fetchReviewsError(error.message))
    }
}