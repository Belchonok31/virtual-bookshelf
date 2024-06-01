import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import styles from './ReviewUser.module.css';
import { format } from 'date-fns';
import {getBookOriginalsAll} from '../../redux/features/bookOriginals/bookOriginalActions'
import {getUsersAll} from '../../redux/features/users/userAction'

const ReviewUser = ({ review, onDelete }) => {

    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(getBookOriginalsAll());
        dispatch(getUsersAll());
    }, [dispatch]);


    const formatDate = (date) => {
        return format(new Date(date), 'HH:mm dd-MM-yyyy');
    };

    const books = useSelector((state) => state.bookOriginal.items);

    const users = useSelector((state) => state.user.items);


    return (
        <li className={styles.reviewItem}>
            <div className={styles.reviewHeader}>
                <span className={styles.reviewAuthor}>{users.find(user => user.id === review.user_id)?.email}</span>
                <span className={styles.reviewDate}>{formatDate(review.dateOfWriting)}</span>
            </div>
            <div className={styles.reviewRating}>{'★'.repeat(review.rating)}</div>
            <div className={styles.book}>
                <img src="https://clipart-library.com/images/riLnxRei8.jpg" alt="" />
                <p className={styles.bookName}>{books.find(book => book.id === review.book_original_id)?.name ?? 0} ({books.find(book => book.id === review.book_original_id)?.authors ?? 0})</p>
            </div>
            <p className={styles.reviewText}>{review.text}</p>
            <div className={styles.delete}>
                <button className={styles.deleteButton} onClick={() => onDelete(review.id)}>Удалить</button>
            </div>
        </li>
    );
};

export default ReviewUser;
