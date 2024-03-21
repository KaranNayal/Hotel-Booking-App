import React, { useEffect, useState } from 'react';
import { Form, Button, Row, Col, Card, Container } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import UserService from '../services/UserService';
import AuthService from '../services/AuthService';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEnvelope, faPhone } from '@fortawesome/free-solid-svg-icons';
import { faFacebook, faInstagram, faTwitter } from '@fortawesome/free-brands-svg-icons';
import chsLogo from './chs.jpeg';
const EditProfile = () => {
  const navigate = useNavigate();
  const [updatedUser, setUpdatedUser] = useState({});
  const [user, setUser] = useState(null);
  const [userId, setUserId] = useState(null);

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const response = await AuthService.fetchUserData();
        const fetchedUserId = response.data.userId;
        setUserId(fetchedUserId);
        const userResponse = await UserService.getUserById(fetchedUserId);
        setUser(userResponse.data);
        setUpdatedUser(userResponse.data); 
      } catch (error) {
        console.error('Error fetching user data:', error);
      }
    };

    fetchUserData();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUpdatedUser({ ...updatedUser, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await UserService.updateUser(userId, updatedUser);
      alert('Profile updated successfully!');
      navigate('/user-dashboard'); // Redirect to user dashboard after successful update
    } catch (error) {
      console.error('Error updating profile:', error);
      alert('Failed to update profile. Please try again.');
    }
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
    <div style={{ background: 'linear-gradient(to bottom right, #ffffff, #f2f2f2)', minHeight: '100vh', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
      <Card style={{ padding: '30px', width: '90%', maxWidth: '800px', boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)' }}>
        <h2 style={{ textAlign: 'center', marginBottom: '30px' }}>Edit User Details</h2>
        <Form onSubmit={handleSubmit}>
          <Form.Group as={Row} controlId="formBasicUsername">
            <Form.Label column sm={4}>Username</Form.Label>
            <Col sm={8}>
              <Form.Control type="text" name="userName" value={user ? user.userName : ''} onChange={handleChange} disabled />
            </Col>
          </Form.Group>
          <Form.Group as={Row} controlId="formBasicAddress">
            <Form.Label column sm={4}>Address</Form.Label>
            <Col sm={8}>
              <Form.Control type="text" name="address" value={updatedUser.address || ''} onChange={handleChange} placeholder={user ? user.address : ''} />
            </Col>
          </Form.Group>
          <Form.Group as={Row} controlId="formBasicEmail">
            <Form.Label column sm={4}>Email address</Form.Label>
            <Col sm={8}>
              <Form.Control type="email" name="email" value={updatedUser.email || ''} onChange={handleChange} placeholder={user ? user.email : ''} />
            </Col>
          </Form.Group>
          <Form.Group as={Row} controlId="formBasicContact">
            <Form.Label column sm={4}>Contact Number</Form.Label>
            <Col sm={8}>
              <Form.Control type="text" name="contactNo" value={updatedUser.contactNo || ''} onChange={handleChange} placeholder={user ? user.contactNo : ''} />
            </Col>
          </Form.Group>
          <div style={{ textAlign: 'center', marginTop: '20px' }}>
            <Button variant="primary" type="submit" style={{ marginRight: '10px', padding: '8px 20px' }}>
              Update Profile
            </Button>
            <Button variant="secondary" onClick={() => navigate('/user-dashboard')} style={{ padding: '8px 20px' }}>
              Back to User Dashboard
            </Button>
          </div>
        </Form>
      </Card>
    </div>
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
    </>
  );
};

export default EditProfile;
