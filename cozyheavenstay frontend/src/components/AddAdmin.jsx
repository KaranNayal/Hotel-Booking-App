import React, { useState, useEffect } from "react";
import AuthService from "../services/AuthService";
import { Link, useNavigate } from "react-router-dom";
import { Container, Row, Col } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faPhone,
  faLock,
  faUser,
  faEnvelope,
  faEye,
  faEyeSlash,
} from "@fortawesome/free-solid-svg-icons"; 
import chsLogo from "./chs.jpeg";
import {
  faFacebook,
  faInstagram,
  faTwitter,
} from "@fortawesome/free-brands-svg-icons";

const AddAdmin = () => {
  const [userName, setUserName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();
  const [cardHeight, setCardHeight] = useState(0);

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };
  
  useEffect(() => {
    // Get the height of the card when it is mounted
    const card = document.getElementById("admin-card");
    if (card) {
      const height = card.offsetHeight;
      setCardHeight(height);
    }
  }, []);

  const saveOrUpdateAdmin = (e) => {
    e.preventDefault();
    const admin = { userName, email, password };

    if (!userName || !email || !password || !confirmPassword) {
      alert("Please fill in all the fields");
      return;
    }

    if (password !== confirmPassword) {
      alert("Passwords do not match");
      return;
    }

    AuthService.registerAdmin(admin)
      .then(() => {
        navigate("/admin-dashboard");
      })
      .catch((error) => {
        console.error("Error registering admin: ", error);
        alert("Failed to register admin. Please try again later.");
      });
  };

  return (
    <div>
      <nav
        className="navbar navbar-light bg-dark"
        style={{ fontFamily: "cursive", color: "#fff" }}
      >
        <div className="container-fluid">
          <Link
            className="navbar-brand d-flex align-items-center"
            to="/admin-dashboard"
          >
            <img
              src={chsLogo}
              alt="Company Logo"
              height="40"
              className="me-2"
            />
            <span className="fw-bold" style={{ color: "#ffffff" }}>
              Cozy Heaven Stay
            </span>
          </Link>
        </div>
      </nav>
      <Container className="mt-4">
        <Row>
          <Col md={6}>
            <div
              className="background-container"
              style={{
                backgroundImage: `url(https://cdn.pixabay.com/photo/2021/02/03/00/10/receptionists-5975962_1280.jpg)`,
                minHeight: cardHeight,
                backgroundSize: "cover",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
              }}
            ></div>
          </Col>
          <Col md={6}>
            <div id="admin-card" className="card">
              <div className="card-header">
                <h2 className="text-center">Register Admin</h2>
              </div>
              <div className="card-body">
                <form onSubmit={saveOrUpdateAdmin}>
                  <div className="mb-3">
                    <label className="form-label">
                      <FontAwesomeIcon icon={faUser} /> User Name
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      placeholder="Enter admin's username"
                      value={userName}
                      onChange={(e) => setUserName(e.target.value)}
                    />
                  </div>
                  <div className="mb-3">
                    <label className="form-label">
                      <FontAwesomeIcon icon={faEnvelope} /> Email
                    </label>
                    <input
                      type="email"
                      className="form-control"
                      placeholder="Enter admin's email"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                    />
                  </div>
                  <div className="mb-3">
                    <label className="form-label">
                      <FontAwesomeIcon icon={faLock} /> Password
                    </label>
                    <div className="input-group">
                      <input
                        type={showPassword ? "text" : "password"}
                        className="form-control"
                        placeholder="Type your password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                      />
                      <button
                        className="btn btn-outline-secondary"
                        type="button"
                        onClick={togglePasswordVisibility}
                      >
                        <FontAwesomeIcon
                          icon={showPassword ? faEyeSlash : faEye}
                        />
                      </button>
                    </div>
                  </div>
                  <div className="mb-3">
                    <label className="form-label">
                      <FontAwesomeIcon icon={faLock} /> Confirm Password
                    </label>
                    <input
                      type="password"
                      placeholder="Type your password again"
                      className="form-control"
                      value={confirmPassword}
                      onChange={(e) => setConfirmPassword(e.target.value)}
                    />
                    {password !== confirmPassword && (
                      <small style={{ color: "red" }}>
                        Passwords do not match
                      </small>
                    )}{" "}
                  </div>

                  <div className="d-grid gap-2">
                    <button type="submit" className="btn btn-primary">
                      Save Admin
                    </button>
                    <Link to="/admin-dashboard" className="btn btn-danger">
                      Cancel
                    </Link>
                  </div>
                </form>
              </div>
            </div>
          </Col>
        </Row>
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
    </div>
  );
};

export default AddAdmin;
