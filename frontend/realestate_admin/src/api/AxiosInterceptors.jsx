import axios from 'axios';
import { Link } from 'react-router-dom';

const api = axios.create({
    baseURL: 'http://localhost:8080/api/v1',
});

// Add a request interceptor
api.interceptors.request.use(
    (config) => {
        const accessToken = localStorage.getItem('access_token');
        if (accessToken) {
            config.headers.Authorization = `Bearer ${accessToken}`;
        }
        return config;
    },
    (error) => Promise.reject(error)

);
api.interceptors.response.use(
    (response) => response,
    async (error) => {
        const originalRequest = error.config;


        if (error.response.status === 401 && !originalRequest._retry) {
            originalRequest._retry = true;

            try {

                let response = axios.get('http://localhost:8080/api/v1/auth/refresh', { withCredentials: true });
                const { access_token } = response.data;

                localStorage.setItem('access_token', access_token);

                // Retry the original request with the new token
                originalRequest.headers.Authorization = `Bearer ${access_token}`;
                return axios(originalRequest);
            } catch (error) {
                sessionStorage.clear();
                <Link to="/login" />;
            }
        }

        return Promise.reject(error);
    }
);
export default api
