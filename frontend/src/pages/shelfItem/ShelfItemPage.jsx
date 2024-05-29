import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { getShelfById } from '../../redux/features/shelfs/shelfThunks';
import Book from '../../components/book/Book';
import styles from './ShelfItemPage.module.css';

const ShelfItemPage = ({ match }) => {
    const { id } = match.params;
    const dispatch = useDispatch();
    const shelf = useSelector((state) => state.shelf.selectedItem);
    const shelfStatus = useSelector((state) => state.shelf.status);

    useEffect(() => {
        if (shelfStatus === 'idle') {
            dispatch(getShelfById(id));
        }
    }, [shelfStatus, dispatch, id]);

    return (
        <div className={styles.container}>
            {shelf && (
                <>
                    <h2 className={styles.shelfName}>{shelf.name}</h2>
                    <div className={styles.bookList}>
                        {shelf.books.map((book) => (
                            <Book key={book.id} book={book} />
                        ))}
                    </div>
                </>
            )}
        </div>
    );
}

export default ShelfItemPage;
