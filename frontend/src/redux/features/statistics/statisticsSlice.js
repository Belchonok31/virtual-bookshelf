import { createSlice } from "@reduxjs/toolkit";

const statisticsSlice = createSlice({
    name: 'statistics',
    initialState: {
        loading: false,
        items: [],
        selectedItem: null,
        error: null
    },
    reducers: {
        fetchStatisticsLoading: (state) => {
            state.loading = true
            state.error = null
        },
        fetchStatisticsAllSuccess: (state, action) => {
            state.loading = false
            state.items = action.payload
        },
        fetchStatisticsOneSuccess: (state, action) => {
            state.loading = false
            state.selectedItem = action.payload
        },
        fetchStatisticsError: (state, action) => {
            state.loading = false
            state.error = action.payload
        }
    }
})

export const {
    fetchStatisticsLoading,
    fetchStatisticsAllSuccess,
    fetchStatisticsOneSuccess,
    fetchStatisticsError
} = statisticsSlice.actions

export default statisticsSlice.reducer