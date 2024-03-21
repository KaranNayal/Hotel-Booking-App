import axios from "axios";

const BASE_REST_API_URL = "http://localhost:8080/api/admin";

class AdminService {
 
    getAllAdmins() {
    const token = localStorage.getItem('token');
    return axios.get(BASE_REST_API_URL + "/getalladmin", {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
  }
  getAdministratorById(id) {
    return axios.get(BASE_REST_API_URL + "/admin/" + id);
  }

  updateAdministrator(id, administrator) {
    return axios.put(BASE_REST_API_URL + "/updateadmin/" + id, administrator);
  }

  deleteAdministrator(id) {
    return axios.delete(BASE_REST_API_URL + "/deleteadmin/" + id);
  }


}

export default new AdminService();