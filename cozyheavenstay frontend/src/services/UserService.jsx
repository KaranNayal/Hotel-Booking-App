import axios from "axios";

const BASE_REST_API_URL = "http://localhost:8080/api";

class UserService {
  getAllUsers() {
    return axios.get(BASE_REST_API_URL + "/users/getallusers");
  }
  addUser(user) {
    return axios.post(BASE_REST_API_URL + "/authenticate/register", user);
  }

  getUserById(id) {
    return axios.get(BASE_REST_API_URL + "/users/getuserbyid/" + id);
  }

  updateUser(id, user) {
    return axios.put(BASE_REST_API_URL + "/users/update/" + id, user);
  }

  deleteUser(id) {
    const token = localStorage.getItem("token");
    if (!token) {
      throw new Error("Token not found");
    }
    return axios.delete(BASE_REST_API_URL + "/users/delete/" + id, {
      headers: { Authorization: `Bearer ${token}` },
    });
  }
  fetchIds() {
    return axios.get(BASE_REST_API_URL + "/authenticate/fetchIds");
  }
  getbyUsername(userName) {
    return axios.get(BASE_REST_API_URL + "/users/getbyusername/" + userName);
  }

  getUserReservations(userId) {
    return axios.get(BASE_REST_API_URL + `/users/${userId}/reservations`);
  }
}

export default new UserService();
