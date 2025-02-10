// useFetchData.js
import { useState, useEffect } from "react";
import api from "../api/AxiosInterceptors"; // adjust path as needed

const useFetchData = (endpoint) => {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [refreshKey, setRefreshKey] = useState(0);

    // Hàm gọi lại API bằng cách thay đổi refreshKey
    const refreshData = () => {
        setRefreshKey((prev) => prev + 1);
    };

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            try {
                const response = await api.get(endpoint);
                // Chỉnh sửa theo cấu trúc dữ liệu trả về từ API của bạn
                setData(response.data.data);
            } catch (err) {
                setError(err);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, [endpoint, refreshKey]);

    return { data, loading, error, refreshData };
};

export default useFetchData;
