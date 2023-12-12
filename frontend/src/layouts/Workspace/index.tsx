import { Button } from '@/pages/SignUp/styles';
import useSWR, { mutate } from 'swr';
import fetcher from '@/shared/utils/fetcher';
import React, { FC, useCallback, useState } from 'react';
import axios from 'axios';
import { Route, useNavigate } from 'react-router-dom';

const Workspace:FC= ({children}:any) => {
  const navigate = useNavigate();
  // const { data, error, mutate } = useSWR('/api/members', fetcher);
  const onLogout = useCallback(() => {
    axios.post('/api/members/logout', null, {
      withCredentials: true,

    })
      .then(() => {
        localStorage.removeItem('login-token')
      })
  }, [])



  return (
    <div>
    <button onClick={onLogout}> Logout </button>
  {children} 테스트
    </div>
  )
}


export default Workspace;