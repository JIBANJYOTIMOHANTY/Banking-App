import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import SignIn from './components/SignIn'
import { BrowserRouter, Route } from 'react-router-dom'
import SignUp from './components/SignUp'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <div>
        <BrowserRouter>
          <Routes>
            <Route path = '/' element = {<SignIn/>} />
            <Route path='/signup' element = {<SignUp />} />
          </Routes>
        </BrowserRouter>
      </div>
    </>
  )
}

export default App
