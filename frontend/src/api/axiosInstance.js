import axios from "axios";

const instance = axios.create({
    baseURL: "/api",
    withCredentials: true, // 세션 쿠키 전송에 꼭 필요!
});

export default instance;