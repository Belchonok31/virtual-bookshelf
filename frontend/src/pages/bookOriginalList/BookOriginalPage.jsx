import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Button, Modal, Form } from 'react-bootstrap';
import {
    getBookOriginalsAll,
    createBookOriginal,
    updateBookOriginal,
    removeBookOriginal
} from '../../redux/features/bookOriginals/bookOriginalActions'
import BookOriginal from '../../components/bookOriginal/BookOriginal';
import styles from './BookOriginalPage.module.css';
import Cookies from 'universal-cookie';
import Header from '../../components/header/Header'

const BookOriginalPage = () => {

    const dispatch = useDispatch();
    const cookie = new Cookies();

    useEffect(() => {
        const token = cookie.get('token');
        if (!token) {
            window.location.href = '/signIn';
        }
        dispatch(getBookOriginalsAll())
    }, [dispatch]);


    const [show, setShow] = useState(false);
    const [editingBookOriginal, setEditingBookOriginal] = useState(null);
    const BookOriginals = useSelector((state) => state.bookOriginal.items);


    const [bookOriginalForm, setBookOriginalForm] = useState({
        name: '',
        authors: '',
        genre: '',
        dateIssue: '',
        description: '',
    });

    const handleBookOriginalChange = (e) => {
        setBookOriginalForm({ ...bookOriginalForm, [e.target.name]: e.target.value });
    };

    const handleShow = (BookOriginal = null) => {
        if (BookOriginal) {
            setEditingBookOriginal(BookOriginal);
            setBookOriginalForm({
                name: BookOriginal.name, authors: BookOriginal.authors,
                genre: BookOriginal.genre, dateIssue: BookOriginal.dateIssue, description: BookOriginal.description
            });
        } else {
            setEditingBookOriginal(null);
            setBookOriginalForm({ name: '', authors: '', genre: '', dateIssue: '', description: '' });
        }
        setShow(true);
    };

    const handleClose = () => {
        setShow(false);
    };

    const handleAdd = (e) => {
        e.preventDefault();
        if (editingBookOriginal) {
            dispatch(updateBookOriginal(editingBookOriginal.id, bookOriginalForm));
        } else {
            dispatch(createBookOriginal(bookOriginalForm));
        }
        setShow(false);
        setBookOriginalForm({ name: '', authors: '', genre: '', dateIssue: '', description: '' });
    };

    const handleDelete = (id) => {
        dispatch(removeBookOriginal(id));
    };

    return (
        <div className={styles.main}>
            <Header />
            <div className={styles.container}>
                <div className={styles.header}>
                    <button className={styles.newButton} onClick={() => handleShow()}>Добавить книгу</button>
                    <h2>Книги первоисточники</h2>
                    <input type="text" placeholder="Поиск" className={styles.searchInput} />
                </div>
                <div className={styles.bookOriginalList}>
                    {BookOriginals.map((bookOriginal) => (
                        <BookOriginal
                            key={bookOriginal.id}
                            bookOriginal={bookOriginal}
                            onEdit={() => handleShow(bookOriginal)}
                            onDelete={handleDelete}
                        />
                    ))}
                </div>
                <Modal show={show} onHide={handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>{editingBookOriginal ? 'Редактировать книгу' : 'Добавить новую книгу'}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <Form.Group className="mb-3">
                                <Form.Label>Имя книги</Form.Label>
                                <Form.Control
                                    type="text"
                                    placeholder="Any name BookOriginal"
                                    name="name"
                                    value={bookOriginalForm.name}
                                    onChange={handleBookOriginalChange}
                                    required
                                />
                            </Form.Group>
                            <Form.Group className="mb-3">
                                <Form.Label>Автор</Form.Label>
                                <Form.Control
                                    type="text"
                                    placeholder="Any authors"
                                    name="authors"
                                    value={bookOriginalForm.authors}
                                    onChange={handleBookOriginalChange}
                                    required
                                />
                            </Form.Group>
                            <Form.Group className="mb-3">
                                <Form.Label>Жанр</Form.Label>
                                <Form.Control
                                    type="text"
                                    placeholder="Any genre"
                                    name="genre"
                                    value={bookOriginalForm.genre}
                                    onChange={handleBookOriginalChange}
                                    required
                                />
                            </Form.Group>
                            <Form.Group className="mb-3">
                                <Form.Label>Дата выпуска</Form.Label>
                                <Form.Control
                                    type="number"
                                    min="1700" max="2025" step="1"
                                    placeholder="Any dateIssue"
                                    name="dateIssue"
                                    value={bookOriginalForm.dateIssue}
                                    onChange={handleBookOriginalChange}
                                    required
                                />
                            </Form.Group>
                            <Form.Group
                                className="mb-3">
                                <Form.Label>Описание</Form.Label>
                                <Form.Control
                                    as="textarea"
                                    rows={3}
                                    placeholder="Any description"
                                    name="description"
                                    value={bookOriginalForm.description}
                                    onChange={handleBookOriginalChange}
                                />
                            </Form.Group>
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
        </div>

    );
}

export default BookOriginalPage;
