import { Navigate, useLocation } from "react-router-dom"

import { getToken } from "../api/auth.js"

const ProtectedRoute = ({children}) => {
    const location = useLocation()

    if (!getToken()) {
        return <Navigate to="/login" replace state={{from: location}} />
    }

    return children
}

export default ProtectedRoute