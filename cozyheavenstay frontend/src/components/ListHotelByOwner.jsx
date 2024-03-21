import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import HotelService from '../services/HotelService'; 
import { faEnvelope, faPhone } from '@fortawesome/free-solid-svg-icons';
import { faFacebook, faInstagram, faTwitter } from '@fortawesome/free-brands-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import chsLogo from './chs.jpeg';
import { Row, Container, Col } from 'react-bootstrap';

const ListHotelByOwner = () => {
  const [hotels, setHotels] = useState([]);
  const ownerId = localStorage.getItem('ownerId');

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
    <div className='hotel-container'>
       <header className="header bg-dark text-white py-4 d-flex justify-content-between align-items-center">
        <div className="d-flex align-items-center">
          <Link className="navbar-brand d-flex align-items-center" to="/owner-dashboard">
            <img src={chsLogo} alt="Company Logo" height="40" className="me-2" />
            <span className="fw-bold">Cozy Heaven Stay</span>
          </Link>
        </div>
        <div className="text-center">
          <h1 className="header-title m-0">Hotels Owned By You</h1>
        </div>
        <div></div> 
        <div></div> {/* This empty div is for spacing*/}
      </header>

      

      <div className='row row-cols-md-3 g-4 mt-4'>
        {hotels.map((hotel, index) => (
          <div className='col' key={index}>
            <div className='card h-100' style={{ background: 'linear-gradient(to right, #f0f0f0, #d9d9d9)' }}>
              <div className='card-body'>
                <h5 className='card-title'>{hotel.hotelName}</h5>
                <p className='card-text'>Location: {hotel.location}</p>
                <p className='card-text'>Rating: {hotel.rating}</p>
                <p className='card-text'>Amenities: {hotel.amenities}</p>
                
                <Link to={`/hotel/${hotel.hotelId}`} className="btn btn-primary">View Hotel</Link>
                
              </div>
            </div>
          </div>
        ))}
      </div>
      <footer className="login-footer mt-4" style={{ backgroundColor: '#343a40', color: '#ffffff', padding: '20px 0', marginTop: 'auto' }}>
        <Container>
          <Row>
            <Col md={4} className="text-center">
              <div style={{ marginBottom: '20px' }}>
                <h5>About Us</h5>
                <p>We are a company committed to providing the best accommodation services to our customers.</p>
              </div>
            </Col>
            <Col md={4} className="text-center">
              <div style={{ marginBottom: '20px' }}>
                <h5>Contact Us</h5>
                <p><FontAwesomeIcon icon={faPhone} /> +1 234 567 890</p>
                <p><FontAwesomeIcon icon={faEnvelope} /> info@example.com</p>
              </div>
            </Col>
            <Col md={4} className="text-center">
              <div style={{ marginBottom: '20px' }}>
                <h5>Follow Us</h5>
                <p>
                  <a href="https://www.facebook.com/example" target="_blank" rel="noopener noreferrer"><FontAwesomeIcon icon={faFacebook} /></a>&nbsp;&nbsp;&nbsp;
                  <a href="https://www.twitter.com/example" target="_blank" rel="noopener noreferrer"><FontAwesomeIcon icon={faTwitter} /></a>&nbsp;&nbsp;&nbsp;
                  <a href="https://www.instagram.com/example" target="_blank" rel="noopener noreferrer"><FontAwesomeIcon icon={faInstagram} /></a>
                </p>
              </div>
            </Col>
          </Row>
        </Container>
        <div className="text-center mt-3" style={{ borderTop: '1px solid #fff', paddingTop: '10px' }}>
          <p>© 2024 Cozy Heaven Stay. All rights reserved.</p>
        </div>
      </footer>
      
    </div>
  );
};

export default ListHotelByOwner;

