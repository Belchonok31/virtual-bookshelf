import React from 'react';
import styles from './Shelf.module.css';

const Shelf = ({ shelf, onEdit, onDelete }) => {
    const shelfUrl = `/shelf/${shelf.id}`;

    return (
        <div className={styles.shelfContainer}>
            <img src="https://i.pinimg.com/originals/3b/bd/f9/3bbdf999912fa2e0b9d439375d77806b.png" alt={shelf.name} className={styles.shelfImage} />
            <div className={styles.shelfDetails}>
                <p className={styles.shelfName}>{shelf.name}</p>
                <a href={shelfUrl} className={styles.shelfUrl}>Содержание...</a>
            </div>
            <div className={styles.actions}>
                <button className={styles.actionButton} onClick={() => onEdit(shelf)}>Редактировать</button>
                <button className={styles.deleteButton} onClick={() => onDelete(shelf.id)}>Удалить</button>
            </div>
        </div>
    );
}

export default Shelf;
