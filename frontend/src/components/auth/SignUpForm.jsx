// src/components/auth/SignUpForm.jsx
import React, { useState } from 'react';
import {checkUsernameAvailability, signUp} from '../../api/auth';
import { AuthWrapper, Input, Button,ErrorMessage, DuplicatedButton, SpacedBox, SubLinks, SubLink } from '../../styles/AuthStyles';


export default function SignUpForm({ onSwitch }) {
    const [form, setForm] = useState({
        userLoginId: '',
        userName: '',
        userNickname: '',
        userPhoneNumber: '',
        userPassword: '',
        userPasswordVerified: ''
    });
    const [idError, setIdError] = useState('');
    const [submitError, setSubmitError] = useState('');


    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // 1. 아이디 유효성 확인
        if (!idError.includes('가능')) {
            setSubmitError('아이디 중복 확인이 필요합니다.');
            return;
        }

        // 3. 비밀번호 일치 확인
        if (form.userPassword !== form.userPasswordVerified) {
            setSubmitError('비밀번호가 일치하지 않습니다.');
            return;
        }

        // 2. 비밀번호 유효성 체크
        const pwRegex = /^[A-Za-z0-9]{5,30}$/;
        if (!pwRegex.test(form.userPassword)) {
            setSubmitError('비밀번호는 영문/숫자 조합 5~30자여야 합니다.');
            return;
        }


        // 4. 성공 시
        try {
            await signUp(form);
            alert('회원가입 성공!');
            onSwitch(); // 로그인 폼으로 전환
            setSubmitError('');
        } catch (e) {
            setSubmitError('회원가입 중 오류가 발생했습니다.');
        }
    };


    const handleCheckDuplicate = async () => {
        if (!form.userLoginId) {
            setIdError('아이디를 입력해주세요');
            return;
        }

        try {
            await checkUsernameAvailability(form.userLoginId);
            setIdError('사용 가능한 아이디입니다!');
        } catch (err) {
            if (err.response?.data?.detailStatusCode === 409002) {
                setIdError('이미 사용 중인 아이디입니다');
            } else if (err.response?.data?.detailStatusCode === 409001) {
                setIdError('아이디 형식을 확인해주세요');
            } else {
                setIdError('확인 중 오류가 발생했습니다');
            }
        }
    }

    return (
        <AuthWrapper>
            <h2 className="text-2xl font-bold text-center mb-6 text-gray-800">회원가입</h2>
            <form onSubmit={handleSubmit}>
                <Input
                    name="userLoginId"
                    placeholder="아이디"
                    marginBottom="none"
                    onChange={(e) => {
                        setForm({ ...form, userLoginId: e.target.value });
                        setIdError(''); // 입력 바뀌면 에러 초기화
                    }}
                    required
                />
                { idError && (
                    <p className={`text-sm mt-1 text-end ${
                        idError.includes('가능') ? 'text-blue-500' : 'text-red-500'
                    }`}>
                        {idError}
                    </p>

                )
                }
                <SpacedBox></SpacedBox>
                <DuplicatedButton type="button" onClick={handleCheckDuplicate}>중복 확인</DuplicatedButton>
                <SpacedBox></SpacedBox>
                <SpacedBox></SpacedBox>
                <Input name="userName" placeholder="이름" onChange={handleChange} required />
                <Input name="userNickname" placeholder="닉네임" onChange={handleChange} required />
                <Input name="userPhoneNumber" placeholder="전화번호 (선택)" onChange={handleChange} />
                <SpacedBox></SpacedBox>
                <SpacedBox></SpacedBox>
                <Input name="userPassword" placeholder="비밀번호" type="password" onChange={handleChange} required />
                <Input name="userPasswordVerified" placeholder="비밀번호 확인" type="password" onChange={handleChange} required />
                <ErrorMessage>{submitError}</ErrorMessage>
                <Button type="submit">가입하기</Button>
                <SubLinks>
                    <SubLink type="button" onClick={onSwitch}>이미 계정이 있으신가요?</SubLink>
                </SubLinks>
            </form>
        </AuthWrapper>
    );
}
