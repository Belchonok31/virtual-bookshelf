import { createSlice } from "@reduxjs/toolkit";

const shelfSlice = createSlice({
    name: 'shelf',
    initialState: {
        loading: false,
        items: [],
        selectedItem: null,
        error: null
    },
    reducers: {
        fetchShelfLoading: (state) => {
            state.loading = true
            state.error = null
        },
        fetchShelfAllSuccess: (state, action) => {
            state.loading = false
            state.items = action.payload
        },
        fetchShelfMeSuccess: (state, action) => {
            state.loading = false
            state.items = action.payload
        },
        fetchShelfOneSuccess: (state, action) => {
            state.loading = false
            state.selectedItem = action.payload
        },
        createShelfSuccess: (state, action) => {
            state.loading = false
            state.items = [action.payload, ...state.items]
        },
        updateShelfSuccess: (state, action) => {
            state.loading = false
            state.items = state.items
                .map(item => item.id === action.payload.id ? action.payload : item)
        },
        removeShelfSuccess: (state, action) => {
            state.loading = false
            state.items = state.items.filter(item => item.id !== action.payload)
        },
        fetchShelfError: (state, action) => {
            state.loading = false
            state.error = action.payload
        }
    }
})

export const {
    fetchShelfLoading,
    fetchShelfAllSuccess,
    fetchShelfMeSuccess,
    fetchShelfOneSuccess,
    createShelfSuccess,
    updateShelfSuccess,
    removeShelfSuccess,
    fetchShelfError
} = shelfSlice.actions

export default shelfSlice.reducer