import React, { useState } from 'react';
import { Container, Row, Col, Form, Button, Card, Carousel, Navbar, Nav } from 'react-bootstrap';
import { FaUser, FaSignInAlt, FaSwimmingPool, FaWifi, FaBicycle, FaUtensils, FaBed, FaParking, FaSmokingBan, FaDog, FaSearch } from 'react-icons/fa';
import { NavLink } from 'react-router-dom';
import { faFacebook, faInstagram, faTwitter } from '@fortawesome/free-brands-svg-icons';
import { faEnvelope, faPhone } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {  animated } from 'react-spring';
import HotelService from '../services/HotelService';
import chsLogo from "./chs.jpeg";

function HomePage() {
  const [checkinDate, setCheckinDate] = useState('');
  const [checkoutDate, setCheckoutDate] = useState('');
  const [roomType, setRoomType] = useState('standard');
  const [hoveredCard, setHoveredCard] = useState(null);
  const [city, setCity] = useState('');
  const [searchedHotels, setSearchedHotels] = useState([]);

  const handleSearch = () => {
    console.log('Searching for hotels...');
    console.log('Checkin Date:', checkinDate);
    console.log('Checkout Date:', checkoutDate);
    console.log('Location:', city);
    console.log('Room Type:', roomType);

    // Calling  the HotelService method to search hotels based on location
    HotelService.getHotelsByLocation(city)
      .then(response => {
        console.log('Searched hotels:', response.data);
        setSearchedHotels(response.data); 
      })
      .catch(error => {
        console.error('Error searching hotels:', error);
      });
  };
  const handleBookNow = () => {
    window.alert('Please login to book this hotel.');
  };
  

  return (
    <div className="home-page">
     <Navbar bg="dark" variant="dark" expand="lg">
        <Container>
        <a className="navbar-brand" href="/">
            <img src={chsLogo} alt="Company Logo" className="logo-size" />
        </a>
          <Navbar.Brand href="#home">CozyHeavenStay</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="mr-auto">
              <NavLink to="/aboutus" className="nav-link">
                About Us
              </NavLink>
            </Nav>
            <Nav className="ml-auto">
              <NavLink to="/adduser" className="nav-link">
                <FaUser /> Register
              </NavLink>
              <NavLink to="/login" className="nav-link">
                <FaSignInAlt /> Login
              </NavLink>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
      <Container className="mt-4">
        <Row>
          <Col md={12}>
            <h1 className="text-center text-primary mb-4">Welcome to CozyHeavenStay</h1>
            <h2 className="text-center mb-4">Find Your Perfect Hotel</h2>
          </Col>
        </Row>
        <Row>
          <Col md={12}>
            <Form>
              <Row>
                <Col md={3}>
                  <Form.Group controlId="checkinDate">
                    <Form.Label>Check-in Date</Form.Label>
                    <Form.Control type="date" value={checkinDate} onChange={(e) => setCheckinDate(e.target.value)} />
                  </Form.Group>
                </Col>
                <Col md={3}>
                  <Form.Group controlId="checkoutDate">
                    <Form.Label>Check-out Date</Form.Label>
                    <Form.Control type="date" value={checkoutDate} onChange={(e) => setCheckoutDate(e.target.value)} />
                  </Form.Group>
                </Col>
                <Col md={3}>
                  <Form.Group controlId="location">
                    <Form.Label>Location</Form.Label>
                    <Form.Control type="text" value={city} onChange={(e) => setCity(e.target.value)} />
                  </Form.Group>
                </Col>
                <Col md={3}>
                  <Form.Group controlId="roomType">
                    <Form.Label>Room Type</Form.Label>
                    <Form.Control as="select" value={roomType} onChange={(e) => setRoomType(e.target.value)}>
                      <option>Standard</option>
                      <option>Deluxe</option>
                      <option>Suite</option>
                    </Form.Control>
                  </Form.Group>
                </Col>
              </Row>
              <Row>
                <Col md={12} className="text-center">
                  <Button variant="primary" onClick={handleSearch}>
                    <FaSearch /> Search
                  </Button>
                </Col>
              </Row>
            </Form>
          </Col>
        </Row>
        <Container className="mt-4">
        <Row xs={1} md={2} lg={3} className="g-4">
  {searchedHotels.map(hotel => (
    <Col key={hotel.hotelId}>
      <Card>
        <Card.Img variant="top" src="https://cdn.pixabay.com/photo/2016/04/15/11/48/hotel-1330850_1280.jpg" style={{ width: '100%', height: 'auto' }} />
        <Card.Body>
          <Card.Title>{hotel.name}</Card.Title>
          <Card.Text>
            <strong>Hotel Id:</strong> {hotel.hotelId}<br />
            <strong>Name:</strong> {hotel.hotelName}<br />
            <strong>Location:</strong> {hotel.location}<br />
            <strong>Rating:</strong> {hotel.rating}<br />
            <strong>Amenities:</strong> {hotel.amenities}<br />
          </Card.Text>
          <Button variant="primary" onClick={() => handleBookNow(hotel.hotelId)}>Book Now</Button>
        </Card.Body>
      </Card>
    </Col>
  ))}
</Row>

      </Container>
        <Row className="mt-4">
          <Col md={12}>
            <Carousel>
              <Carousel.Item>
                <img
                  className="d-block w-100"
                  src="https://cdn.pixabay.com/photo/2015/12/28/10/19/hotel-1111199_1280.jpg"
                  alt="First slide"
                  style={{ height: '400px'}}
                />
                <Carousel.Caption>
                  <h3> Cozy Heaven Stay</h3>
                  <p>Where every stay tells a story of comfort and care.</p>
                </Carousel.Caption>
              </Carousel.Item>
              <Carousel.Item>
                <img
                  className="d-block w-100"
                  src="https://cdn.pixabay.com/photo/2015/09/21/09/53/villa-cortine-palace-949547_1280.jpg"
                  alt="Second slide"
                  style={{ height: '400px' }}
                />
                <Carousel.Caption>
                <h3>Cozy Heaven Stay</h3>
                  <p>Where every stay tells a story of comfort and care.</p>
                </Carousel.Caption>
              </Carousel.Item>
            </Carousel>
          </Col>
        </Row>

        <Row className="mt-4">
          <Col md={12}>
            <h2 className="text-center">Amenities</h2>
          </Col>
          <Col md={12} className="amenities">
            <Row>
              <Col md={3}>
                <div className="amenity">
                  <FaSwimmingPool className="amenity-icon" />
                  <p>Swimming Pool</p>
                </div>
              </Col>
              <Col md={3}>
                <div className="amenity">
                  <FaWifi className="amenity-icon" />
                  <p>Free Wi-Fi</p>
                </div>
              </Col>
              <Col md={3}>
                <div className="amenity">
                  <FaBicycle className="amenity-icon" />
                  <p>Bicycle Rental</p>
                </div>
              </Col>
              <Col md={3}>
                <div className="amenity">
                  <FaUtensils className="amenity-icon" />
                  <p>Restaurant</p>
                </div>
              </Col>
              <Col md={3}>
                <div className="amenity">
                  <FaDog className="amenity-icon" />
                  <p>Pet Friendly</p>
                </div>
              </Col>
              <Col md={3}>
                <div className="amenity">
                  <FaBed className="amenity-icon" />
                  <p>Comfortable Beds</p>
                </div>
              </Col>
              <Col md={3}>
                <div className="amenity">
                  <FaParking className="amenity-icon" />
                  <p>Parking</p>
                </div>
              </Col>
              <Col md={3}>
                <div className="amenity">
                  <FaSmokingBan className="amenity-icon" />
                  <p>Non-Smoking</p>
                </div>
              </Col>
            </Row>
          </Col>
        </Row>

        {/* Cards Section */}
        <Row className="mt-4">
          <Col md={4}>
            <animated.div className="card-container" style={hoveredCard === 'hotel1' ? { transform: 'scale(1.05)' } : null}>
              <Card className="hotel-card" onMouseEnter={() => setHoveredCard('hotel1')} onMouseLeave={() => setHoveredCard(null)}>
                <Card.Img variant="top" src="https://cdn.pixabay.com/photo/2016/03/16/22/17/hotel-room-1261900_1280.jpg" />
                <Card.Body>
                  <Card.Title>Hotel 1</Card.Title>
                  <Card.Text>
                    This is a description of Hotel 1.
                  </Card.Text>
                  <Button variant="outline-primary">More Info</Button>
                </Card.Body>
              </Card>
            </animated.div>
          </Col>
          <Col md={4}>
            <animated.div className="card-container" style={hoveredCard === 'hotel2' ? { transform: 'scale(1.05)' } : null}>
              <Card className="hotel-card" onMouseEnter={() => setHoveredCard('hotel2')} onMouseLeave={() => setHoveredCard(null)}>
                <Card.Img variant="top" src="https://cdn.pixabay.com/photo/2018/10/01/00/51/luxury-hotel-3715115_1280.jpg" />
                <Card.Body>
                  <Card.Title>Hotel 2</Card.Title>
                  <Card.Text>
                    This is a description of Hotel 2.
                  </Card.Text>
                  <Button variant="outline-primary">More Info</Button>
                </Card.Body>
              </Card>
            </animated.div>
          </Col>
          <Col md={4}>
            <animated.div className="card-container" style={hoveredCard === 'hotel3' ? { transform: 'scale(1.05)' } : null}>
              <Card className="hotel-card" onMouseEnter={() => setHoveredCard('hotel3')} onMouseLeave={() => setHoveredCard(null)}>
                <Card.Img variant="top" src="https://cdn.pixabay.com/photo/2021/10/06/15/05/bedroom-6686061_1280.jpg" />
                <Card.Body>
                  <Card.Title>Hotel 3</Card.Title>
                  <Card.Text>
                    This is a description of Hotel 3.
                  </Card.Text>
                  <Button variant="outline-primary">More Info</Button>
                </Card.Body>
              </Card>
            </animated.div>
          </Col>
          <Col md={4}>
            <animated.div className="card-container" style={hoveredCard === 'hotel4' ? { transform: 'scale(1.05)' } : null}>
              <Card className="hotel-card" onMouseEnter={() => setHoveredCard('hotel4')} onMouseLeave={() => setHoveredCard(null)}>
                <Card.Img variant="top" src="https://cdn.pixabay.com/photo/2012/12/19/18/13/architecture-70920_1280.jpg" />
                <Card.Body>
                  <Card.Title>Hotel 4</Card.Title>
                  <Card.Text>
                    This is a description of Hotel 4.
                  </Card.Text>
                  <Button variant="outline-primary">More Info</Button>
                </Card.Body>
              </Card>
            </animated.div>
          </Col>

          <Col md={4}>
            <animated.div className="card-container" style={hoveredCard === 'hotel5' ? { transform: 'scale(1.05)' } : null}>
              <Card className="hotel-card" onMouseEnter={() => setHoveredCard('hotel5')} onMouseLeave={() => setHoveredCard(null)}>
                <Card.Img variant="top" src="https://cdn.pixabay.com/photo/2015/09/17/10/55/sirmione-943919_1280.jpg
" />
                <Card.Body>
                  <Card.Title>Hotel 5</Card.Title>
                  <Card.Text>
                    This is a description of Hotel 5.
                  </Card.Text>
                  <Button variant="outline-primary">More Info</Button>
                </Card.Body>
              </Card>
            </animated.div>
          </Col>

          <Col md={4}>
            <animated.div className="card-container" style={hoveredCard === 'hotel6' ? { transform: 'scale(1.05)' } : null}>
              <Card className="hotel-card" onMouseEnter={() => setHoveredCard('hotel6')} onMouseLeave={() => setHoveredCard(null)}>
                <Card.Img variant="top" src="https://cdn.pixabay.com/photo/2017/01/14/12/48/hotel-1979406_1280.jpg" />
                <Card.Body>
                  <Card.Title>Hotel 6</Card.Title>
                  <Card.Text>
                    This is a description of Hotel 6.
                  </Card.Text>
                  <Button variant="outline-primary">More Info</Button>
                </Card.Body>
              </Card>
            </animated.div>
          </Col>
        </Row>

        <Row className="mt-4">
          <Col md={12} className="text-center">
            <Button variant="primary">View More Hotels</Button>
          </Col>
        </Row>
        </Container>
        <footer className="login-footer mt-4" style={{ backgroundColor: '#343a40', color: '#ffffff', padding: '20px 0' }}>
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
}

export default HomePage;
