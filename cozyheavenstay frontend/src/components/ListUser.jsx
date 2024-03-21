import React, { useEffect, useState } from 'react';
import UserService from '../services/UserService';
import { Link, useNavigate } from 'react-router-dom';
import { faEnvelope, faPhone } from '@fortawesome/free-solid-svg-icons';
import { faFacebook, faInstagram, faTwitter } from '@fortawesome/free-brands-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Button, Row, Container, Col } from 'react-bootstrap';

import chsLogo from './chs.jpeg';

const ListUser = () => {
  const [users, setUsers] = useState([]);
  const navigate = useNavigate();

  const deleteUser = (id) => {
    console.log("Delete user handler fired. ID value received =", id);
    UserService.deleteUser(id)
      .then((response) => {
        console.log("Response received from delete API:", response.data);
        fetchAllUsers(); // calling function to display updated list after deleting
      })
      .catch(error => {
        console.error("Error received from delete API:", error);
      });
  };

  const fetchAllUsers = () => {
    console.log("Use Effect...");

    UserService.getAllUsers()
      .then((response) => {
        console.log('Response received from API:', response.data);
        setUsers(response.data);
        console.log("State variable assigned with response data...");
      })
      .catch(error => {
        console.error("Error received from fetch all users API:", error);
      });
  };

  useEffect(() => {
    console.log("useEffect hook fired");
    fetchAllUsers();
  }, []);

  return (
    <div className='user-container' style={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
      <header className="header bg-dark text-white py-4 d-flex justify-content-between align-items-center">
        <div className="d-flex align-items-center">
          <Link className="navbar-brand d-flex align-items-center" to="/admin-dashboard">
            <img src={chsLogo} alt="Company Logo" height="40" className="me-2" />
            <span className="fw-bold">Cozy Heaven Stay</span>
          </Link>
        </div>
        <div className="text-center">
          <h1 className="header-title m-0">User  Data     </h1>
        </div>
        <div></div> {/* This empty div is for spacing*/}
      </header>

      <table className="table table-bordered table-hover table-light mt-4">
        <thead>
          <tr className='table-dark'>
            <th>User ID</th>
            <th>Email</th>
            <th>User Name</th>
            <th>Gender</th>
            <th>Address</th>
            <th>Contact Number</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          {
            users.map((user, key) => (
              <tr key={key}>
                <td>{user.userId}</td>
                <td>{user.email}</td>
                <td>{user.userName}</td>
                <td>{user.gender}</td>
                <td>{user.address}</td>
                <td>{user.contactNo}</td>
                <td><button className='btn btn-danger' onClick={() => deleteUser(user.userId)}>Delete</button></td>
              </tr>
            ))
          }
        </tbody>
      </table>

      <div style={{ textAlign: 'center', marginTop: '20px', marginBottom: '20px' }}>
        <Button variant="secondary" onClick={() => navigate('/admin-dashboard')}>
          Back to Admin Dashboard
        </Button>
      </div>

      <footer className="login-footer" style={{ backgroundColor: '#343a40', color: '#ffffff', padding: '20px 0', marginTop: 'auto' }}>
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

export default ListUser;
