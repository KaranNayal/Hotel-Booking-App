import React, { useEffect, useState } from 'react';
import HotelService from '../services/HotelService';
import { Link } from 'react-router-dom';

const ListHotel = () => {
  const [hotels, setHotels] = useState([]);
  //const navigate = useNavigate();

  const deleteHotel = (id) => {
    console.log("Delete hotel handler fired. ID value received =", id);
    HotelService.deleteHotel(id)
      .then((response) => {
        console.log("Response received from delete API:", response.data);
        fetchAllHotels(); // calling function to display updated list after deleting
      })
      .catch(error => {
        console.error("Error received from delete API:", error);
      });
  };

  const fetchAllHotels = () => {
    console.log("Use Effect...");

    HotelService.getAllHotels()
      .then((response) => {
        console.log('Response received from API:', response.data);
        setHotels(response.data);
        console.log("State variable assigned with response data...");
      })
      .catch(error => {
        console.error("Error received from fetch all hotels API:", error);
      });
  };

  useEffect(() => {
    console.log("useEffect hook fired");
    fetchAllHotels();
  }, []);

  return (
    <div className='container'>
      <h2 className='text-center'>Hotel Data</h2>
      <Link to="/addhotel" className='btn btn-dark'>Add Hotel</Link>
      <table className="table table-bordered table-hover table-light">
        <thead>
          <tr className='table-dark'>
            <th>Hotel ID</th>
            <th>Hotel Name</th>
            <th>Location</th>
            <th>Amenities</th>
            <th>Rating</th>
            <th>Update</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          {
            hotels.map((hotel, key) => (
              <tr key={key}>
                <td>{hotel.hotelId}</td>
                <td>{hotel.hotelName}</td>
                <td>{hotel.location}</td>
                <td>{hotel.amenities}</td>
                <td>{hotel.rating}</td>
                <td><Link to={`/updatehotel/${hotel.hotelId}`} className='btn btn-success'>Update</Link></td>
                <td><button className='btn btn-danger' onClick={() => deleteHotel(hotel.hotelId)}>Delete</button></td>
              </tr>
            ))
          }
        </tbody>
      </table>
    </div>
  );
};

export default ListHotel;
