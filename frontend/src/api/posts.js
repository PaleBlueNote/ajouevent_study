// src/api/posts.js
import api from './axiosInstance'; // axios 인스턴스를 가져옴

export const fetchPosts = async () => {
    const response = await api.get('/posts');
    return response.data;
};

export const createPost = async (postData) => {
    const response = await api.post('/posts', postData);
    return response.data;
};
