import React, { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faUser,
  faLock,
  faEye,
  faEyeSlash,
  faAngleRight,
  faPhone,
  faEnvelope,
} from "@fortawesome/free-solid-svg-icons";
import {
  faFacebook,
  faTwitter,
  faInstagram,
} from "@fortawesome/free-brands-svg-icons";
import AuthService from "../services/AuthService";
import { Container, Row, Col, Carousel } from "react-bootstrap";
import "./Login.css";
import chsLogo from "./chs.jpeg";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await AuthService.login({
        userName: username,
        password: password,
      });

      console.log("Login response:", response);

      if (response.data) {
        const { accessToken, userDto, administratorDto, hotelOwnerDto } =
          response.data;

        if (userDto) {
          localStorage.setItem("token", accessToken);
          window.location.href = "/user-dashboard";
          localStorage.setItem("userId", userDto.userId);
        } else if (administratorDto) {
          localStorage.setItem("token", accessToken);
          window.location.href = "/admin-dashboard";
        } else if (hotelOwnerDto) {
          localStorage.setItem("token", accessToken);
          window.location.href = "/owner-dashboard";
        } else {
          setError("Invalid username or password.");
        }
      } else {
        setError("Invalid username or password.");
      }
    } catch (error) {
      console.error("Login failed:", error);
      setError("Login failed. Please check your username and password.");
    }
  };

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  return (
    <div className="login-container">
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
        <div className="container-fluid">
          <a className="navbar-brand" href="/">
            <img src={chsLogo} alt="Company Logo" className="logo-size" />
          </a>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav ms-auto">
              <li className="nav-item">
                <a className="nav-link" href="/">
                  Home
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/aboutus">
                  About
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/contact">
                  Contact
                </a>
              </li>
            </ul>
          </div>
        </div>
      </nav>

      <div className="container">
        <div className="row">
          <div className="col-md-6 order-md-2">
            <form className="login-form" onSubmit={handleLogin}>
              <h2 className="text-center mb-4 login-title">Welcome Back!</h2>
              <div className="mb-3">
                <label htmlFor="username" className="form-label">
                  Username
                </label>
                <div className="input-group">
                  <span className="input-group-text">
                    <FontAwesomeIcon icon={faUser} />
                  </span>
                  <input
                    type="text"
                    className="form-control input-field"
                    id="username"
                    placeholder="Enter username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                  />
                </div>
              </div>
              <div className="mb-3">
                <label htmlFor="password" className="form-label">
                  Password
                </label>
                <div className="input-group">
                  <span className="input-group-text">
                    <FontAwesomeIcon icon={faLock} />
                  </span>
                  <input
                    type={showPassword ? "text" : "password"}
                    className="form-control input-field"
                    id="password"
                    placeholder="Enter password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                  />
                  <button
                    className="btn btn-outline-secondary"
                    type="button"
                    onClick={togglePasswordVisibility}
                  >
                    <FontAwesomeIcon icon={showPassword ? faEyeSlash : faEye} />
                  </button>
                </div>
              </div>
              <div className="text-danger mb-3">{error}</div>
              <div className="mb-3">
      <a href="/reset" className="forgot-password-link">Forgot Password?</a>
      <span style={{ marginRight: '380px' }}></span>
      <a href="/adduser">Create an account</a>
    </div>
              <button
                type="submit"
                className="btn btn-primary btn-lg btn-block login-button"
              >
                Login <FontAwesomeIcon icon={faAngleRight} />
              </button>
            </form>
          </div>
          <div className="col-md-6 order-md-1">
            <div className="login-divider"></div>
            <Carousel>
              <Carousel.Item>
                <img
                  className="d-block w-100 carousel-img"
                  src="https://cdn.pixabay.com/photo/2017/06/25/08/07/sunbeds-2439951_1280.jpg"
                  alt="First slide"
                />
              </Carousel.Item>
              <Carousel.Item>
                <img
                  className="d-block w-100 carousel-img"
                  src="https://cdn.pixabay.com/photo/2015/09/15/11/04/hotel-940730_1280.jpg"
                  alt="Second slide"
                />
              </Carousel.Item>
              <Carousel.Item>
                <img
                  className="d-block w-100 carousel-img"
                  src="https://cdn.pixabay.com/photo/2017/08/30/17/25/decoration-2697944_1280.jpg"
                  alt="Third slide"
                />
              </Carousel.Item>
            </Carousel>
          </div>
        </div>
      </div>
  
      <footer className="login-footer" style={{ backgroundColor: '#343a40', color: '#ffffff', padding: '20px 0', marginTop: 'auto', position: 'fixed', bottom: 0, width: '100%' }}>
      
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

export default Login;