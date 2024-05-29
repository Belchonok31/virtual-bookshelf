import React from 'react';
import styles from './Book.module.css';

const Book = ({ book }) => {
    const shelfUrl = `/book/${book.id}`;
    return (
        <div className={styles.bookContainer}>
            <img src="https://st2.depositphotos.com/1742172/9632/v/950/depositphotos_96321880-stock-illustration-cartoon-thick-book.jpg" alt={book.name} className={styles.bookImage} />
            <div className={styles.bookDetails}>
                <p className={styles.bookName}>{book.name}</p>
                <p className={styles.bookLabel}>{book.label}</p>
                <a href={book.bookOriginalId} className={styles.bookUrl}>Первоисточник</a>
            </div>
        </div>
    );
}

export default Book;
