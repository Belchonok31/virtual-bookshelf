import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { Button, Modal, Form } from 'react-bootstrap';
import { getShelfs, createShelf, updateShelf, removeShelf } from '../../redux/features/shelfs/shelfActions'
import Shelf from '../../components/shelf/Shelf';
import styles from './ShelfPage.module.css';
import Cookies from 'universal-cookie';
import Header from '../../components/header/Header';

const ShelfPage = () => {

    const dispatch = useDispatch();
    const cookie = new Cookies();
    const navigate = new useNavigate();

    useEffect(() => {
        const token = cookie.get('token');
        if (!token) {
            navigate('/signIn')
        }
        dispatch(getShelfs())
    }, [dispatch]);


    const [show, setShow] = useState(false);
    const [editingShelf, setEditingShelf] = useState(null);
    const shelfs = useSelector((state) => state.shelf.items);


    const [shelfForm, setShelfForm] = useState({
        name: '',
        description: '',
    });

    const handleShelfChange = (e) => {
        setShelfForm({ ...shelfForm, [e.target.name]: e.target.value });
    };

    const handleShow = (shelf = null) => {
        if (shelf) {
            setEditingShelf(shelf);
            setShelfForm({ name: shelf.name, description: shelf.description });
        } else {
            setEditingShelf(null);
            setShelfForm({ name: '', description: '' });
        }
        setShow(true);
    };

    const handleClose = () => {
        setShow(false);
    };

    const handleAdd = (e) => {
        e.preventDefault();
        if (editingShelf) {
            dispatch(updateShelf(editingShelf.id, shelfForm));
        } else {
            dispatch(createShelf(shelfForm));
        }
        setShow(false);
        setShelfForm({ name: '', description: '' });
    };

    const handleDelete = (id) => {
        dispatch(removeShelf(id));
    };

    return (
        <div className={styles.main}>
            <Header />
            <div className={styles.container}>
                    <div className={styles.header}>
                        <button className={styles.newButton} onClick={() => handleShow()}>Добавить полку</button>
                        <h2>Мои полки</h2>
                        <input type="text" placeholder="Поиск" className={styles.searchInput} />
                    </div>
                    <div className={styles.shelfList}>
                        {shelfs.map((shelf) => (
                            <Shelf
                                key={shelf.id}
                                shelf={shelf}
                                onEdit={() => handleShow(shelf)}
                                onDelete={handleDelete}
                            />
                        ))}
                    </div>
                    <Modal show={show} onHide={handleClose}>
                        <Modal.Header closeButton>
                            <Modal.Title>{editingShelf ? 'Редактировать полку' : 'Добавить новую полку'}</Modal.Title>
                        </Modal.Header>
                        <Modal.Body>
                            <Form>
                                <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                                    <Form.Label>Имя полки</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Any name shelf"
                                        name="name"
                                        value={shelfForm.name}
                                        onChange={handleShelfChange}
                                        required
                                    />
                                </Form.Group>
                                <Form.Group
                                    className="mb-3"
                                    controlId="exampleForm.ControlTextarea1"
                                >
                                    <Form.Label>Описание</Form.Label>
                                    <Form.Control
                                        as="textarea"
                                        rows={3}
                                        placeholder="Any description"
                                        name="description"
                                        value={shelfForm.description}
                                        onChange={handleShelfChange}
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

export default ShelfPage;
