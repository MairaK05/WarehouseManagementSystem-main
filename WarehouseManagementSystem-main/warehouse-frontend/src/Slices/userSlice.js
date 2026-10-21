import { createSlice } from '@reduxjs/toolkit';


const usersSlice = createSlice({
    name: 'users',
    initialState: {
        user: null,
        token: null,
        loggedIn: false,
        loading: false,
        err: null,
    },
    reducers: {
        userLogin: state => {
            state.loading = true;
            state.err = null;
        },
        loginSuccessful: (state, action) => {
            state.loading = false;
            state.loggedIn = true;
            state.token = action.payload.token;
            state.err = null;
            state.user = action.payload.user;

        },
        loginUnsuccessful: (state, action) => {
            state.loading = false;
            state.err = action.payload.err;
            state.loggedIn = false;
        },
        userLogout: state => {
            state.user = null;
            state.token = null;
            state.loggedIn = false;
            state.err = null;
        },
    }
});

export const {
    userLogin, userLogout, loginSuccessful, loginUnsuccessful
} = usersSlice.actions;

export default usersSlice.reducer;