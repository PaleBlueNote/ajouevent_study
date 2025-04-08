// src/components/PostCard.jsx
import React from 'react';

function PostCard({ post }) {
    return (
        <div className="bg-white rounded-2xl shadow-md p-5 hover:shadow-lg transition duration-300">
            <div className="flex items-center gap-3 mb-3">
                <div className="w-10 h-10 rounded-full bg-gradient-to-tr from-indigo-400 to-purple-400 flex items-center justify-center text-white font-bold text-sm">
                    {post.author?.[0]?.toUpperCase() || "?"}
                </div>
                <div>
                    <p className="font-semibold text-sm">{post.author || "익명"}</p>
                    <p className="text-xs text-gray-400">{new Date(post.createdAt).toLocaleString()}</p>
                </div>
            </div>

            <h2 className="text-lg font-bold text-gray-800 mb-2">{post.title}</h2>
            <p className="text-gray-700 mb-3 line-clamp-3">{post.content}</p>

            <div className="flex justify-between text-xs text-gray-500">
                <span>👍 {post.likes || 0} 좋아요</span>
                <span>👀 {post.views || 0} 조회수</span>
            </div>
        </div>
    );
} export default PostCard;