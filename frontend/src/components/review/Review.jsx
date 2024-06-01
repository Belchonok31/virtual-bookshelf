import React from 'react';
import styles from './Review.module.css';
import { format } from 'date-fns';

const Review = ({ author, dateOfWriting, rating, text }) => {
    const formatDate = (date) => {
        return format(new Date(date), 'HH:mm dd-MM-yyyy');
    };

    return (
        <li className={styles.reviewItem}>
            <div className={styles.reviewHeader}>
                <span className={styles.reviewAuthor}>{author}</span>
                <span className={styles.reviewDate}>{formatDate(dateOfWriting)}</span>
            </div>
            <div className={styles.reviewRating}>{'★'.repeat(rating)}</div>
            <p className={styles.reviewText}>{text}</p>
        </li>
    );
};

export default Review;
