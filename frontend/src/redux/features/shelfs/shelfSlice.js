import { createSlice } from '@reduxjs/toolkit';
import { getShelfsAll, getShelfs, getShelfById, addShelf, updateShelf, deleteShelf } from './shelfThunks';

const booksSlice = createSlice({
  name: 'shelf',
  initialState: {
    items: [],
    selectedItem: null,
    status: 'idle',
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(getShelfsAll.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(getShelfsAll.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.items = action.payload;
      })
      .addCase(getShelfsAll.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      })
      .addCase(getShelfs.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(getShelfs.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.items = action.payload;
      })
      .addCase(getShelfs.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      })
      .addCase(getShelfById.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(getShelfById.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.selectedItem = action.payload;
      })
      .addCase(getShelfById.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      })
      .addCase(addShelf.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(addShelf.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.items.push(action.payload);
      })
      .addCase(addShelf.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      })
      .addCase(updateShelf.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(updateShelf.fulfilled, (state, action) => {
        state.status = 'succeeded';
        const index = state.items.findIndex(shelf => shelf.id === action.payload.id);
        if (index !== -1) {
          state.items[index] = action.payload;
        }
      })
      .addCase(updateShelf.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      })
      .addCase(deleteShelf.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(deleteShelf.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.items = state.items.filter(shelf => shelf.id !== action.meta.arg);
      })
      .addCase(deleteShelf.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      });
  },
});  

export default booksSlice.reducer;