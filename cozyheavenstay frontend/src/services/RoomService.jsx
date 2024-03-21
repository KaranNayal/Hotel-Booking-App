import axios from "axios";

const BASE_REST_API_URL = "http://localhost:8080/api";

class RoomService {
    getAllRooms() {
        return axios.get(BASE_REST_API_URL + "/rooms/getallrooms");
    }

    addRoom(room) {
        const token = localStorage.getItem('token');
        return axios.post(BASE_REST_API_URL + "/rooms/save", room, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });
    }
    getRoomByRoomNumberAndHotelId(roomNumber, hotelId) {
        return axios.get(BASE_REST_API_URL + "/rooms/number/" + roomNumber + "/hotel/" + hotelId);
    }

    updateRoom(id, room) {
        return axios.put(BASE_REST_API_URL + "/rooms/update/" + id, room);
    }

    deleteRoom(id) {
        return axios.delete(BASE_REST_API_URL + "/rooms/delete/" + id);
    }
    getRoomByRoomNumber(roomNumber) {
        return axios.get(BASE_REST_API_URL + "/rooms/number/" + roomNumber);
    }
    getRoomsByHotelId(hotelId) {
        return axios.get(BASE_REST_API_URL + "/rooms/getbyhotelid/" + hotelId);
    }
    
    getLastAddedRoom(id) {
        return axios.get(BASE_REST_API_URL + "/rooms/lastAdded/" + id);
    }
    getRoomById(id) {
        return axios.get(BASE_REST_API_URL + "/rooms/getbyid/" + id);
    }
    makeReservation(reservationRequestDTO) {
        return axios.post(BASE_REST_API_URL + "/reservations/make", reservationRequestDTO);
    }

}

export default new RoomService();

