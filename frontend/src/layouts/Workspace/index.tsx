import { Button } from '@/pages/SignUp/styles';
import useSWR from 'swr';
import fetcher from '@/shared/utils/fetcher';
import React, { FC, useCallback } from 'react';
import axios from 'axios';
import { Route } from 'react-router-dom';

const Workspace:FC= ({children}:any) => {
  const { data, error, mutate } = useSWR('/api/members', fetcher);
  const onLogout = useCallback(() => {
    axios.post('/api/members/logout', null, {
      withCredentials: true,

    })
      .then(() => {
        mutate();
      })
  }, [])

  if (!data) {
    return <Route path="/login" />
  }

  return (
    <div>
    <button onClick={onLogout}> Logout </button>
  {children}
    </div>
  )
}


export default Workspace;