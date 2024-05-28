import React from 'react';
import styles from './Shelf.module.css'

const Shelf = ({ shelf }) => {

    return (
        <div className={styles.main}>
            <h2>Shelf</h2>
            <div key={shelf.id} className={styles.shelf}>
                <p className={styles.shelfName}>{shelf.name}</p>
                <p className={styles.shelfDescription}>{shelf.description}</p>
            </div>
        </div>
    )
}

export default Shelf;
