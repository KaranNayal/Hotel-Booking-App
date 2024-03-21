import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faStar } from '@fortawesome/free-solid-svg-icons';
import HotelService from '../services/HotelService';
import RoomService from '../services/RoomService'; // Import RoomService
import { faFacebook, faInstagram, faTwitter } from '@fortawesome/free-brands-svg-icons';
import { faEnvelope, faPhone } from '@fortawesome/free-solid-svg-icons';
import chsLogo from "./chs.jpeg";
import { Container, Row, Col } from 'react-bootstrap';

const HotelDetail = () => {
  const { id } = useParams(); // Get hotel ID from URL params
  const [hotel, setHotel] = useState(null);
  const [rooms, setRooms] = useState([]);

  useEffect(() => {
    fetchHotelDetails();
    fetchRoomsByHotelId(); // Fetch rooms when component mounts
  }, []);

  const fetchHotelDetails = async () => {
    try {
      const response = await HotelService.getHotelById(id);
      setHotel(response.data);
    } catch (error) {
      console.error('Error fetching hotel details:', error);
    }
  };

  const fetchRoomsByHotelId = async () => {
    try {
      const response = await RoomService.getRoomsByHotelId(id);
      setRooms(response.data);
    } catch (error) {
      console.error('Error fetching rooms by hotel ID:', error);
    }
  };

  if (!hotel) {
    return <div>Loading...</div>;
  }

  return (
    <div className="detail-container">
       <header className="header bg-dark text-white py-4 d-flex justify-content-between align-items-center">
        <div className="d-flex align-items-center">
          <Link className="navbar-brand d-flex align-items-center" to="/owner-dashboard">
            <img src={chsLogo} alt="Company Logo" height="40" className="me-2" />
            <span className="fw-bold">Cozy Heaven Stay</span>
          </Link>
        </div>
       
        <div></div> {/* This empty div is for spacing*/}
      </header>
      
      <div className="row justify-content-center mt-4">
    <div className="col-md-8">
      <div className="card" style={{ backgroundImage: 'linear-gradient(135deg, #ffffff, #cccccc)' }}> 
        <div className="card-body" style={{ padding: '30px' }}> 
          <h2 className="card-title">{hotel.hotelName}</h2>
          <p className="card-text">
            <FontAwesomeIcon icon={faStar} /> Rating: {hotel.rating}
          </p>
          <p className="card-text">
            <strong>Location:</strong> {hotel.location}
          </p>
          <p className="card-text">
            <strong>Amenities:</strong> {hotel.amenities}
          </p>
          <div className="text-center">
            <Link to={`/hotel/${id}/add-room`} class="btn btn-primary mt-3">
              Add Room
            </Link>
          </div>
        </div>
      </div>
    </div>
  </div>


      <footer className="login-footer" style={{ backgroundColor: '#343a40', color: '#ffffff', padding: '20px 0', marginTop: 'auto', position: 'fixed', bottom: 0, width: '100%' }}>
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

export default HotelDetail;
