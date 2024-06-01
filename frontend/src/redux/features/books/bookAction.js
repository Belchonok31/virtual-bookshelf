import axiosInstance from '../../app/axiosInstanse';
import { fetchBookLoading,
    fetchBookAllSuccess,
    fetchBookMeSuccess,
    fetchBookOneSuccess,
    createBookSuccess,
    updateBookSuccess,
    removeBookSuccess,
    fetchBookError } from "./bookSlice"

export const createBook = (bookOriginalId, BookData, shelfId) => async (dispatch) => {
    dispatch(fetchBookLoading())
    try {
        const { data } = await axiosInstance.post(`/bookOriginal/${bookOriginalId}`, BookData,
        {
            params: {
                shelfId: shelfId
            }
        }
        );
        dispatch(createBookSuccess(data))
    } catch (error) {
        dispatch(fetchBookError(error.message))
    }
}

export const updateBook = ( id, BookData ) => async (dispatch) => {
    dispatch(fetchBookLoading())
    try {
        const { data } = await axiosInstance.put(`/book/${id}`, BookData);
        dispatch(updateBookSuccess(data))
    } catch (error) {
        dispatch(fetchBookError(error.message))
    }
}

export const getBooksAll = () => async (dispatch) => {
    dispatch(fetchBookLoading())
    try {
        const { data } =  await axiosInstance.get('/book');
        dispatch(fetchBookAllSuccess(data))
    } catch (error) {
        dispatch(fetchBookError(error.message))
    }
}

export const getBooks = (shelfId) => async (dispatch) => {
    dispatch(fetchBookLoading())
    try {
        const { data } = await axiosInstance.get(`shelf/${shelfId}/book`);
        dispatch(fetchBookMeSuccess(data))
    } catch (error) {
        dispatch(fetchBookError(error.message))
    }
}

export const getBookById = (id) => async (dispatch) => {
    dispatch(fetchBookLoading())
    try {
        const { data } = await axiosInstance.get(`/book/${id}`);
        dispatch(fetchBookOneSuccess(data))
    } catch (error) {
        dispatch(fetchBookError(error.message))
    }
}

export const removeBook = (id) => async (dispatch) => {
    dispatch(fetchBookLoading())
    try {
        if (await axiosInstance.delete(`/Book/${id}`)) {
            dispatch(removeBookSuccess(id))
        }
    } catch (error) {
        dispatch(fetchBookError(error.message))
    }
}