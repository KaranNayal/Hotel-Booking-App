import React, { useState, useEffect } from "react";
import {
  Container,
  Row,
  Col,
  Nav,
  Button,
  Card,
} from "react-bootstrap";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faEnvelope,
  faPhone,
  faUsersCog,
  faUserPlus,
  faListAlt,
  faSignOutAlt,
  faHotel,
} from "@fortawesome/free-solid-svg-icons";
import AuthService from "../services/AuthService";
import HotelService from "../services/HotelService";
import {
  faFacebook,
  faInstagram,
  faTwitter,
} from "@fortawesome/free-brands-svg-icons";
import chsLogo from "./chs.jpeg";

const AdminDashboard = () => {
  const [user, setUser] = useState(null);
  const [city, setCity] = useState("");
  const [searchResults, setSearchResults] = useState([]);

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const response = await AuthService.fetchUserData();
        setUser(response.data);
      } catch (error) {
        console.error("Error fetching user data:", error);
      }
    };

    fetchUserData();
  }, []);

  const handleLogout = () => {
    AuthService.logout();
    window.location.replace("/login");
  };

  const handleSearch = async () => {
    try {
      setSearchResults([]); // Clear previous search results
      console.log("Searching hotels in", city);
    } catch (error) {
      console.error("Error searching hotels:", error);
    }
  };

  const handleViewAllHotels = async () => {
    try {
      const response = await HotelService.getAllHotels();
      console.log("All Hotels:", response.data);
      // Handle displaying all hotels in your application
    } catch (error) {
      console.error("Error fetching all hotels:", error);
    }
  };

  return (
    <div className="dashboard-container" style={{ backgroundImage: `url('https://cdn.pixabay.com/photo/2017/03/09/06/30/pool-2128578_1280.jpg')`, backgroundSize: 'cover', backgroundPosition: 'center', backgroundRepeat: 'no-repeat'}}>
      <header className="header bg-dark text-white py-4 d-flex justify-content-between align-items-center">
        <Link
          className="navbar-brand d-flex align-items-center"
          to="/admin-dashboard"
        >
          <img src={chsLogo} alt="Company Logo" height="40" className="me-2" />
          <span className="fw-bold" style={{ color: "#ffffff" }}>
            Cozy Heaven Stay
          </span>
        </Link>
        <div className="d-flex align-items-center">
          <h1 className="header-title m-0">Admin Dashboard</h1>
        </div>
        <Button variant="link" className="logout-button" onClick={handleLogout}>
          <FontAwesomeIcon icon={faSignOutAlt} /> Logout
        </Button>
      </header>

      <Container fluid>
        <Row>
          <Col className="content p-4">
            <Row>
              <Col md={4}>
                <Card className="mb-3">
                  <Card.Body>
                    <Card.Title>
                      <FontAwesomeIcon icon={faUserPlus} /> Add Hotel Owner
                    </Card.Title>
                    <Card.Text>Add a new hotel owner to the system.</Card.Text>
                    <Nav.Link as={Link} to="/addowner">
                      Add
                    </Nav.Link>
                  </Card.Body>
                </Card>
              </Col>

              <Col md={4}>
                <Card className="mb-3">
                  <Card.Body>
                    <Card.Title>
                      <FontAwesomeIcon icon={faUsersCog} /> Add Admin
                    </Card.Title>
                    <Card.Text>Add a new admin to the system.</Card.Text>
                    <Nav.Link as={Link} to="/addadmin"  className="nav-link-hover">
                      Add
                    </Nav.Link>
                  </Card.Body>
                </Card>
              </Col>

              <Col md={4}>
                <Card className="mb-3">
                  <Card.Body>
                    <Card.Title>
                      <FontAwesomeIcon icon={faHotel} /> List All Hotels
                    </Card.Title>
                    <Card.Text>View all registered hotels.</Card.Text>
                    <Nav.Link as={Link} to="/allhotels">
                      View
                    </Nav.Link>
                  </Card.Body>
                </Card>
              </Col>
              <Col md={4}>
                <Card className="mb-3">
                  <Card.Body>
                    <Card.Title>
                      <FontAwesomeIcon icon={faListAlt} /> List All User
                    </Card.Title>
                    <Card.Text>View all registered users.</Card.Text>
                    <Nav.Link as={Link} to="/listallusers">
                    View
                    </Nav.Link>
                  </Card.Body>
                </Card>
              </Col>

              <Col md={4}>
                <Card className="mb-3">
                  <Card.Body>
                    <Card.Title>
                      <FontAwesomeIcon icon={faListAlt} /> List All Hotel
                      Admins.
                    </Card.Title>
                    <Card.Text>View all registered hotel admins.</Card.Text>
                    <Nav.Link as={Link} to="/listalladmins">
                    View
                    </Nav.Link>
                  </Card.Body>
                </Card>
              </Col>

              <Col md={4}>
                <Card className="mb-3">
                  <Card.Body>
                    <Card.Title>
                      <FontAwesomeIcon icon={faListAlt} /> List All Hotel Owners
                    </Card.Title>
                    <Card.Text>View all registered hotel owners.</Card.Text>
                    <Nav.Link as={Link} to="/listallowners">
                    View
                    </Nav.Link>
                  </Card.Body>
                </Card>
              </Col>
            </Row>
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

export default AdminDashboard;
