import axiosInstance from '../../app/axiosInstanse';
import { fetchUserLoading,
    fetchUserAllSuccess,
    fetchUserFromContextSuccess,
    fetchUserOneSuccess,
    createUserSuccess,
    updateUserSuccess,
    removeUserSuccess,
    fetchUserError } from "./userSlice"

export const createUser = (UserData) => async (dispatch) => {
    dispatch(fetchUserLoading())
    try {
        const { data } = await axiosInstance.post('/user', UserData);
        dispatch(createUserSuccess(data))
    } catch (error) {
        dispatch(fetchUserError(error.message))
    }
}

export const updateUser = ( id, UserData ) => async (dispatch) => {
    dispatch(fetchUserLoading())
    try {
        const { data } = await axiosInstance.put(`/user/${id}`, UserData);
        dispatch(updateUserSuccess(data))
    } catch (error) {
        dispatch(fetchUserError(error.message))
    }
}

export const getUsersAll = () => async (dispatch) => {
    dispatch(fetchUserLoading())
    try {
        const { data } =  await axiosInstance.get('/user');
        dispatch(fetchUserAllSuccess(data))
    } catch (error) {
        dispatch(fetchUserError(error.message))
    }
}

export const getUserFromContext = () => async (dispatch) => {
    dispatch(fetchUserLoading())
    try {
        const { data } =  await axiosInstance.get('/user/context');
        dispatch(fetchUserFromContextSuccess(data))
    } catch (error) {
        dispatch(fetchUserError(error.message))
    }
}

export const getUserById = (id) => async (dispatch) => {
    dispatch(fetchUserLoading())
    try {
        const { data } = await axiosInstance.get(`/user/${id}`);
        dispatch(fetchUserOneSuccess(data))
    } catch (error) {
        dispatch(fetchUserError(error.message))
    }
}

export const removeUser = (id) => async (dispatch) => {
    dispatch(fetchUserLoading())
    try {
        if (await axiosInstance.delete(`/user/${id}`)) {
            dispatch(removeUserSuccess(id))
        }
    } catch (error) {
        dispatch(fetchUserError(error.message))
    }
}