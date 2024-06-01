import React from 'react';
import { Link } from 'react-router-dom';
import styles from './BookOriginal.module.css';

const BookOriginal = ({ bookOriginal, onEdit, onDelete, onAdd }) => {

    return (
        <div className={styles.bookOriginalContainer}>
            <img src="https://clipart-library.com/images/riLnxRei8.jpg" alt={bookOriginal.name} className={styles.bookOriginalImage} />
            <div className={styles.bookOriginalDetails}>
                <p className={styles.bookOriginalName}>{bookOriginal.name}</p>
                <p className={styles.bookOriginalAuthors}>{bookOriginal.authors}</p>
                <Link to={`${bookOriginal.id}`} className={styles.bookOriginalUrl}>Подробнее...</Link>
            </div>
            <div className={styles.actions}>
                <button className={styles.addedButton} onClick={() => onAdd(bookOriginal)}>Добавить</button>
                <button className={styles.actionButton} onClick={() => onEdit(bookOriginal)}>Редактировать</button>
                <button className={styles.deleteButton} onClick={() => onDelete(bookOriginal.id)}>Удалить</button>
            </div>
        </div>
    );
}

export default BookOriginal;
