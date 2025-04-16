import api from './axiosInstance'; // axios 인스턴스 가져오기

export const signUp = async (userData) => {
    return await api.post('/users', userData);
};

export const checkUsernameAvailability = async (tempId) => {
    return await api.post('/users/validate', null, {
        params: { id: tempId },
    });
};

export const login = async (loginData) => {
    return await api.post('/users/login', loginData);
};

export const logout = async () => {
    return await api.post('/users/logout', {});
};

export const getMyInfo = async () => {
    const response = await api.get('/users/info');
    return response.data;
};

export const changeUserInfo = async (changeData) => {
    return await api.put('/users/info', changeData);
};

export const deleteUser = async () => {
    return await api.delete('/users/info');
};

export const getUserInfo = async (userId) => {
    return await api.get(`/users/info/${userId}`);
};
