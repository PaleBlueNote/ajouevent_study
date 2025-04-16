// src/components/auth/LoginForm.jsx
import React, { useState } from 'react';
import { login } from '../../api/auth';
import { AuthWrapper, Input, ErrorMessage, Button, SubLinks, SubLink } from '../../styles/AuthStyles';

export default function LoginForm({onSwitch}) {
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
        <AuthWrapper>
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
                    <ErrorMessage>
                        {error}
                    </ErrorMessage>
                )}
                <SubLinks>
                    <SubLink type="button">비밀번호 찾기</SubLink>
                    <SubLink type="button" onClick={onSwitch}>회원가입</SubLink>
                </SubLinks>
            </form>
        </AuthWrapper>
    );
}
