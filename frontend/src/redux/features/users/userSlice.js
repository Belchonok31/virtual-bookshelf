import { createSlice } from "@reduxjs/toolkit";

const userSlice = createSlice({
    name: 'user',
    initialState: {
        loading: false,
        items: [],
        selectedItem: null,
        error: null
    },
    reducers: {
        fetchUserLoading: (state) => {
            state.loading = true
            state.error = null
        },
        fetchUserAllSuccess: (state, action) => {
            state.loading = false
            state.items = action.payload
        },
        fetchUserFromContextSuccess: (state, action) => {
          state.loading = false
          state.selectedItem = action.payload
      },
        fetchUserOneSuccess: (state, action) => {
            state.loading = false
            state.selectedItem = action.payload
        },
        createUserSuccess: (state, action) => {
            state.loading = false
            state.items = [action.payload, ...state.items]
        },
        updateUserSuccess: (state, action) => {
            state.loading = false
            state.selectedItem = action.payload
        },
        removeUserSuccess: (state, action) => {
            state.loading = false
            state.items = state.items.filter(item => item.id !== action.payload)
        },
        fetchUserError: (state, action) => {
            state.loading = false
            state.error = action.payload
        }
    }
})

export const {
    fetchUserLoading,
    fetchUserAllSuccess,
    fetchUserFromContextSuccess,
    fetchUserOneSuccess,
    createUserSuccess,
    updateUserSuccess,
    removeUserSuccess,
    fetchUserError
} = userSlice.actions

export default userSlice.reducer