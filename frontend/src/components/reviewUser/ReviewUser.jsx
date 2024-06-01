import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import styles from './ReviewUser.module.css';
import { format } from 'date-fns';
import {getBookOriginalsAll} from '../../redux/features/bookOriginals/bookOriginalActions'

const ReviewUser = ({ author, dateOfWriting, rating, bookId, text }) => {

    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(getBookOriginalsAll());
    }, [dispatch]);


    const formatDate = (date) => {
        return format(new Date(date), 'HH:mm dd-MM-yyyy');
    };

    const books = useSelector((state) => state.bookOriginal.items);


    return (
        <li className={styles.reviewItem}>
            <div className={styles.reviewHeader}>
                <span className={styles.reviewAuthor}>{author}</span>
                <span className={styles.reviewDate}>{formatDate(dateOfWriting)}</span>
            </div>
            <div className={styles.reviewRating}>{'★'.repeat(rating)}</div>
            <div className={styles.book}>
                <img src="https://clipart-library.com/images/riLnxRei8.jpg" alt="" />
                <p className={styles.bookName}>{books.find(book => book.id === bookId)?.name ?? 0} ({books.find(book => book.id === bookId)?.authors ?? 0})</p>
            </div>
            <p className={styles.reviewText}>{text}</p>
        </li>
    );
};

export default ReviewUser;
