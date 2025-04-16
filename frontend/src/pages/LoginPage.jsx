// src/pages/LoginPage.jsx
import React from 'react';
import LoginForm from '../components/auth/LoginForm';

const LoginPage = () => {
    return (
        <div className="min-h-screen flex">
            {/* 왼쪽 소개 영역 */}
            <div className="w-8/12 bg-gradient-to-br from-indigo-500 to-purple-500 text-white flex flex-col justify-center items-center p-12">
                <h2 className="text-4xl font-bold mb-4">환영합니다!</h2>
                <p className="text-lg text-center">
                    아름답고 간단한 게시판에 오신 것을 환영합니다 🌈<br />
                    지금 바로 로그인하고 시작하세요!
                </p>
                <img
                    src="https://source.unsplash.com/400x300/?technology,design"
                    alt="intro"
                    className="mt-8 rounded-xl shadow-lg"
                />
            </div>

            {/* 오른쪽 로그인 영역 */}
            <div className="w-4/12 flex items-center justify-center bg-white">
                {/* ← 여기 padding-right (pr-12)으로 오른쪽 공간 확보 */}
                <div className="w-full max-w-md p-8">
                    <LoginForm />
                </div>
            </div>
        </div>

    );
};

export default LoginPage;
