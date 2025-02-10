// ProtectedRoute.jsx
import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import Cookies from 'js-cookie';

const ProtectedRoute = () => {
    // Check if the refresh token exists in cookies
    const refreshToken = Cookies.get('refresh_token');

    if (!refreshToken) {
        // If no refresh token, redirect to login page
        return <Navigate to="/login" replace />;
    }

    // Otherwise, render the child routes
    return <Outlet />;
};

export default ProtectedRoute;
