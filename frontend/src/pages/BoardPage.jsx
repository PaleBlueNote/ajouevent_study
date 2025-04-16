// src/pages/BoardPage.jsx
import React, { useEffect, useState } from 'react';
import PostList from '../components/PostList';
import PostForm from '../components/PostForm';
import { fetchPosts } from '../api/posts';

const BoardPage = () => {
    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(true);

    const loadPosts = async () => {
        try {
            const data = await fetchPosts();
            setPosts(data);
        } catch (error) {
            console.error("게시글 로딩 실패:", error);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        loadPosts();
    }, []);

    return (
        <div className="min-h-screen bg-gray-50 py-8 px-4 sm:px-6 lg:px-8">
            <div className="max-w-7xl mx-auto w-full">
                <h1 className="text-3xl font-bold mb-6 text-center text-gray-800">📢 게시판</h1>
                <PostForm onPostCreated={loadPosts} />
                {loading ? (
                    <p className="text-center text-gray-500">게시글을 불러오는 중...</p>
                ) : posts.length === 0 ? (
                    <p className="text-center text-gray-500">게시글이 없습니다.</p>
                ) : (
                    <PostList posts={posts} />
                )}
            </div>
        </div>
    );
};

export default BoardPage;
