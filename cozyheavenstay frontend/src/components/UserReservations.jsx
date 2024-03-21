import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import UserService from '../services/UserService';
import {
    Container,
    Row,
    Col,
    Button,
    Card,
  } from "react-bootstrap";
  import { Link } from "react-router-dom";
  import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
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
const UserReservations = () => {
    const [reservations, setReservations] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const userId = localStorage.getItem('userId');
        if (!userId) {
            console.error('userId not found in local storage');
            return;
        }

        UserService.getUserReservations(userId)
            .then(response => {
                console.log('Response received from API:', response.data);
                setReservations(response.data);
            })
            .catch(error => {
                console.error("Error received from fetch user reservations API:", error);
            });
    }, []);

    return (
        <>
       <header className="header bg-dark text-white py-4 d-flex justify-content-between align-items-center">
    <Link
        className="navbar-brand d-flex align-items-center"
        to="/user-dashboard"
    >
        <img src={chsLogo} alt="Company Logo" height="40" className="me-2" />
    </Link>
    <div className="d-flex align-items-center flex-grow-1 justify-content-center"> {/* Updated this div */}
        <h1 className="header-title m-0 text-center"> {/* Added text-center class */}
            My Reservations
        </h1>
    </div>
</header>

        <Container>
           
            <div className="reservation-list mt-4">
                <Row>
                    {reservations.map(reservation => (
                        <Col key={reservation.id} md={6}>
                            <Card className="reservation-card" style={{ background: 'linear-gradient(to bottom right, #f0f0f0, #d0d0d0)' }}>
                                <Card.Body>
                                    <Card.Title>Reservation ID: {reservation.reservationId}</Card.Title>
                                    <Card.Text>Room ID: {reservation.roomId}</Card.Text>
                                    <Card.Text>Number of Rooms: {reservation.noOfRooms}</Card.Text>
                                    <Card.Text>Number of Adults: {reservation.noOfAdults}</Card.Text>
                                    <Card.Text>Number of Children: {reservation.noOfChildrens}</Card.Text>
                                    <Card.Text>Status: {reservation.status}</Card.Text>
                                    <Card.Text>Check-in Date: {reservation.checkInDate}</Card.Text>
                                    <Card.Text>Check-out Date: {reservation.checkOutDate}</Card.Text>
                                    <Card.Text>Total Fare: {reservation.totalFare}</Card.Text>
                                </Card.Body>
                            </Card>
                        </Col>
                    ))}
                </Row>
            </div>
            <Button className='mt-4' variant="secondary" onClick={() => navigate('/user-dashboard')}>
                Back to Home
            </Button>
        </Container>
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
        </>
    );
};

export default UserReservations;
