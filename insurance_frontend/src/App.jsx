import './App.css'
import ClaimSubmitView from './components/ClaimSubmitView'
import ClaimHistoryView from './components/ClaimHistoryView'
import CoverageView from './components/CoverageView'
import { Route, Routes } from "react-router-dom"

function App() {
  return (
    <div className='app'>
      <Routes>
        <Route path="/" element={<CoverageView/>}></Route>
        <Route path="/submit-claim" element={<ClaimSubmitView/>}></Route>
        <Route path="/claim-history" element={<ClaimHistoryView/>}></Route>
      </Routes>
    </div>
  )
}

export default App