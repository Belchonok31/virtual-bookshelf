import React from 'react';
import { useEffect } from 'react';
import Cookies from 'universal-cookie';
import { useNavigate } from 'react-router-dom';
import Header from '../../components/header/Header';
import MainSection from '../../components/mainSection/MainSection';

function MainPage() {

  const cookie = new Cookies()
  const navigate = useNavigate();

  useEffect(() => {
    const token = cookie.get('token');
    if (!token) {
      navigate("/signUp");
    }
  }, []);

  return (
    <div>
      <Header />
      <MainSection />
    </div>
  )
}

export default MainPage;