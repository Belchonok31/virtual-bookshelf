import { createSlice } from "@reduxjs/toolkit";

const reviewSlice = createSlice({
    name: 'review',
    initialState: {
        loading: false,
        items: [],
        selectedItem: null,
        error: null
    },
    reducers: {
        fetchReviewsLoading: (state) => {
            state.loading = true
            state.error = null
        },
        fetchReviewsAllSuccess: (state, action) => {
            state.loading = false
            state.items = action.payload
        },
        fetchReviewsMeSuccess: (state, action) => {
            state.loading = false
            state.items = action.payload
        },
        fetchReviewsOneSuccess: (state, action) => {
            state.loading = false
            state.selectedItem = action.payload
        },
        createReviewsSuccess: (state, action) => {
            state.loading = false
            state.items = [action.payload, ...state.items]
        },
        updateReviewsSuccess: (state, action) => {
            state.loading = false
            state.items = state.items
                .map(item => item.id === action.payload.id ? action.payload : item)
        },
        removeReviewsSuccess: (state, action) => {
            state.loading = false
            state.items = state.items.filter(item => item.id !== action.payload)
        },
        fetchReviewsError: (state, action) => {
            state.loading = false
            state.error = action.payload
        }
    }
})

export const {
    fetchReviewsLoading,
    fetchReviewsAllSuccess,
    fetchReviewsMeSuccess,
    fetchReviewsOneSuccess,
    createReviewsSuccess,
    updateReviewsSuccess,
    removeReviewsSuccess,
    fetchReviewsError
} = reviewSlice.actions

export default reviewSlice.reducer