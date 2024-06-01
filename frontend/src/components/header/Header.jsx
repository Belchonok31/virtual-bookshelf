import React from 'react';
import { Link } from 'react-router-dom';
import styles from './Header.module.css';

const Header = () => {
    return (
        <header className={styles.header}>
            <div className={styles.logo}>
                <img src="https://i.pinimg.com/originals/9c/1c/08/9c1c087dab8c7e359dd2a6e789f5bb62.png" alt="Logo" />
                <span><Link to="/">Виртуальная книжка полка</Link></span>
            </div>
            <nav>
                <ul>
                    <li><Link to="/shelf">Полки</Link></li>
                    <li><Link to="/bookOriginal">Каталог</Link></li>
                </ul>
            </nav>
            <Link to="/user">
                <img src="https://kitt-nn.ru/wp-content/uploads/2023/06/team-1.png" alt="User Icon" className={styles.userIcon} />
            </Link>
        </header>
    );
}

export default Header;
