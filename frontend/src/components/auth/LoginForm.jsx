// src/components/auth/LoginForm.jsx
import React, { useState } from 'react';
import styled from 'styled-components';
import { login } from '../../api/auth';

// 🎨 styled-components로 스타일 정의
const LoginWrapper = styled.div`
  max-width: 400px;
  margin: 0 auto;
  margin-top: 10vh;
  background: white;
  padding: 2rem;
  border-radius: 1.5rem;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
`;

const Input = styled.input`
    width: 100%;
    padding: 0.75rem 1rem;
    margin-bottom: 1rem;
    border: 1px solid #ddd;
    border-radius: 0.75rem;
    font-size: 1rem;
    color: #1a1a1a;
    background-color: #f9fafb;

    &:focus {
        outline: none;
        border-color: #6366f1;
        box-shadow: 0 0 0 3px rgba(99, 102, 241, 0.2);
    }
`;

const Button = styled.button`
  width: 100%;
  padding: 0.75rem 1rem;
  background-color: #6366f1;
  color: white;
  font-weight: bold;
  border: none;
  border-radius: 0.75rem;
  cursor: pointer;
  transition: background 0.2s;

  &:hover {
    background-color: #4f46e5;
  }
`;

const SubLinks = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 1rem;
`;

const SubLink = styled.button`
  background: none;
  border: none;
  color: #6b7280;
  font-size: 0.9rem;
  text-decoration: underline;
  cursor: pointer;

  &:focus {
    outline: none;
  }

  &:hover {
    color: #374151;
  }
`;

export default function LoginForm() {
    const [userLoginId, setUserLoginId] = useState('');
    const [userPassword, setUserPassword] = useState('');
    const [error, setError] = useState('');


    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const res = await login({ userLoginId, userPassword });
            console.log('로그인 성공:', res.data);
            setError('');
        } catch (err) {
            console.error('로그인 실패:', err);
            setError('로그인에 실패하였습니다!');
        }
    };

    return (
        <LoginWrapper>
            <h2 className="text-2xl font-bold text-center mb-6 text-gray-800">로그인 폼</h2>
            <form onSubmit={handleSubmit}>
                <Input
                    type="text"
                    placeholder="아이디를 입력해주세요"
                    value={userLoginId}
                    onChange={(e) => setUserLoginId(e.target.value)}
                    required
                />
                <Input
                    type="password"
                    placeholder="비밀번호를 입력해주세요"
                    value={userPassword}
                    onChange={(e) => setUserPassword(e.target.value)}
                    required
                />
                <Button type="submit">로그인</Button>
                {error && (
                    <p className="text-red-500 text-sm mt-2 text-center">
                        {error}
                    </p>
                )}
                <SubLinks>
                    <SubLink type="button">비밀번호 찾기</SubLink>
                    <SubLink type="button">회원가입</SubLink>
                </SubLinks>
            </form>
        </LoginWrapper>
    );
}
