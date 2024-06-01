import { configureStore } from '@reduxjs/toolkit';
import authReducer from '../features/auth/authSlice'
import userReducer from '../features/users/userSlice';
import shelfReducer from '../features/shelfs/shelfSlice'
import bookOriginalReducer from '../features/bookOriginals/bookOriginalSlice'
import bookReducer from '../features/books/bookSlice'
import reviewReducer from '../features/reviews/reviewsSlice'
import statisticsReducer from '../features/statistics/statisticsSlice'

const store = configureStore({
  reducer: {
    auth: authReducer,
    user: userReducer,
    shelf: shelfReducer,
    bookOriginal: bookOriginalReducer,
    book: bookReducer,
    review: reviewReducer,
    statistics: statisticsReducer,
  },
});

export default store;