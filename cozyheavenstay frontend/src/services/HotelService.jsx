import axios from 'axios';

const BASE_REST_API_URL = 'http://localhost:8080/api/hotels';

class HotelService {
  getAllHotels() {
    return axios.get(`${BASE_REST_API_URL}/getallhotels`);
  }

  getHotelById(id) {
    const token = localStorage.getItem('token');
    if (!token) {
      throw new Error('Token not found');
    }
    return axios.get(`${BASE_REST_API_URL}/gethotelbyid/${id}`, {
      headers: { Authorization: `Bearer ${token}` },
    });
  }

  addHotel(hotel) {
    const token = localStorage.getItem('token');
    if (!token) {
      throw new Error('Token not found');
    }
    return axios.post(`${BASE_REST_API_URL}/add`, hotel, {
      headers: { Authorization: `Bearer ${token}` },
    });
  }

  updateHotel(id, hotel) {
    const token = localStorage.getItem('token');
    if (!token) {
      throw new Error('Token not found');
    }
    return axios.put(`${BASE_REST_API_URL}/updatehotel/${id}`, hotel, {
      headers: { Authorization: `Bearer ${token}` },
    });
  }

  deleteHotel(id) {
    const token = localStorage.getItem('token');
    if (!token) {
      throw new Error('Token not found');
    }
    return axios.delete(`${BASE_REST_API_URL}/delete/${id}`, {
      headers: { Authorization: `Bearer ${token}` },
    });
  }

  getHotelsByLocation(city) {
    return axios.get(`${BASE_REST_API_URL}/getlocation/${city}`);
  }

  getHotelsByAmenities(amenities) {
    return axios.get(`${BASE_REST_API_URL}/getamenities/${amenities}`);
  }

  getHotelsWithRoomType(roomType) {
    return axios.get(`${BASE_REST_API_URL}/getroomtype/${roomType}`);
  }

  getHotelsByOwnerId(ownerId) {
    return axios.get(`${BASE_REST_API_URL}/gethotelsbyowner/${ownerId}`);
  }
}

export default new HotelService();
