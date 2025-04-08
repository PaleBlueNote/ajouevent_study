import React, { useState } from 'react';
import { createPost } from '../api/posts';

const PostForm = ({ onPostCreated }) => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [author, setAuthor] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await createPost({ title, content, author });
            console.log('글 작성 성공:', response.data);
            if (onPostCreated) onPostCreated(); // 새 글 작성 후 목록 새로고침용
            setTitle('');
            setContent('');
            setAuthor('');
        } catch (error) {
            console.error('글 작성 실패:', error);
        }
    };

    return (
        <form
            onSubmit={handleSubmit}
            className="bg-white p-6 rounded-2xl shadow-md mb-8 space-y-4"
        >
            <input
                type="text"
                placeholder="제목"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                className="w-full border border-gray-300 rounded-lg p-2 bg-white text-gray-800
             focus:outline-none focus:ring-2 focus:ring-blue-400"
                required
            />
            <textarea
                placeholder="내용"
                value={content}
                onChange={(e) => setContent(e.target.value)}
                className="w-full border border-gray-300 rounded-lg p-2 bg-white text-gray-800
             focus:outline-none focus:ring-2 focus:ring-blue-400"
                required
            />
            <input
                type="text"
                placeholder="작성자"
                value={author}
                onChange={(e) => setAuthor(e.target.value)}
                className="w-full border border-gray-300 rounded-lg p-2 bg-white text-gray-800
             focus:outline-none focus:ring-2 focus:ring-blue-400"
                required
            />
            <div className="text-right">
                <button
                    type="submit"
                    className="w-full border border-gray-300 rounded-lg p-2 bg-white text-gray-800
             focus:outline-none focus:ring-2 focus:ring-blue-400"
                >
                    ✏️ 작성하기
                </button>
            </div>
        </form>
    );
}; export default PostForm;
