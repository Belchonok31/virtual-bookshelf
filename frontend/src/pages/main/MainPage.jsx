import React from 'react';
import { useEffect } from 'react';
import Cookies from 'universal-cookie';
import { useNavigate } from 'react-router-dom';

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
        <div>hello world</div>
    )
}

export default MainPage;