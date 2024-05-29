import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Button, Modal, Form } from 'react-bootstrap';
import { getShelfs, addShelf } from '../../redux/features/shelfs/shelfThunks';
import Shelf from '../../components/shelf/Shelf';
import styles from './ShelfPage.module.css';
import Cookies from 'universal-cookie';

const ShelfPage = () => {

    const cookie = new Cookies()
    useEffect(() => {
        const token = cookie.get('token');
        if (!token) {
          window.location.href = '/signIn';
        }
        else {
            dispatch(getShelfs());
        }
      }, []);


    const dispatch = useDispatch();
    const [show, setShow] = useState(false);
    const shelfs = useSelector((state) => state.shelf.items);

    const [shelfForm, setShelfForm] = useState({
        name: '',
        description: '',
    });

    const handleShelfChange = (e) => {
        setShelfForm({ ...shelfForm, [e.target.name]: e.target.value });
    };

    const handleShow = () => {
        setShow(true);
    };

    const handleClose = () => {
        setShow(false);
    };

    const handleAdd = (e) => {
        e.preventDefault();
        dispatch(addShelf(shelfForm));
        window.location.reload();
    };

    return (
        <div className={styles.container}>
            <div className={styles.header}>
                <button className={styles.newButton} onClick={handleShow}>Добавить полку</button>
                <h2>Мои полки</h2>
                <input type="text" placeholder="Поиск" className={styles.searchInput} />
            </div>
            <div className={styles.shelfList}>
                {shelfs.map((shelf) => (
                    <Shelf key={shelf.id} shelf={shelf} />
                ))}
            </div>
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Добавить новую полку</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>Имя полки</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Shelf_1"
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
                            <Form.Control as="textarea" rows={3} 
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
    );
}

export default ShelfPage;
