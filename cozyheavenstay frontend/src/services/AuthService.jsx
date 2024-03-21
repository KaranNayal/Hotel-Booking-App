import axios from 'axios';

const BASE_REST_API_URL = 'http://localhost:8080/api/authenticate';

class AuthService {
  register(user) {
    return axios.post(`${BASE_REST_API_URL}/register`, user);
  }

  async login(user) {
    const response = await axios.post(`${BASE_REST_API_URL}/login`, user);
    const token = response.data.accessToken;
    localStorage.setItem('token', token);
    return response;
  }
 
  async fetchIds() {
    try {
      const token = localStorage.getItem('token');
      if (!token) {
        throw new Error('Token not found');
      }
      const response = await axios.get(`${BASE_REST_API_URL}/fetchIds`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      return response.data; 
    } catch (error) {
      throw new Error('Error fetching user IDs: ' + error.message);
    }
  }
  fetchOwnerId(config) {
    const token = localStorage.getItem('token');
    if (!token) {
      throw new Error('Token not found');
    }
    return axios.get(`${BASE_REST_API_URL}/fetchIds`, { ...config, headers: { Authorization: `Bearer ${token}` } });
  }
  fetchUserData() {
    const token = localStorage.getItem('token');
    if (!token) {
      throw new Error('Token not found');
    }
    return axios.get(`${BASE_REST_API_URL}/fetchIds`, {
      headers: { Authorization: `Bearer ${token}` },
    });
  }
  logout() {
    localStorage.removeItem('token');
  }

  async registerOwner(owner) {
    const token = localStorage.getItem('token');
    if (!token) {
      const confirmLogin = window.confirm('Please login to register as owner. Would you like to login now?');
      if (confirmLogin) {
        window.location.href = '/login';
      }
      return; // Exit the function if user cancels login
    }

    try {
      const response = await axios.post(`${BASE_REST_API_URL}/registerowner`, owner, {
        headers: { Authorization: `Bearer ${token}` }
      });
      alert('owner added successfully');
      return response.data; // Return the response data if registration is successful
    

    } catch (error) {
      console.error('Error occurred while registering owner:', error);
      throw new Error('Registration failed. Please try again later.'); // Throw error for unsuccessful registration
    }
  }


  registerAdmin(admin) {
    const token = localStorage.getItem('token');
    if (!token) {
      alert('Please login to register as admin.');
      return Promise.reject(new Error('User not logged in')); // Return a rejected promise
    }
    return axios.post(`${BASE_REST_API_URL}/registeradmin`, admin, {
      headers: { Authorization: `Bearer ${token}` }
    })
    .then(response => {
      alert('Admin registered successfully'); // Show alert for successful admin registration
      return response.data; // Return the response data
    })
    .catch(error => {
      console.error('Error occurred while registering admin:', error);
      alert('Registration failed. Please try again later.'); // Show alert for unsuccessful admin registration
      throw error; // Rethrow the error
    });
  }
  
}

export default new AuthService();
