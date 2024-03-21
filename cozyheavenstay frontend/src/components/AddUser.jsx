import React, { useState, useEffect } from "react";
import UserService from "../services/UserService";
import { Link, useParams, useNavigate } from "react-router-dom";
import { Container, Row, Col } from "react-bootstrap"; 

import {
  faFacebook,
  faInstagram,
  faTwitter,
} from "@fortawesome/free-brands-svg-icons";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faEye,
  faEyeSlash,
  faUser,
  faLock,
  faEnvelope,
  faMapMarkerAlt,
  faUserPlus,
  faPhone,
} from "@fortawesome/free-solid-svg-icons";
import chsLogo from "./chs.jpeg";

const AddUser = () => {
  const [email, setEmail] = useState("");
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [gender, setGender] = useState("");
  const [address, setAddress] = useState("");
  const [contactNo, setContactNo] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [cardHeight, setCardHeight] = useState(0); 
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (id) {
      UserService.getUserById(id)
        .then((response) => {
          const { email, userName, password, gender, address, contactNo } =
            response.data;
          setEmail(email);
          setUserName(userName);
          setPassword(password);
          setGender(gender);
          setAddress(address);
          setContactNo(contactNo);
        })
        .catch((error) => {
          console.log("Error fetching user data: ", error);
          alert("Failed to fetch user data. Please try again later.");
        });
    }
  }, [id]);

  useEffect(() => {
    // Get the height of the card when it is mounted
    const card = document.getElementById("user-card");
    if (card) {
      const height = card.offsetHeight;
      setCardHeight(height);
    }
  }, []);

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  const saveOrUpdateUser = (e) => {
    e.preventDefault();
    const user = { email, userName, password, gender, address, contactNo };

    if (
      !email ||
      !userName ||
      !password ||
      !confirmPassword ||
      !gender ||
      !address ||
      !contactNo
    ) {
      alert("Please fill in all the fields");
      return;
    }

    if (password !== confirmPassword) {
      alert("Passwords do not match");
      return;
    }

    UserService.getAllUsers()
      .then((response) => {
        const existingUser = response.data.find(
          (u) =>
            u.email === email ||
            u.userName === userName ||
            u.contactNo === contactNo
        );
        if (existingUser) {
          if (existingUser.email === email) {
            alert("Email already exists");
          } else if (existingUser.userName === userName) {
            alert("Username already exists");
          } else if (existingUser.contactNo === contactNo) {
            alert("Contact number already exists");
          }
        } else {
          if (id) {
            UserService.updateUser(id, user)
              .then(() => {
                navigate("/user-dashboard");
                alert("User data updated successfully");
              })
              .catch((error) => {
                console.log("Error updating user data: ", error);
                alert("Failed to update user data. Please try again later.");
              });
          } else {
            UserService.addUser(user)
              .then(() => {
                navigate("/login");
                alert("User registered successfully");
              })
              .catch((error) => {
                console.log("Error adding new user: ", error);
                alert("Failed to register user. Please try again later.");
              });
          }
        }
      })
      .catch((error) => {
        console.error("Error fetching existing users: ", error);
        alert("Failed to fetch existing users. Please try again later.");
      });
  };

  return (
    <div>
      <nav
        className="navbar navbar-light bg-dark"
        style={{ fontFamily: "cursive", color: "#fff" }}
      >
        <div className="container-fluid">
          <Link className="navbar-brand d-flex align-items-center" to="/">
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
                backgroundImage: `url(https://cdn.pixabay.com/photo/2018/11/09/10/10/interior-3804154_1280.jpg)`,
                minHeight: cardHeight,
                backgroundSize: "cover",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
              }}
            >
            
            </div>
          </Col>
          <Col md={6}>
            <div id="user-card" className="card">
              <div className="card-header">
                <h2 className="text-center">
                  {id ? "Update User" : "Register"}
                </h2>{" "}
                {/* Changed "Add User" to "Register" */}
              </div>
              <div className="card-body">
                <form onSubmit={saveOrUpdateUser}>
                  <div className="mb-3">
                    <label className="form-label">
                      <FontAwesomeIcon icon={faEnvelope} /> Email
                    </label>
                    <input
                      type="email"
                      className="form-control"
                      placeholder="Enter your email"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                    />
                  </div>
                  <div className="mb-3">
                    <label className="form-label">
                      <FontAwesomeIcon icon={faUser} /> User Name
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      placeholder="Enter your username"
                      value={userName}
                      onChange={(e) => setUserName(e.target.value)}
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
                  <div className="mb-3">
                    <label className="form-label">
                      <FontAwesomeIcon icon={faUserPlus} /> Gender
                    </label>
                    <select
                      className="form-select"
                      value={gender}
                      onChange={(e) => setGender(e.target.value)}
                    >
                      <option value="">Select your Gender</option>
                      <option value="male">Male</option>
                      <option value="female">Female</option>
                      <option value="other">Other</option>
                    </select>
                  </div>
                  <div className="mb-3">
                    <label className="form-label">
                      <FontAwesomeIcon icon={faMapMarkerAlt} /> Address
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      placeholder="Enter your address"
                      value={address}
                      onChange={(e) => setAddress(e.target.value)}
                    />
                  </div>
                  <div className="mb-3">
                    <label className="form-label">
                      <FontAwesomeIcon icon={faPhone} /> Contact Number
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      placeholder="Enter your 10-digit phone number"
                      value={contactNo}
                      onChange={(e) => setContactNo(e.target.value)}
                    />
                  </div>
                  <div className="d-grid gap-2">
                    <button type="submit" className="btn btn-primary">
                      Save User
                    </button>
                    <Link to="/" className="btn btn-danger">
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

export default AddUser;
