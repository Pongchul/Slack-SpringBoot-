import React, { useCallback, useState } from 'react';
import { Button, Form, Header, Input, Label, LinkContainer, Error } from '@/pages/SignUp/styles';
import { Link, Navigate, Route, useRevalidator } from 'react-router-dom';
import useInput from '@/shared/hooks/useInput';
import axios from 'axios';
import useSWR, { useSWRConfig } from 'swr';
import fetcher from '@/shared/utils/fetcher';

const LogIn = () => {
  const { data, error } = useSWR('/api/members', fetcher);
  const { mutate } = useSWRConfig();
  const [logInError, setLogInError] = useState(false);
  const [email, onChangeEmail] = useInput('');
  const [password, onChangePassword] = useInput('');
  const onSubmit = useCallback(
    (e:any) => {
      e.preventDefault();
      setLogInError(false);
      axios
        .post(
          '/api/members/login',
          { email, password },
          {
            withCredentials: true,
          },
        )
        .then((response) => {
          mutate('/api/members');
          console.log("<<<< response >>>>" , response);
        })
        .catch((error) => {
          setLogInError(error.response?.status === 401);
        });
    },
    [email, password],
  );

  if (data) {
    return <Route path="/workspace/channel" />
  }

  return (
    <div id="container">
      <Header>Slact</Header>
      <Form onSubmit={onSubmit}>
        <Label id="email-label">
          <span>이메일 주소</span>
          <div>
            <Input type="email" id="email" name="email" value={email} onChange={onChangeEmail} />
          </div>
        </Label>
        <Label id="password-label">
          <span>비밀번호</span>
          <div>
            <Input type="password" id="password" name="password" value={password} onChange={onChangePassword} />
          </div>
          {logInError && <Error>이메일과 비밀번호 조합이 일치하지 않습니다.</Error>}
        </Label>
        <Button type="submit">로그인</Button>
      </Form>
      <LinkContainer>
        아직 회원이 아니신가요?&nbsp;
        <Link to="/signup">회원가입 하러가기</Link>
      </LinkContainer>
    </div>
  );
}


export default LogIn;