import React, { useState } from 'react';
import UserService from '../services/UserService';
import { useNavigate } from 'react-router-dom';
import {
    Container,
    Row,
    Col,
    FormControl, InputGroup,Button, Form
    
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

const PasswordResetForm = () => {
    const [userName, setUsername] = useState('');
    const [user, setUser] = useState(null);
    const [newPassword, setNewPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [error, setError] = useState(null);
    const [submitted, setSubmitted] = useState(false);
    const [validNewPassword, setValidNewPassword] = useState(false);
    const [validConfirmPassword, setValidConfirmPassword] = useState(false);
    const navigate = useNavigate();

    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    };

    const handleNewPasswordChange = (event) => {
        const newPasswordValue = event.target.value;
        setNewPassword(newPasswordValue);
        setValidNewPassword(newPasswordValue.length >= 8);
    };

    const handleConfirmPasswordChange = (event) => {
        const confirmPasswordValue = event.target.value;
        setConfirmPassword(confirmPasswordValue);
        setValidConfirmPassword(confirmPasswordValue === newPassword);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        UserService.getbyUsername(userName)
            .then((response) => {
                setUser(response.data);
                setError(null);
                setSubmitted(true);
            })
            .catch((error) => {
                setUser(null);
                setError('User not found');
            });
    };

    const handleResetPassword = () => {
        const updatedUser = {
            ...user,
            password: newPassword
        };

        UserService.updateUser(user.userId, updatedUser)
            .then(() => {
                window.alert("Password updated successfully!");
                navigate("/login");
            })
            .catch(error => {
                console.log("Error updating password:", error);
                window.alert("An error occurred while updating the password!");
            });
    };

    return (
        <> <header className="header bg-dark text-white py-4 d-flex justify-content-between align-items-center">
        <Link
          className="navbar-brand d-flex align-items-center"
          to="/login"
        >
          <img src={chsLogo} alt="Company Logo" height="40" className="me-2" />
          <span className="fw-bold" style={{ color: "#ffffff" }}>
            Cozy Heaven Stay
          </span>
        </Link>
       
      
      </header>
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '70vh' }}>
            <div className='card' style={{ width: '800px', backgroundColor: '#f0f5ff', padding: '80px', borderRadius: '10px' }}>
                {!submitted && (
                    <Form onSubmit={handleSubmit}>
                        <h2 style={{ color: '#007bff', marginBottom: '20px' }}> Reset Password</h2>
                        <InputGroup className="mb-3">
                            <FormControl
                                placeholder="Enter username"
                                aria-label="Username"
                                aria-describedby="basic-addon2"
                                value={userName}
                                onChange={handleUsernameChange}
                            />
                            &nbsp; &nbsp;
                            <Button variant="primary" type="submit">
                                Submit
                            </Button>
                        </InputGroup>
                    </Form>
                )}
                {error && <p style={{ color: '#dc3545' }}>User not found</p>}
                {user && submitted && ( 
                    <Form>
                        <Form.Group controlId="newPassword">
                            <Form.Label>New Password</Form.Label>
                            <Form.Control
                                type="password"
                                placeholder="Enter new password"
                                value={newPassword}
                                onChange={handleNewPasswordChange}
                                isInvalid={newPassword.length > 0 && !validNewPassword}
                                isValid={validNewPassword}
                            />
                            <Form.Control.Feedback type="invalid">Password must be at least 8 characters</Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group controlId="confirmPassword">
                            <Form.Label>Confirm Password</Form.Label>
                            <Form.Control
                                type="password"
                                placeholder="Confirm new password"
                                value={confirmPassword}
                                onChange={handleConfirmPasswordChange}
                                isInvalid={confirmPassword.length > 0 && !validConfirmPassword}
                                isValid={validConfirmPassword}
                            />
                            <Form.Control.Feedback type="invalid">Passwords do not match</Form.Control.Feedback>
                        </Form.Group>
                        <Button variant="primary" onClick={handleResetPassword} disabled={!validNewPassword || !validConfirmPassword}>
                            Reset Password
                        </Button>
                    </Form>
                )}
            </div>
        
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
                }    
export default PasswordResetForm;
