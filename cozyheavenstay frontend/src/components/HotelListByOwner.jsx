import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import HotelService from '../services/HotelService'; // Make sure this path is correct

const ListHotelByOwner = () => {
  const [hotels, setHotels] = useState([]);
  const ownerId = localStorage.getItem('ownerId'); // Fetch ownerId from local storage

  const fetchHotelsByOwner = () => {
    if (!ownerId) {
      console.error('Owner ID not found in local storage');
      return;
    }
    HotelService.getHotelsByOwnerId(ownerId)
      .then((response) => {
        console.log('Response received from getHotelsByOwnerId API:', response.data);
        setHotels(response.data);
      })
      .catch(error => {
        console.error("Error received from getHotelsByOwnerId API:", error);
      });
  };

  useEffect(() => {
    fetchHotelsByOwner();
  }, [ownerId]);

  return (
    <div className='container'>
      <h2 className='text-center'>Hotels Owned By You</h2>
      <div className="row">
        {
          hotels.map((hotel, index) => (
            <div key={index} className="col-md-4 mb-4">
              <div className="card" style={{ width: '25rem', height: '20rem' }}>
                <div className="card-body">
                  <h5 className="card-title">{hotel.hotelName}</h5>
                  <p className="card-text">{hotel.location}</p>
                  <p className="card-text">Amenities: {hotel.amenities}</p>
                  <p className="card-text">Rating: {hotel.rating}</p>
                  {/* Add more hotel details as needed */}
                  <Link to={`/hotel/${hotel.hotelId}`} className="btn btn-primary">View Hotel</Link>
                </div>
              </div>
            </div>
          ))
        }
      </div>
    </div>
  );
};

export default ListHotelByOwner;
