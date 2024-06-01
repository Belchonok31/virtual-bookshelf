import React, { useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { getBookOriginalById } from '../../redux/features/bookOriginals/bookOriginalActions';
import { getReviewsByBookId } from '../../redux/features/reviews/reviewsActions'
import { getUsersAll } from '../../redux/features/users/userAction'
import { getStatistics } from '../../redux/features/statistics/statisticsAction'
import styles from './BookOriginalItem.module.css';
import Cookies from 'universal-cookie';
import Header from '../../components/header/Header'
import Review from '../../components/review/Review';


const BookOriginalItem = () => {

    const dispatch = useDispatch();
    const cookie = new Cookies();
    const { id } = new useParams();

    useEffect(() => {
        const token = cookie.get('token');
        if (!token) {
            window.location.href = '/signIn';
        }
        dispatch(getBookOriginalById(id))
        dispatch(getReviewsByBookId(id))
        dispatch(getStatistics(id))
        dispatch(getUsersAll())
    }, [dispatch]);

    const book = useSelector((state) => state.bookOriginal.selectedItem);

    const reviews = useSelector((state) => state.review.items);

    const users = useSelector((state) => state.user.items);

    const statistics = useSelector((state) => state.statistics.selectedItem);

    return (
        <div className={styles.main}>
            <Header />
            <div className={styles.bookPage}>
                {book && (
                    <>
                        <div className={styles.infoPlusDescription}>
                            <div className={styles.bookInfo}>
                                <img src="https://clipart-library.com/images/riLnxRei8.jpg" alt="Book Cover" className={styles.bookImage} />
                                <div className={styles.bookDetails}>
                                    <h1 className={styles.bookName}>{book.name}</h1>
                                    <p><strong>Автор:</strong> {book.authors}</p>
                                    <p><strong>Жанр:</strong> {book.genre}</p>
                                    <p><strong>Дата издания:</strong> {book.dateIssue}</p>
                                    <div className={styles.rating}>
                                        <div className={styles.item}>
                                            <img src="https://www.clipartmax.com/png/full/73-730227_gold-star-star-clipart.png" alt="" />
                                            <span>{statistics.rating ?? 0}</span>
                                        </div>
                                        <div className={styles.item}>
                                            <img src="https://cdn2.iconfinder.com/data/icons/circle-icons-1/64/magnifyingglass-1024.png" alt="" />
                                            <span>{statistics.numberOfReviews ?? 0}</span>
                                        </div>
                                        <div className={styles.item}>
                                            <img src="https://www.pngmart.com/files/21/Green-Add-Button-PNG-Isolated-HD.png" alt="" />
                                            <span>{statistics.additions ?? 0}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <p className={styles.bookDescription}>{book.description}</p>
                        </div>
                        <div className={styles.bookReviews}>
                            <h2>Отзывы:</h2>
                            {reviews && (
                                <>
                                    <ul>

                                        {reviews.map((review, index) => (
                                            <Review
                                                key={index}
                                                author={users.find((user) => user.id === review.user_id).email}
                                                dateOfWriting={review.dateOfWriting}
                                                rating={review.rating}
                                                text={review.text}
                                            />
                                        ))}
                                    </ul>
                                </>
                            )}
                        </div>
                    </>
                )}
            </div>
        </div>
    );
};

export default BookOriginalItem;
