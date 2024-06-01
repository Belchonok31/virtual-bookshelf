import axiosInstance from '../../app/axiosInstanse';
import {
    fetchStatisticsLoading,
    fetchStatisticsAllSuccess,
    fetchStatisticsOneSuccess,
    fetchStatisticsError
} from "./statisticsSlice"

export const getStatisticsAll = () => async (dispatch) => {
    dispatch(fetchStatisticsLoading())
    try {
        const { data } = await axiosInstance.get('/statistics');
        dispatch(fetchStatisticsAllSuccess(data))
    } catch (error) {
        dispatch(fetchStatisticsError(error.message))
    }
}

export const getStatistics= (bookOriginalId) => async (dispatch) => {
    dispatch(fetchStatisticsLoading())
    try {
        const { data } = await axiosInstance.get(`bookOriginal/${bookOriginalId}/statistics`);
        dispatch(fetchStatisticsOneSuccess(data))
    } catch (error) {
        dispatch(fetchStatisticsError(error.message))
    }
}

export const getStatisticsyId = (id) => async (dispatch) => {
    dispatch(fetchStatisticsLoading())
    try {
        const { data } = await axiosInstance.get(`/statistics/${id}`);
        dispatch(fetchStatisticsOneSuccess(data))
    } catch (error) {
        dispatch(fetchStatisticsError(error.message))
    }
}