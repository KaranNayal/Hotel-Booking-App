import React, { useState, useEffect } from "react";
import {
  Container,
  Row,
  Col,
  Nav,
  NavLink,
  Carousel,
  Button,
  Card,
  Form,
} from "react-bootstrap";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faSearch,
  faEnvelope,
  faPhone,
  faHotel,
  faUserEdit,
  faListAlt,
  faSignOutAlt,
} from "@fortawesome/free-solid-svg-icons";
import {
  faFacebook,
  faInstagram,
  faTwitter,
} from "@fortawesome/free-brands-svg-icons";
import UserService from "../services/UserService";
import chsLogo from "./chs.jpeg";
import HotelService from "../services/HotelService";
import AuthService from "../services/AuthService";
const UserDashboard = () => {
  const [userId, setUserId] = useState(null);
  const [user, setUser] = useState(null);
  const [city, setCity] = useState("");
  const [searchResults, setSearchResults] = useState([]);

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const response = await AuthService.fetchUserData();
        const fetchedUserId = response.data.userId;
        setUserId(fetchedUserId);
        const userResponse = await UserService.getUserById(fetchedUserId);
        setUser(userResponse.data);
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
      const response = await HotelService.getHotelsByLocation(city);
      setSearchResults(response.data);
    } catch (error) {
      console.error("Error searching hotels:", error);
    }
  };
  return (
    <div className="dashboard-container">
      <header className="header bg-dark text-white py-4 d-flex justify-content-between align-items-center">
        <Link
          className="navbar-brand d-flex align-items-center"
          to="/user-dashboard"
        >
          <img src={chsLogo} alt="Company Logo" height="40" className="me-2" />
          <span className="fw-bold" style={{ color: "#ffffff" }}>
            Cozy Heaven Stay
          </span>
        </Link>
        <div className="d-flex align-items-center">
          <h1 className="header-title m-0">
            Welcome {user ? user.userName : ""}
          </h1>
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
              <NavLink as={Link} to="/book-hotel" className="nav-link">
                <FontAwesomeIcon icon={faHotel} /> Book Hotel
              </NavLink>

              <NavLink as={Link} to="/edit-profile" className="nav-link">
                <FontAwesomeIcon icon={faUserEdit} /> Edit Profile
              </NavLink>
              <NavLink as={Link} to="/view-reservations" className="nav-link">
                <FontAwesomeIcon icon={faListAlt} /> View Reservations
              </NavLink>
              <Col md={8}>
                <Form.Control
                  type="text"
                  placeholder="Enter location"
                  value={city}
                  onChange={(e) => setCity(e.target.value)}
                />
              </Col>
              <Button variant="primary" onClick={handleSearch}>
                <FontAwesomeIcon icon={faSearch} /> Search
              </Button>
            </Nav>
          </Col>

          <Col sm={9} className="content p-4">
            <>
              <Container className="mt-4">
                <Row xs={1} md={2} lg={3} className="g-4">
                  {searchResults.map((hotel) => (
                    <Col key={hotel.hotelId}>
                      <Card>
                        <Card.Img
                          variant="top"
                          src="https://cdn.pixabay.com/photo/2016/04/15/11/48/hotel-1330850_1280.jpg"
                          style={{ width: "100%", height: "auto" }}
                        />
                        <Card.Body>
                          <Card.Title>{hotel.name}</Card.Title>
                          <Card.Text>
                            <strong>Hotel Id:</strong> {hotel.hotelId}
                            <br />
                            <strong>Name:</strong> {hotel.hotelName}
                            <br />
                            <strong>Location:</strong> {hotel.location}
                            <br />
                            <strong>Rating:</strong> {hotel.rating}
                            <br />
                            <strong>Amenities:</strong> {hotel.amenities}
                            <br />
                          </Card.Text>
                          <NavLink
                        as={Link}
                        to="/book-hotel"
                        className="btn btn-primary"
                      >
                       Book Now
                      </NavLink>
                        </Card.Body>
                      </Card>
                    </Col>
                  ))}
                </Row>
              </Container>
              <Carousel>
                <Carousel.Item>
                  <img
                    className="d-block w-100"
                    src="https://cdn.pixabay.com/photo/2015/10/13/15/19/bodrum-986308_1280.jpg"
                    alt="First slide"
                  />
                  <Carousel.Caption>
                    <h3>Discover Our Special Offers</h3>
                    <p>Book now and save on your next vacation!</p>
                  </Carousel.Caption>
                </Carousel.Item>
              </Carousel>
              <Row className="mt-4">
                <Col>
                  <h3>Special Discounts</h3>
                </Col>
              </Row>
              <Row>
                <Col md={4}>
                  <Card>
                    <Card.Img
                      variant="top"
                      src="https://cdn.pixabay.com/photo/2019/07/30/14/12/woman-4373078_1280.jpg"
                    />
                    <Card.Body>
                      <Card.Title>Summer Sale</Card.Title>
                      <Card.Text>
                        Get 20% off on bookings made before July 31st.
                      </Card.Text>
                      <NavLink
                        as={Link}
                        to="/special-offers"
                        className="btn btn-primary"
                      >
                        View Offer
                      </NavLink>
                    </Card.Body>
                  </Card>
                </Col>
                <Col md={4}>
                  <Card>
                    <Card.Img
                      variant="top"
                      src="https://cdn.pixabay.com/photo/2017/04/13/21/54/telluride-2228789_1280.jpg"
                    />
                    <Card.Body>
                      <Card.Title>Winter Discount</Card.Title>
                      <Card.Text>
                        Book now and enjoy 15% off on winter bookings.
                      </Card.Text>
                      <NavLink
                        as={Link}
                        to="/special-offers"
                        className="btn btn-primary"
                      >
                        View Offer
                      </NavLink>
                    </Card.Body>
                  </Card>
                </Col>
                <Col md={4}>
                  <Card>
                    <Card.Img
                      variant="top"
                      src="https://cdn.pixabay.com/photo/2019/02/26/05/44/fireworks-4021214_1280.jpg"
                    />
                    <Card.Body>
                      <Card.Title>New Year Special</Card.Title>
                      <Card.Text>
                        Book now and get a free room upgrade.
                      </Card.Text>
                      <NavLink
                        as={Link}
                        to="/special-offers"
                        className="btn btn-primary"
                      >
                        View Offer
                      </NavLink>
                    </Card.Body>
                  </Card>
                </Col>
              </Row>
              <Row className="mt-4">
                <Col>
                  <h3>Room Types</h3>
                </Col>
              </Row>
              <Row>
                {/* Room Types Cards */}
                <Col md={4}>
                  <Card>
                    <Card.Img
                      variant="top"
                      src="https://cdn.pixabay.com/photo/2020/10/18/09/16/bedroom-5664221_1280.jpg"
                    />

                    <Card.Body>
                      <Card.Title>Standard Room</Card.Title>
                      <Card.Text>
                        Enjoy a comfortable stay in our standard room.
                      </Card.Text>
                      <NavLink
                        as={Link}
                        to="/book-hotel"
                        className="btn btn-primary"
                      >
                        Book Now
                      </NavLink>
                    </Card.Body>
                  </Card>
                </Col>
                <Col md={4}>
                  <Card>
                    <Card.Img
                      variant="top"
                      src="https://cdn.pixabay.com/photo/2017/08/10/07/32/hotel-room-2619509_1280.jpg"
                    />

                    <Card.Body>
                      <Card.Title>Deluxe Room</Card.Title>
                      <Card.Text>
                        Experience luxury in our deluxe room.
                      </Card.Text>
                      <NavLink
                        as={Link}
                        to="/book-hotel"
                        className="btn btn-primary"
                      >
                        Book Now
                      </NavLink>
                    </Card.Body>
                  </Card>
                </Col>
                <Col md={4}>
                  <Card>
                    <Card.Img
                      variant="top"
                      src="https://cdn.pixabay.com/photo/2017/01/14/12/48/hotel-1979406_1280.jpg"
                    />

                    <Card.Body>
                      <Card.Title>Suite</Card.Title>
                      <Card.Text>
                        Indulge in our luxurious suite for a memorable stay.
                      </Card.Text>
                      <NavLink
                        as={Link}
                        to="/book-hotel"
                        className="btn btn-primary"
                      >
                        Book Now
                      </NavLink>
                    </Card.Body>
                  </Card>
                </Col>
              </Row>
            </>
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

export default UserDashboard;
