import React from 'react';
import { Link } from 'react-router-dom';
import styles from './MainSection.module.css';

const MainSection = () => {
  return (
    <section className={styles.mainSection}>
      <img src="https://a.l3n.co/i/9vsGM.jpg" alt="Main Image" />
      <div className={styles.overlayText}>
        <p>Добро пожаловать на нашу платформу - вашу личную виртуальную книжную полку!
          Наш сайт создан для любителей книг, которые хотят легко находить, сохранять и управлять своими любимыми произведениями.</p>
      </div>
      <Link to="/bookOriginal" className={styles.catalogButton}>
          Перейти в каталог
      </Link>
    </section>
  );
}

export default MainSection;
