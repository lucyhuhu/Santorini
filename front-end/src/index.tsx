import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'

/**
 * The entrance of React app, renders the application
 * at the HTML element with id="root".
 */
const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
)
/**
 * Render the application, App is the component we define in App.tsx.
 */
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
)
