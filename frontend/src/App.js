import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Provider } from 'react-redux';
import store from './redux/app/store';
import './App.css';
import SignUpPage from './pages/signUp/SignUpPage';
import SignInPage from './pages/signIn/SignInPage';
import MainPage from './pages/main/MainPage'
import UserPage from './pages/user/UserPage';
import UserReviews from './pages/userReviews/UserReviewsPage';
import ShelfPage from './pages/shelfList/ShelfPage';
import ShelfItemPage from './pages/shelfItem/ShelfItemPage';
import BookOriginalPage from './pages/bookOriginalList/BookOriginalPage';
import BookOriginalItem from './pages/bookOriginalItem/BookOriginalItem';


function App() {
  return (
    <Provider store={store}>
        <BrowserRouter className="App">
          <Routes>
            <Route path="/signUp" element={<SignUpPage/>} />
            <Route path="/signIn" element={<SignInPage/>} />
            <Route path='/' element={<MainPage/>} />
            <Route path='/user' element={<UserPage/>} />
            <Route path='/user/reviews' element={<UserReviews/>} />
            <Route path='/shelf' element={<ShelfPage/>} />
            <Route path="/shelf/:id" element={<ShelfItemPage/>} />
            <Route path='/bookOriginal' element={<BookOriginalPage/>} />
            <Route path='/bookOriginal/:id' element={<BookOriginalItem/>} />
          </Routes>
      </BrowserRouter>
    </Provider>
  );
}

export default App;
