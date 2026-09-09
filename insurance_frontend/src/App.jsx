import './App.css'
import ClaimSubmitView from './components/ClaimSubmitView'
import CoverageView from './components/CoverageView'
import { Route, Routes } from "react-router-dom"

function App() {
  return (
    <div className='app'>
      <Routes>
        <Route path="/" element={<CoverageView/>}></Route>
        <Route path="/submit-claim" element={<ClaimSubmitView/>}></Route>
      </Routes>
    </div>
  )
}

export default App