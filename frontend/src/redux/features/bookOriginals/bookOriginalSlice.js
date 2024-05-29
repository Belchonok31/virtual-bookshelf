import { createSlice } from "@reduxjs/toolkit";

const bookOriginalSlice = createSlice({
    name: 'bookOriginal',
    initialState: {
        loading: false,
        items: [],
        selectedItem: null,
        error: null
    },
    reducers: {
        fetchBookOriginalLoading: (state) => {
            state.loading = true
            state.error = null
        },
        fetchBookOriginalAllSuccess: (state, action) => {
            state.loading = false
            state.items = action.payload
        },
        fetchBookOriginalOneSuccess: (state, action) => {
            state.loading = false
            state.selectedItem = action.payload
        },
        createBookOriginalSuccess: (state, action) => {
            state.loading = false
            state.items = [action.payload, ...state.items]
        },
        updateBookOriginalSuccess: (state, action) => {
            state.loading = false
            state.items = state.items
                .map(item => item.id === action.payload.id ? action.payload : item)
        },
        removeBookOriginalSuccess: (state, action) => {
            state.loading = false
            state.items = state.items.filter(item => item.id !== action.payload)
        },
        fetchBookOriginalError: (state, action) => {
            state.loading = false
            state.error = action.payload
        }
    }
})

export const {
    fetchBookOriginalLoading,
    fetchBookOriginalAllSuccess,
    fetchBookOriginalOneSuccess,
    createBookOriginalSuccess,
    updateBookOriginalSuccess,
    removeBookOriginalSuccess,
    fetchBookOriginalError
} = bookOriginalSlice.actions

export default bookOriginalSlice.reducer