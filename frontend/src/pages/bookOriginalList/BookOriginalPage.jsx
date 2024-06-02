import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { Button, Modal, Form } from 'react-bootstrap';
import {
    getBookOriginalsAll,
    createBookOriginal,
    updateBookOriginal,
    removeBookOriginal
} from '../../redux/features/bookOriginals/bookOriginalActions'
import {createBook} from '../../redux/features/books/bookAction'
import BookOriginal from '../../components/bookOriginal/BookOriginal';
import styles from './BookOriginalPage.module.css';
import Cookies from 'universal-cookie';
import Header from '../../components/header/Header'
import {getShelfs} from '../../redux/features/shelfs/shelfActions'

const BookOriginalPage = () => {

    const dispatch = useDispatch();
    const cookie = new Cookies();
    const navigate = useNavigate();

    useEffect(() => {
        const token = cookie.get('token');
        if (!token) {
            navigate("/signIn");
        }
        dispatch(getBookOriginalsAll())
        dispatch(getShelfs())
    }, [dispatch]);


    const [show, setShow] = useState(false);
    const [show2, setShow2] = useState(false);

    const [editingBookOriginal, setEditingBookOriginal] = useState(null);
    const BookOriginals = useSelector((state) => state.bookOriginal.items);

    const shelfs = useSelector((state) => state.shelf.items);
    const placeholderText = shelfs.map(shelf => shelf.id).join(', ');


    const [selectedBook, setSeletedBook] = useState(null);


    const [bookOriginalForm, setBookOriginalForm] = useState({
        name: '',
        authors: '',
        genre: '',
        dateIssue: '',
        description: '',
    });

    const [bookForm, setBookForm] = useState({
        name: '',
        label: '',
        shelf: '',
    });

    const handleBookChange = (e) => {
        setBookForm({ ...bookForm, [e.target.name]: e.target.value });
    };
    const handleBookOriginalChange = (e) => {
        setBookOriginalForm({ ...bookForm, [e.target.name]: e.target.value });
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

    const handleShow2 = (bookOriginal) => {
        setSeletedBook(bookOriginal);
        setShow2(true);
    };

    const handleClose2 = () => {
        setShow2(false);
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

    const handleAddBook = (e) => {
        e.preventDefault();
        dispatch(createBook(selectedBook.id, bookForm, bookForm.shelf))
        setShow2(false);
        setBookForm({ name: '', label: '', shelf: ''});
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
                            onAdd={() => handleShow2(bookOriginal)}
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

                <Modal show={show2} onHide={handleClose2}>
                    <Modal.Header closeButton>
                        <Modal.Title>Добавить новую книгу</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <Form.Group className="mb-3">
                                <Form.Label>Имя книги</Form.Label>
                                <Form.Control
                                    type="text"
                                    placeholder='Any name book'
                                    name="name"
                                    value={bookForm.name}
                                    onChange={handleBookChange}
                                    required
                                />
                            </Form.Group>
                            <Form.Group className="mb-3">
                                <Form.Label>Надпись</Form.Label>
                                <Form.Control
                                    type="text"
                                    placeholder="Any label book"
                                    name="label"
                                    value={bookForm.label}
                                    onChange={handleBookChange}
                                    required
                                />
                            </Form.Group>
                            <Form.Group className="mb-3">
                                <Form.Label>Полка</Form.Label>
                                <Form.Control
                                    type="number"
                                    placeholder={placeholderText}
                                    name="shelf"
                                    value={bookForm.shelf}
                                    onChange={handleBookChange}
                                    required
                                />
                            </Form.Group>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleClose2}>
                            Закрыть
                        </Button>
                        <Button variant="primary" onClick={handleAddBook}>
                            Сохранить
                        </Button>
                    </Modal.Footer>
                </Modal>
            </div>
        </div>

    );
}

export default BookOriginalPage;
