import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { getShelfs } from '../../redux/features/shelfs/shelfThunks';
import Shelf from '../../components/shelf/Shelf';
import styles from './ShelfPage.module.css'

const ShelfPage = () => {
    const dispatch = useDispatch();
    const shelfs = useSelector((state) => state.shelf.items);
    const shelfStatus = useSelector((state) => state.shelf.status);

    useEffect(() => {
        if (shelfStatus === 'idle') {
          dispatch(getShelfs());
        }
    }, [shelfStatus, dispatch]);

    return (
        <div>
            <h2>All Shelves</h2>
            {shelfs.map((shelf) => (
                <Shelf key={shelf.id} shelf={shelf} />
            ))}
        </div>
    )
}

export default ShelfPage;
