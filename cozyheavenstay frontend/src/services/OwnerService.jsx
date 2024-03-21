import axios from "axios";

const BASE_REST_API_URL = "http://localhost:8080/api";

class OwnerService {
  getAllHotelOwners() {
    const token = localStorage.getItem('token');
    return axios.get(BASE_REST_API_URL + "/hotel-owners/getallowner", {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
  }

  addHotelOwner(hotelOwner) {
    return axios.post(BASE_REST_API_URL + "/hotel-owners/register", hotelOwner);
  }

  getHotelOwnerById(id) {
    return axios.get(BASE_REST_API_URL + "/hotel-owners/getowner/" + id);
  }

  updateHotelOwner(id, hotelOwner) {
    const token = localStorage.getItem('token');
    return axios.put(BASE_REST_API_URL + "/hotel-owners/updateowner/" + id, hotelOwner, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
  }

  deleteHotelOwner(id) {
    return axios.delete(BASE_REST_API_URL + "/hotel-owners/deleteowner/" + id);
  }
}

export default new OwnerService();