import { Button } from '@/pages/SignUp/styles';
import useSWR, { mutate } from 'swr';
import fetcher from '@/shared/utils/fetcher';
import React, { FC, useCallback, useState } from 'react';
import axios from 'axios';
import { Route, useNavigate } from 'react-router-dom';
import { Header, ProfileImg, RightMenu } from '@/layouts/Workspace/styles';

const Workspace:FC= ({children}:any) => {
  const navigate = useNavigate();
  const { data, error, mutate } = useSWR('/api/members', fetcher);
  const onLogout = useCallback(() => {
    axios.post('/api/members/logout', null, {
      withCredentials: true,

    })
      .then((res) => {
        localStorage.removeItem('login-token')
        // data = res.data
      })
  }, [])



  return (
    <div>
      <Header>
        <RightMenu>
          <span>
            <ProfileImg src=""></ProfileImg>
          </span>
        </RightMenu>
      </Header>
    <button onClick={onLogout}> Logout </button>
  {children} 테스트
    </div>
  )
}


export default Workspace;