// useFetchData.js
import { useState, useEffect } from "react";
import api from "../api/AxiosInterceptors"; // adjust path as needed

const useFetchData = (endpoint) => {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await api.get(endpoint);
                setData(response.data.data); // adjust based on your API response structure
            } catch (err) {
                setError(err);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, [endpoint]);

    return { data, loading, error };
};

export default useFetchData;
