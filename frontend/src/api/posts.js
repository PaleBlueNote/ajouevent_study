import axios from "axios";

const BASE_URL = "http://localhost:8080";

export const fetchPosts = async () => {
    const response = await axios.get(`${BASE_URL}/api/posts`);
    return response.data;
};

export const createPost = async (postData) => {
    return await axios.post(`${BASE_URL}/api/posts`, postData);
};
