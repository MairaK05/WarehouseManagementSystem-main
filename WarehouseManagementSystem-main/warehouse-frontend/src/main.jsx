import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { Provider } from 'react-redux';
import { configureStore } from '@reduxjs/toolkit';
import loginReducer from './Slices/userSlice';
import { persistStore, persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage';

export const store = configureStore({
  reducer: {
    login: loginReducer
  },
});

createRoot(document.getElementById('root')).render(
  <Provider store={store}>
  <StrictMode>
    <App />
  </StrictMode>
  </Provider>
)
