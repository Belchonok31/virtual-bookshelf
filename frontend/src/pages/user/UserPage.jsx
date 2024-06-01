import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Button, Modal, Form } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { getUserFromContext, updateUser } from '../../redux/features/users/userAction';
import { getReviews } from '../../redux/features/reviews/reviewsActions'
import styles from './UserPage.module.css';
import Cookies from 'universal-cookie';
import Header from '../../components/header/Header';
import Review from '../../components/review/Review';

const UserPage = () => {

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

    const [show, setShow] = useState(false);

    const [userForm, setUserForm] = useState({
        firstName: '',
        middleName: '',
    });

    const handleUserChange = (e) => {
        setUserForm({ ...userForm, [e.target.name]: e.target.value });
    };

    const handleShow = () => {
        setUserForm({ firstName: user.firstName, middleName: user.middleName});
        setShow(true);
    };

    const handleClose = () => {
        setShow(false);
    };

    const handleUpdate = (e) => {
        dispatch(updateUser(user.id, userForm));
        setShow(false);
    };


    return (
        <div>
            <Header />
            <div className={styles.userPage}>
                {user && (
                    <>
                        <div className={styles.userInfo}>
                            <img src="https://kitt-nn.ru/wp-content/uploads/2023/06/team-1.png" alt="User Avatar" className={styles.userAvatar} />
                            <div className={styles.userDetails}>
                                <div className={styles.item}><strong>Имя:</strong> {user.firstName}</div>
                                <div className={styles.item}><strong>Фамилия:</strong> {user.middleName}</div>
                                <div className={styles.item}><strong>Email:</strong> {user.email}</div>
                                <div className={styles.item}><strong>Роль:</strong> {user.role}</div>
                                <div className={styles.actions}>
                                    <button className={styles.actionButton} onClick={handleShow}>Редактировать</button>
                                </div>
                            </div>
                        </div>
                        <div className={styles.doing}>
                            <Link to='/user/reviews'>
                                <span className={styles.label}>Мои отзывы</span>
                            </Link>
                        </div>
                    </>
                )}
            </div>
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Редактировать профиль</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>Имя</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Any user firstName"
                                name="firstName"
                                value={userForm.firstName}
                                onChange={handleUserChange}
                                required
                            />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>Фамилия</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Any user middleName"
                                name="middleName"
                                value={userForm.middleName}
                                onChange={handleUserChange}
                                required
                            />
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Закрыть
                    </Button>
                    <Button variant="primary" onClick={handleUpdate}>
                        Сохранить
                    </Button>
                </Modal.Footer>
            </Modal>
        </div>

    );
};

export default UserPage;
