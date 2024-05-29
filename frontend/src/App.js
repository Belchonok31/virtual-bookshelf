import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Provider } from 'react-redux';
import store from './redux/app/store';
import './App.css';
import SignUpPage from './pages/signUp/SignUpPage';
import SignInPage from './pages/signIn/SignInPage';
import MainPage from './pages/main/MainPage'
import ShelfPage from './pages/shelfList/ShelfPage';
import ShelfItemPage from './pages/shelfItem/ShelfItemPage';
import BookOriginalPage from './pages/bookOriginalList/BookOriginalPage';


function App() {
  return (
    <Provider store={store}>
        <BrowserRouter className="App">
          <Routes>
            <Route path="/signUp" element={<SignUpPage/>} />
            <Route path="/signIn" element={<SignInPage/>} />
            <Route path='/' element={<MainPage/>} />
            <Route path='/shelf' element={<ShelfPage/>} />
            <Route path="/shelf/:id" component={ShelfItemPage} />
            <Route path='/bookOriginal' element={<BookOriginalPage/>} />
          </Routes>
      </BrowserRouter>
    </Provider>
  );
}

export default App;
