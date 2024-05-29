import { configureStore } from '@reduxjs/toolkit';
import authReducer from '../features/auth/authSlice'
import userReducer from '../features/users/userSlice';
import shelfReducer from '../features/shelfs/shelfSlice'
import bookOriginalReducer from '../features/bookOriginals/bookOriginalSlice'

const store = configureStore({
  reducer: {
    auth: authReducer,
    user: userReducer,
    shelf: shelfReducer,
    bookOriginal: bookOriginalReducer,
  },
});

export default store;