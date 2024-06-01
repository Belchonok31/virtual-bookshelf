import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { getUserFromContext } from '../../redux/features/users/userAction';
import { getReviews, removeReviews } from '../../redux/features/reviews/reviewsActions'
import styles from './UserReviewsPage.module.css';
import Cookies from 'universal-cookie';
import Header from '../../components/header/Header';
import ReviewUser from '../../components/reviewUser/ReviewUser';

const UserReviewsPage = () => {

    const dispatch = useDispatch();
    const cookie = new Cookies()

    useEffect(() => {
        const token = cookie.get('token');
        if (!token) {
            window.location.href = '/signIn';
        }
        dispatch(getUserFromContext());
        dispatch(getReviews());
    }, [dispatch]);


    const user = useSelector((state) => state.user.selectedItem);

    const reviews = useSelector((state) => state.review.items);

    const handleDelete = (id) => {
        dispatch(removeReviews(id));
    };


    return (
        <div>
            <Header />
            <div className={styles.userPage}>
                {user && (
                    <>
                        <div className={styles.userInfo}>
                            <img src="https://kitt-nn.ru/wp-content/uploads/2023/06/team-1.png" alt="User Avatar" className={styles.userAvatar} />
                            <div className={styles.item}><h2>Мои отзывы</h2></div>
                        </div>
                        <div className={styles.userReviews}>
                            {reviews &&(
                                <>
                                    <ul>
                                        {reviews.map((review, index) => (
                                            <ReviewUser
                                                key={index}
                                                review={review}
                                                onDelete={handleDelete}
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

export default UserReviewsPage;
