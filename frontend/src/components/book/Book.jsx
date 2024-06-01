import React from 'react';
import { Link } from 'react-router-dom';
import styles from './Book.module.css';

const Book = ({ book, onEdit, onDelete }) => {
    return (
        <div className={styles.bookContainer}>
            <img src="https://content.hipolink.net/image/3746901a-8eed-4765-b6be-e6d63e5bfb4b.png" alt={book.name} className={styles.bookImage} />
            <div className={styles.bookDetails}>
                <p className={styles.bookName}>{book.name}</p>
                <p className={styles.bookAuthors}>{book.authors}</p>
                <Link to={`/bookOriginal/${book.bookOriginalId}`} className={styles.bookUrl}>Подробнее...</Link>
            </div>
            <div className={styles.actions}>
                <button className={styles.actionButton} onClick={() => onEdit(book)}>Редактировать</button>
                <button className={styles.deleteButton} onClick={() => onDelete(book.id)}>Удалить</button>
            </div>
        </div>
    );
}

export default Book;
