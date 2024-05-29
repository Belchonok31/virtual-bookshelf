import { createSlice } from '@reduxjs/toolkit';
import { getBooks, ge } from './bookThunk';
  
  const booksSlice = createSlice({
    name: 'books',
    initialState: {
      books: [],
      comment: [],
      status: 'idle',
      error: null,
    },
    reducers: {},
    extraReducers: (builder) => {
      builder
        .addCase(fetchBooks.pending, (state) => {
          state.status = 'loading';
        })
        .addCase(fetchBooks.fulfilled, (state, action) => {
          state.status = 'succeeded';
          state.books = action.payload;
        })
        .addCase(fetchBooks.rejected, (state, action) => {
          state.status = 'failed';
          state.error = action.error.message;
        })
        .addCase(addBook.pending, (state) => {
          state.status = 'loading';
        })
        .addCase(addBook.fulfilled, (state, action) => {
          state.status = 'succeeded';
          state.books.push(action.payload);
        })
        .addCase(addBook.rejected, (state, action) => {
          state.status = 'failed';
          state.error = action.error.message;
        })
        .addCase(fetchComment.fulfilled, (state, action) => {
          state.status = 'succeeded';
          state.comment = action.payload;
        });
    },
  });  

export default booksSlice.reducer;