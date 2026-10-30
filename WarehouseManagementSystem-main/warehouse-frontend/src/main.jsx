import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { Provider } from 'react-redux';
import { configureStore } from '@reduxjs/toolkit';
import loginReducer from './Slices/userSlice';

import { vehicleSlice } from './Slices/entitySlice'; //slices
import { employeeSlice } from './Slices/entitySlice';
import { auditSlice } from './Slices/entitySlice';

import { vehicleLoadingSlice } from './Slices/entityLoadingSlice'; //slice loaders
import { employeeLoadingSlice } from './Slices/entityLoadingSlice';
import { auditLoadingSlice } from './Slices/entityLoadingSlice';

export const store = configureStore({
  reducer: {
    login: loginReducer,
    vehicles: vehicleSlice.reducer, 
    vehicleLoading: vehicleLoadingSlice.reducer,
    employeeLoading: employeeLoadingSlice.reducer,
    auditLoading: auditLoadingSlice.reducer,
    employees: employeeSlice.reducer,
    logs: auditSlice.reducer
  },
});

createRoot(document.getElementById('root')).render(
  <Provider store={store}>
  <StrictMode>
    <App />
  </StrictMode>
  </Provider>
)
