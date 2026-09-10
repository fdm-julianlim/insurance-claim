import './App.css'
import ClaimSubmitView from './components/ClaimSubmitView'
import ClaimHistoryView from './components/ClaimHistoryView'
import CoverageView from './components/CoverageView'
import LoginView from './components/LoginView'
import ProtectedRoute from './components/ProtectedRoute'
import { Route, Routes } from "react-router-dom"

function App() {
  return (
    <div className='app'>
      <Routes>
        <Route path="/login" element={<LoginView/>}></Route>
        <Route path="/" element={<ProtectedRoute><CoverageView/></ProtectedRoute>}></Route>
        <Route path="/submit-claim" element={<ProtectedRoute><ClaimSubmitView/></ProtectedRoute>}></Route>
        <Route path="/claim-history" element={<ProtectedRoute><ClaimHistoryView/></ProtectedRoute>}></Route>
      </Routes>
    </div>
  )
}

export default App