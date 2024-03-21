import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './BookingForm.css';
import { faCalendarAlt } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import RoomService from '../services/RoomService'; // Import RoomService
import chsLogo from './chs.jpeg';
import {  Row, Col,Container } from 'react-bootstrap';
import {  useNavigate } from "react-router-dom";

import {faEnvelope, faPhone } from '@fortawesome/free-solid-svg-icons';
import { faFacebook, faInstagram, faTwitter } from '@fortawesome/free-brands-svg-icons';
const BookingForm = () => {
  const [formData, setFormData] = useState({
    hotelId: '',
    userId: '',
    roomType: '',
    bedType: '',
    acStatus: false,
    checkInDate: '',
    checkOutDate: '',
    noOfRooms: 1,
    noOfAdults: 1,
    noOfChildren: 0,
    totalFare: 0,
  });
  const navigate = useNavigate();

  useEffect(() => {
    // Set userId and hotelId from local storage
    const userId = localStorage.getItem('userId');
    const hotelId = localStorage.getItem('selectedHotelId');
    setFormData((prevFormData) => ({ ...prevFormData, userId, hotelId }));
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

 
const calculateTotalFare = () => {
  const { roomType, bedType, acStatus, noOfAdults, noOfChildren, noOfRooms, checkInDate, checkOutDate } = formData;
  let baseFare = calculateBaseFare(roomType, bedType, acStatus);

  // Calculate the number of days between check-in and check-out dates
  const checkInDateTime = new Date(checkInDate).getTime();
  const checkOutDateTime = new Date(checkOutDate).getTime();
  const millisecondsPerDay = 1000 * 60 * 60 * 24;
  const numberOfDays = Math.ceil((checkOutDateTime - checkInDateTime) / millisecondsPerDay);

  let totalFare = baseFare * noOfRooms * numberOfDays;

  // Calculate additional charge based on occupants
  const totalOccupants = noOfAdults + noOfChildren;
  const additionalChargeRate = 0.4; // Additional charge rate for each additional occupant
  const maxOccupancy = getMaxOccupancy(bedType); // Maximum occupancy based on bed type

  if (totalOccupants > maxOccupancy) {
    const additionalOccupants = totalOccupants - maxOccupancy;
    totalFare += additionalOccupants * (baseFare * additionalChargeRate) * numberOfDays;
  }
  setFormData((prevState) => ({ ...prevState, totalFare }));
  return totalFare; // Return the calculated total fare
};

  
  const calculateBaseFare = (roomType, bedType, acStatus) => {
    let baseFare = 0;
    switch (roomType) {
      case 'STANDARD':
        switch (bedType) {
          case 'SINGLE':
            baseFare = 2000;
            break;
          case 'DOUBLE':
            baseFare = 2500;
            break;
          case 'KING':
            baseFare = 3000;
            break;
          default:
            baseFare = 2000;
        }
        break;
      case 'DELUXE':
        switch (bedType) {
          case 'DOUBLE':
            baseFare = 4500;
            break;
          case 'KING':
            baseFare = 5500;
            break;
          default:
            baseFare = 4500;
        }
        break;
      case 'SUITE':
        baseFare = 7300;
        if (bedType !== 'KING') {
          // Ensure bed type is set to KING for SUITE rooms
          setFormData((prevState) => ({ ...prevState, bedType: 'KING' }));
        }
        if (!acStatus) {
          // Ensure AC status is set to true for SUITE rooms
          setFormData((prevState) => ({ ...prevState, acStatus: true }));
        }
        break;
      default:
        baseFare = 0;
    }
    // Add 200 to the base fare if AC is enabled
    if (acStatus) {
      baseFare += 200;
    }
    return baseFare;
  };
  
  const getMaxOccupancy = (bedType) => {
    switch (bedType) {
      case 'SINGLE':
        return 2;
      case 'DOUBLE':
        return 4;
      case 'KING':
        return 6;
      default:
        return 0;
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const totalFare = calculateTotalFare(); // Calculate total fare only when the form is submitted
    // Retrieve hotelId and userId from localStorage
    const hotelId = localStorage.getItem('selectedHotelId');
    const userId = localStorage.getItem('userId');

    // Make reservation
    const formattedFormData = {
      hotelId: hotelId,
      userId: userId,
      roomType: formData.roomType,
      bedType: formData.bedType,
      acStatus: formData.acStatus,
      checkInDate: formData.checkInDate + 'T00:00:00', // Format check-in date
      checkOutDate: formData.checkOutDate + 'T00:00:00', // Format check-out date
      noOfRooms: formData.noOfRooms,
      noOfAdults: formData.noOfAdults,
      noOfChildren: formData.noOfChildren,
      totalFare:totalFare,
    };

    RoomService.makeReservation(formattedFormData)
      .then((response) => {
        // Handle successful reservation
        console.log('Reservation successful:', response.data);
        // Reset form data
        setFormData({
          hotelId: '',
          userId: '',
          roomType: '',
          bedType: '',
          acStatus: false,
          checkInDate: '',
          checkOutDate: '',
          noOfRooms: 1,
          noOfAdults: 1,
          noOfChildren: 0,
          totalFare: 0,
        });
        // Provide feedback to the user
        navigate("/user-dashboard");

        alert('Reservation successful');
      })
      .catch((error) => {
        // Handle reservation error
        console.error('Reservation failed:', error);
        // Provide feedback to the user
        alert('Reservation failed');
      });
  };

  return (
    <>
     <nav className="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <div className="container-fluid">
        <a className="navbar-brand" href="/user-dashboard">
            <img src={chsLogo} alt="Company Logo" className="logo-size" />
        </a>
      
       
       
    </div>
</nav>
    <div className="container booking-form">
       
      <h2>
        <FontAwesomeIcon icon={faCalendarAlt} /> Book a Room
      </h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="roomType">Room Type</label>
          <select
            className="form-control"
            id="roomType"
            name="roomType"
            value={formData.roomType}
            onChange={handleChange}
          >
            <option value={null}>Select One</option>
            <option value="STANDARD">Standard</option>
            <option value="DELUXE">Deluxe</option>
            <option value="SUITE">Suite</option>
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="bedType">Bed Type</label>
          <select
            className="form-control"
            id="bedType"
            name="bedType"
            value={formData.bedType}
            onChange={handleChange}
          >
            <option value={null}>Select One</option>
            <option value="SINGLE">Single</option>
            <option value="DOUBLE">Double</option>
            <option value="KING">King</option>
          </select>
        </div>
        <div className="form-group form-check">
          <input
            type="checkbox"
            className="form-check-input"
            id="acStatus"
            name="acStatus"
            checked={formData.acStatus}
            onChange={(e) => setFormData({ ...formData, acStatus: e.target.checked })}
          />
          <label className="form-check-label" htmlFor="acStatus">
            Air Conditioner
          </label>
        </div>
        <div className="form-group">
          <label htmlFor="checkInDate">Check-in Date</label>
          <input
            type="date"
            className="form-control"
            id="checkInDate"
            name="checkInDate"
            value={formData.checkInDate}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="checkOutDate">Check-out Date</label>
          <input
            type="date"
            className="form-control"
            id="checkOutDate"
            name="checkOutDate"
            value={formData.checkOutDate}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="noOfRooms">Number of Rooms (Max 10)</label>
          <input
            type="number"
            className="form-control"
            id="noOfRooms"
            name="noOfRooms"
            value={formData.noOfRooms}
            onChange={handleChange}
            min="1"
            max="10"
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="noOfAdults">Number of Adults</label>
          <input
            type="number"
            className="form-control"
            id="noOfAdults"
            name="noOfAdults"
            value={formData.noOfAdults}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="noOfChildren">Number of Children</label>
          <input
            type="number"
            className="form-control"
            id="noOfChildren"
            name="noOfChildren"
            value={formData.noOfChildren}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="totalFare">Total Fare</label>
          <input
            type="number"
            step="0.01"
            className="form-control"
            id="totalFare"
            name="totalFare"
            value={formData.totalFare}
            readOnly // Make the input read-only to prevent manual changes
            required
          />
        </div>
        <button type="submit" className="btn btn-primary mt-4">
          Submit
        </button>
      </form>
    
      
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
    </>
  );
};

export default BookingForm;
