import React from 'react';
import { Link } from 'react-router-dom';
import chsLogo from './chs.jpeg';
import { Container, Row, Col, Nav, NavLink, Carousel, Button } from 'react-bootstrap';
import AuthService from '../services/AuthService';
import { faEnvelope, faPhone, faHotel, faUserEdit, faSignOutAlt } from '@fortawesome/free-solid-svg-icons';
import { faFacebook, faInstagram, faTwitter } from '@fortawesome/free-brands-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

const OwnerDashboard = () => {
  const handleLogout = () => {
    AuthService.logout();
    window.location.replace('/login');
  };
  
  return (
    <div className="dashboard-container">
      <header className="header bg-dark text-white py-4 d-flex justify-content-between align-items-center">
        <Link className="navbar-brand d-flex align-items-center" to="/">
          <img src={chsLogo} alt="Company Logo" height="40" className="me-2" />
          <span className="fw-bold" style={{ color: '#ffffff' }}>Cozy Heaven Stay</span>
        </Link>
        <div className="d-flex align-items-center">
          <h1 className="header-title m-0">Owner Dashboard</h1>
        </div>
        <Button variant="link" className="logout-button" onClick={handleLogout}>
          <FontAwesomeIcon icon={faSignOutAlt} /> Logout
        </Button>
      </header>
      <Container fluid>
        <Row>
          <Col sm={3} className="sidebar bg-light p-4">
            <h2 className="mb-4">Dashboard</h2>
            <Nav className="flex-column">
              <NavLink as={Link} to="/addhotel" className="nav-link">
                <FontAwesomeIcon icon={faHotel} /> Add Hotel
              </NavLink>
             
              <NavLink as={Link} to="/myhotels" className="nav-link">
                <FontAwesomeIcon icon={faHotel} /> My Hotels
              </NavLink>
              <NavLink as={Link} to="/editownerprofile" className="nav-link">
                <FontAwesomeIcon icon={faUserEdit} /> Edit Profile
              </NavLink>
            </Nav>
          </Col>
          <Col sm={9} className="content p-4">
            <Row className="mt-4">
              <Col md={12}>
                <Carousel>
                  <Carousel.Item>
                    <img
                      className="d-block w-100"
                      src="https://cdn.pixabay.com/photo/2018/10/01/00/52/roof-top-pool-3715118_1280.jpg"
                      alt="First slide"
                    />
                    <Carousel.Caption>
                      <h3>Cozy Heaven Stay</h3>
                      <p>Where every stay tells a story of comfort and care.</p>
                    </Carousel.Caption>
                  </Carousel.Item>
                  <Carousel.Item>
                    <img
                      className="d-block w-100"
                      src="https://cdn.pixabay.com/photo/2015/04/16/22/21/courchevel-726325_1280.jpg"
                      alt="Second slide"
                    />
                    <Carousel.Caption>
                      <h3>Second slide label</h3>
                      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                    </Carousel.Caption>
                  </Carousel.Item>
                </Carousel>
              </Col>
            </Row>
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
};

export default OwnerDashboard;
