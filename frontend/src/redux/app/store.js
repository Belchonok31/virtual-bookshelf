import { configureStore } from '@reduxjs/toolkit';
import authReducer from '../features/auth/authSlice'
import userReducer from '../features/users/userSlice';
import shelfReducer from '../features/shelfs/shelfSlice'

const store = configureStore({
  reducer: {
    auth: authReducer,
    user: userReducer,
    shelf: shelfReducer,
  },
});

export default store;