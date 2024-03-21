import React, { useEffect, useState } from 'react';
import RoomService from '../services/RoomService';
import { Link } from 'react-router-dom';

const ListRoom = () => {
  const [rooms, setRooms] = useState([]);

  const deleteRoom = (id) => {
    console.log("Delete room handler fired. ID value received =", id);
    RoomService.deleteRoom(id)
      .then((response) => {
        console.log("Response received from delete API:", response.data);
        fetchAllRooms(); // calling function to display updated list after deleting
      })
      .catch(error => {
        console.error("Error received from delete API:", error);
      });
  };

  const fetchAllRooms = () => {
    console.log("Use Effect...");

    RoomService.getAllRooms()
      .then((response) => {
        console.log('Response received from API:', response.data);
        setRooms(response.data);
        console.log("State variable assigned with response data...");
      })
      .catch(error => {
        console.error("Error received from fetch all rooms API:", error);
      });
  };

  useEffect(() => {
    console.log("useEffect hook fired");
    fetchAllRooms();
  }, []);

  return (
    <div className='container'>
      <h2 className='text-center'>Room Data</h2>
      <table className="table table-bordered table-hover table-light">
        <thead>
          <tr className='table-dark'>
            <th>Room ID</th>
            <th>Room Number</th>
            <th>Bed Type</th>
            <th>Availability</th>
            <th>Max Occupancy</th>
            <th>AC Status</th>
            <th>Base Fare</th>
            <th>Room Type</th>
            <th>Hotel ID</th>
            <th>Update</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          {
            rooms.map((room, key) => (
              <tr key={key}>
                <td>{room.roomId}</td>
                <td>{room.roomNumber}</td>
                <td>{room.bedType}</td>
                <td>{room.availability}</td>
                <td>{room.maxOccupancy}</td>
                <td>{room.acStatus ? 'Yes' : 'No'}</td>
                <td>{room.baseFare}</td>
                <td>{room.roomType}</td>
                <td>{room.hotelId}</td>
                <td><Link to={`/updateroom/${room.roomId}`} className='btn btn-success'>Update</Link></td>
                <td><button className='btn btn-danger' onClick={() => deleteRoom(room.roomId)}>Delete</button></td>
              </tr>
            ))
          }
        </tbody>
      </table>
    </div>
  );
};

export default ListRoom;
