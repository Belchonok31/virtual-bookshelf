import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { useParams, useNavigate } from 'react-router-dom';
import { getShelfById } from '../../redux/features/shelfs/shelfActions'
import { getBooks } from '../../redux/features/books/bookAction'
import styles from './ShelfItemPage.module.css';
import Cookies from 'universal-cookie';
import Header from '../../components/header/Header';
import Book from '../../components/book/Book'

const ShelfItemPage = () => {

    const dispatch = useDispatch();
    const cookie = new Cookies();
    const { id } = new useParams();
    const navigate = useNavigate();

    useEffect(() => {
        const token = cookie.get('token');
        if (!token) {
            navigate("/signUp");
        }
        dispatch(getShelfById(id))
        dispatch(getBooks(id))
    }, [dispatch]);

    const shelf = useSelector((state) => state.shelf.selectedItem);

    const books = useSelector((state) => state.book.items);

    return (
        <div className={styles.shelfPage}>
            <Header />
            <div className={styles.shelfInfo}>
                <div className={styles.labelPlusDescription}>
                    <div className={styles.shelfLabel}>
                        <img src="https://i.pinimg.com/originals/3b/bd/f9/3bbdf999912fa2e0b9d439375d77806b.png" alt="User Avatar" className={styles.shelfAvatar} />
                        <div className={styles.item}><h2>{shelf?.name}</h2></div>
                    </div>
                    <div className={styles.shelfDescription}><strong>Описание:</strong> {shelf?.description}</div>
                </div>
                <div className={styles.bookList}>
                    {books.map((book) => (
                        <Book
                            key={book.id}
                            book={book}
                        />
                    ))}
                </div>
            </div>
        </div>
    )
}

export default ShelfItemPage;
