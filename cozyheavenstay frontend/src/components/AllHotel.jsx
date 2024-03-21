import React, { useEffect, useState } from 'react';
import HotelService from '../services/HotelService';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import Select from 'react-select';
import {
  Container,
  Row,
  Col,
} from "react-bootstrap";
import { Link } from "react-router-dom";
import {
  faEnvelope,
  faPhone,

} from "@fortawesome/free-solid-svg-icons";
import {
  faFacebook,
  faInstagram,
  faTwitter,
} from "@fortawesome/free-brands-svg-icons";
import chsLogo from "./chs.jpeg";
const AllHotel = () => {
  const [hotels, setHotels] = useState([]);
  const [filterLocation, setFilterLocation] = useState('');
  const [filterRating, setFilterRating] = useState('');
  const [selectedAmenities, setSelectedAmenities] = useState([]);
  const [allAmenities, setAllAmenities] = useState([]);
  const [filteredHotels, setFilteredHotels] = useState([]);

  useEffect(() => {
    fetchAllHotels();
    fetchAllAmenities();
  }, []);

  const fetchAllHotels = () => {
    HotelService.getAllHotels()
      .then((response) => {
        setHotels(response.data);
        setFilteredHotels(response.data);
      })
      .catch(error => {
        console.error("Error received from fetch all hotels API:", error);
      });
  };

  const fetchAllAmenities = () => {
    // Mocking amenities data
    const amenitiesData = ['Swimming Pool', 'Gym', 'Spa', 'Restaurant'];
    setAllAmenities(amenitiesData.map(amenity => ({ value: amenity, label: amenity })));
  };

  const deleteHotel = (id) => {
    HotelService.deleteHotel(id)
      .then(() => {
        fetchAllHotels();
      })
      .catch(error => {
        console.error("Error received from delete API:", error);
      });
  };

  const applyFilter = () => {
    let filtered = hotels.filter(hotel => {
      let matchLocation = true;
      let matchRating = true;
      let matchAmenities = true;

      if (filterLocation.trim() !== '') {
        matchLocation = hotel.location.toLowerCase().includes(filterLocation.toLowerCase());
      }

      if (filterRating !== '') {
        matchRating = hotel.rating >= filterRating;
      }

      if (selectedAmenities.length > 0) {
        matchAmenities = selectedAmenities.every(amenity =>
          hotel.amenities.toLowerCase().includes(amenity.value.toLowerCase())
        );
      }

      return matchLocation && matchRating && matchAmenities;
    });

    setFilteredHotels(filtered);
  };

  const clearFilters = () => {
    setFilterLocation('');
    setFilterRating('');
    setSelectedAmenities([]);
    setFilteredHotels(hotels);
  };

  return (
    <div className='hotel-container'>
      

      <header className="header bg-dark text-white py-4 d-flex justify-content-between align-items-center">
        <div className="d-flex align-items-center">
          <Link className="navbar-brand d-flex align-items-center" to="/admin-dashboard">
            <img src={chsLogo} alt="Company Logo" height="40" className="me-2" />
            <span className="fw-bold">Cozy Heaven Stay</span>
          </Link>
        </div>
        <div className="text-center">
          <h1 className="header-title m-0">Hotel  Data</h1>
        </div>
        <div></div>
        <div></div>{/* These empty div is for spacing*/}
      </header>
      <div className='row mt-4'>
        <div className='col-md-9'>
        </div>
        <div className='col-md-3 mt-4'>
          <div className='row'>
            <div className='col-md-6'>
              <button className='btn btn-secondary btn-sm' onClick={clearFilters}>Clear Filters</button>
            </div>
            <div className='col-md-6'>
              <button className='btn btn-primary btn-sm' onClick={applyFilter}>Apply Filter</button>
            </div>
          </div>
        </div>
      </div>
      <div className='row mt-4'>
        <div className='col-md-3 '>
          <input
            type='text'
            className='form-control'
            placeholder='Location'
            value={filterLocation}
            onChange={(e) => setFilterLocation(e.target.value)}
          />
        </div>
        <div className='col-md-3 '>
          <select
            className='form-control'
            value={filterRating}
            onChange={(e) => setFilterRating(e.target.value)}
          >
            <option value=''>Select Rating</option>
            <option value='1'>1</option>
            <option value='2'>2</option>
            <option value='3'>3</option>
            <option value='4'>4</option>
            <option value='5'>5</option>
          </select>
        </div>
        <div className='col-md-6'>
          <Select
            isMulti
            className='basic-multi-select'
            classNamePrefix='select amenities'
            options={allAmenities}
            value={selectedAmenities}
            onChange={setSelectedAmenities}
            placeholder="Select Amenities"

          />
        </div>
      </div>
      <div className='row row-cols-md-3 g-4 mt-4'>
        {filteredHotels.map((hotel, key) => (
          <div className='col' key={key}>
            <div className='card h-100' style={{ background: 'linear-gradient(to right, #f0f0f0, #d9d9d9)' }}>
              <div className='card-body'>
                <h5 className='card-title'>{hotel.hotelName}</h5>
                <p className='card-text'>Location: {hotel.location}</p>
                <p className='card-text'>Rating: {hotel.rating}</p>
                <p className='card-text'>Amenities: {hotel.amenities}</p>
                <p className='card-text'>Owner Id: {hotel.ownerId}</p>
                <div className='d-flex justify-content-between align-items-center'>
                  <button className='btn btn-sm btn-outline-danger' onClick={() => deleteHotel(hotel.hotelId)}>
                    <FontAwesomeIcon icon={faTrash} /> Delete
                  </button>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
      <footer
        className="login-footer mt-4"
        style={{
          backgroundColor: "#343a40",
          color: "#ffffff",
          padding: "20px 0",
        }}
      >
        <Container>
          <Row>
            <Col md={4} className="text-center">
              <div style={{ marginBottom: "20px" }}>
                <h5>About Us</h5>
                <p>
                  We are a company committed to providing the best accommodation
                  services to our customers.
                </p>
              </div>
            </Col>
            <Col md={4} className="text-center">
              <div style={{ marginBottom: "20px" }}>
                <h5>Contact Us</h5>
                <p>
                  <FontAwesomeIcon icon={faPhone} /> +1 234 567 890
                </p>
                <p>
                  <FontAwesomeIcon icon={faEnvelope} /> info@example.com
                </p>
              </div>
            </Col>
            <Col md={4} className="text-center">
              <div style={{ marginBottom: "20px" }}>
                <h5>Follow Us</h5>
                <p>
                  <a
                    href="https://www.facebook.com/example"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    <FontAwesomeIcon icon={faFacebook} />
                  </a>
                  &nbsp;&nbsp;&nbsp;
                  <a
                    href="https://www.twitter.com/example"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    <FontAwesomeIcon icon={faTwitter} />
                  </a>
                  &nbsp;&nbsp;&nbsp;
                  <a
                    href="https://www.instagram.com/example"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    <FontAwesomeIcon icon={faInstagram} />
                  </a>
                </p>
              </div>
            </Col>
          </Row>
        </Container>
        <div
          className="text-center mt-3"
          style={{ borderTop: "1px solid #fff", paddingTop: "10px" }}
        >
          <p>© 2024 Cozy Heaven Stay. All rights reserved.</p>
        </div>
      </footer>
    </div>
  );
};

export default AllHotel;
