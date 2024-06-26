import { createSlice } from "@reduxjs/toolkit";

const bookSlice = createSlice({
    name: 'book',
    initialState: {
        loading: false,
        items: [],
        selectedItem: null,
        error: null
    },
    reducers: {
        fetchBookLoading: (state) => {
            state.loading = true
            state.error = null
        },
        fetchBookAllSuccess: (state, action) => {
            state.loading = false
            state.items = action.payload
        },
        fetchBookMeSuccess: (state, action) => {
            state.loading = false
            state.items = action.payload
        },
        fetchBookOneSuccess: (state, action) => {
            state.loading = false
            state.selectedItem = action.payload
        },
        createBookSuccess: (state, action) => {
            state.loading = false
            state.items = [action.payload, ...state.items]
        },
        updateBookSuccess: (state, action) => {
            state.loading = false
            state.items = state.items
                .map(item => item.id === action.payload.id ? action.payload : item)
        },
        removeBookSuccess: (state, action) => {
            state.loading = false
            state.items = state.items.filter(item => item.id !== action.payload)
        },
        fetchBookError: (state, action) => {
            state.loading = false
            state.error = action.payload
        }
    }
})

export const {
    fetchBookLoading,
    fetchBookAllSuccess,
    fetchBookMeSuccess,
    fetchBookOneSuccess,
    createBookSuccess,
    updateBookSuccess,
    removeBookSuccess,
    fetchBookError
} = bookSlice.actions

export default bookSlice.reducer