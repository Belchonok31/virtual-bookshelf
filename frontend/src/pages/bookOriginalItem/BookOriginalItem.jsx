import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { Button, Modal, Form } from 'react-bootstrap';
import { getBookOriginalById } from '../../redux/features/bookOriginals/bookOriginalActions';
import { getUsersAll, getUserFromContext } from '../../redux/features/users/userAction'
import { getStatistics } from '../../redux/features/statistics/statisticsAction'
import {createReviews, getReviewsByBookId} from '../../redux/features/reviews/reviewsActions'
import styles from './BookOriginalItem.module.css';
import Cookies from 'universal-cookie';
import Header from '../../components/header/Header'
import Review from '../../components/review/Review'


const BookOriginalItem = () => {

    const dispatch = useDispatch();
    const cookie = new Cookies();
    const { id } = new useParams();
    const navigate = useNavigate();

    useEffect(() => {
        const token = cookie.get('token');
        if (!token) {
            navigate("/signUp");
        }
        dispatch(getBookOriginalById(id))
        dispatch(getStatistics(id))
        dispatch(getReviewsByBookId(id))
        dispatch(getUsersAll())
        dispatch(getUserFromContext())
    }, [dispatch]);

    const Book = useSelector((state) => state.bookOriginal.selectedItem);

    const statistics = useSelector((state) => state.statistics.selectedItem);

    const reviews = useSelector((state) => state.review.items);

    const users = useSelector((state) => state.user.items);

    const user = useSelector((state) => state.user.selectedItem);


    const [show, setShow] = useState(false);

    const [error, setError] = useState('');

    const [reviewForm, setReviewForm] = useState({
        text: '',
        rating: '',
    });

    const handleReviewChange = (e) => {
        setReviewForm({ ...reviewForm, [e.target.name]: e.target.value });
    };

    const handleShow =() => {
        setShow(true);
    }

    const handleClose = () => {
        setShow(false);
        setError('');
    };

    const handleAdd = (e) => {
        e.preventDefault();
        const { rating } = reviewForm;
        if (rating < 1 || rating > 5) {
            setError('Оценка должна быть в диапазоне от 1 до 5');
            return;
        }
        dispatch(createReviews(Book?.id, reviewForm))
        dispatch(getStatistics(Book?.id))
        setShow(false);
        setReviewForm({ text: '', rating: ''});
        setError('');
    };
    
    const userHasReview = reviews.some((review) => review.book_original_id === Book?.id && review.user_id === user?.id);

    console.log(userHasReview)

    return (
        <div className={styles.main}>
            <Header />
            <div className={styles.BookPage}>
                {Book && (
                    <>
                        <div className={styles.infoPlusDescription}>
                            <div className={styles.BookInfo}>
                                <img src="https://clipart-library.com/images/riLnxRei8.jpg" alt="Book Cover" className={styles.BookImage} />
                                <div className={styles.BookDetails}>
                                    <h1 className={styles.BookName}>{Book.name}</h1>
                                    <p><strong>Автор:</strong> {Book.authors}</p>
                                    <p><strong>Жанр:</strong> {Book.genre}</p>
                                    <p><strong>Дата издания:</strong> {Book.dateIssue}</p>
                                    <div className={styles.rating}>
                                        <div className={styles.item}>
                                            <img src="https://www.clipartmax.com/png/full/73-730227_gold-star-star-clipart.png" alt="" />
                                            <span>{statistics?.rating ?? 0}</span>
                                        </div>
                                        <div className={styles.item}>
                                            <img src="https://cdn2.iconfinder.com/data/icons/circle-icons-1/64/magnifyingglass-1024.png" alt="" />
                                            <span>{statistics?.numberOfReviews ?? 0}</span>
                                        </div>
                                        <div className={styles.item}>
                                            <img src="https://cdn1.iconfinder.com/data/icons/smallicons-controls/32/614340-.svg-1024.png" alt="" />
                                            <span>{statistics?.additions ?? 0}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <p className={styles.BookDescription}><strong>Описание:</strong> {Book.description}</p>
                            <div className={styles.actions}>
                                <button className={styles.actionButton}>Добавить к себе</button>
                                {!userHasReview && (
                                    <button className={styles.deleteButton} onClick={() => handleShow()}>Оставить отзыв</button>
                                )}
                               
                            </div>
                        </div>
                        <div className={styles.BookReviews}>
                            <h2><strong>Отзывы:</strong></h2>
                            {reviews && (
                                <>
                                    <ul>

                                        {reviews.map((review, index) => (
                                            <Review
                                                key={index}
                                                author={users.find((user) => user?.id === review.user_id)?.email}
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
            <Modal show={show} onHide={handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Отзыв</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <Form.Group className="mb-3">
                                <Form.Label>Содержимое</Form.Label>
                                <Form.Control
                                    type="text"
                                    placeholder="Any text about this book"
                                    name="text"
                                    value={reviewForm.name}
                                    onChange={handleReviewChange}
                                    required
                                />
                            </Form.Group>
                            <Form.Group className="mb-3">
                                <Form.Label>Оценка</Form.Label>
                                <Form.Control
                                    type="number"
                                    min="0" max="5"
                                    placeholder="Any rating"
                                    name="rating"
                                    value={reviewForm.rating}
                                    onChange={handleReviewChange}
                                    required
                                />
                            </Form.Group>
                            {error && <p className="text-danger">{error}</p>}
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleClose}>
                            Закрыть
                        </Button>
                        <Button variant="primary" onClick={handleAdd}>
                            Сохранить
                        </Button>
                    </Modal.Footer>
                </Modal>
        </div>
        
    );
};

export default BookOriginalItem;
