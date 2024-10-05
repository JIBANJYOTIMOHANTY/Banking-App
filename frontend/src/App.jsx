import { useState } from 'react'
import './App.css'
import SignIn from './components/SignIn'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import SignUp from './components/SignUp'
import HomePage from './components/HomePage'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <div>
        <BrowserRouter>
          <Routes>
            <Route path = '/' element = {<SignIn/>} />
            <Route path='/signup' element = {<SignUp />} />
            <Route path = '/home' element = {<HomePage />} />
          </Routes>
        </BrowserRouter>
      </div>
    </>
  )
}

export default App
