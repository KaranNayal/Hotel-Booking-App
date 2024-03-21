import React, { useState, useEffect } from "react";
import { useParams, useNavigate, Link } from "react-router-dom";
import { Alert, Form } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faPlusCircle,
  faTimesCircle,
  faInfoCircle,
} from "@fortawesome/free-solid-svg-icons";
import RoomService from "../services/RoomService";
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
import { Button, Row, Container, Col } from "react-bootstrap";
const AddRoom = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [roomData, setRoomData] = useState({
    roomNumber: "",
    bedType: null,
    availability: "AVAILABLE",
    maxOccupancy: 4,
    acStatus: false,
    baseFare: 0.0,
    roomType: null,
    hotelId: id || "",
  });

  const [showAlert, setShowAlert] = useState(false);
  const [alertMessage, setAlertMessage] = useState("");
  const [cardHeight, setCardHeight] = useState(0); // State to store card height

  useEffect(() => {
    // Get the height of the card when it is mounted
    const card = document.getElementById("room-container");
    if (card) {
      const height = card.offsetHeight;
      setCardHeight(height);
    }
  }, []);

  useEffect(() => {
    
    // Fetch the last added room number and increment it by 1
    const fetchLastRoomNumber = async () => {
      try {
        const lastRoom = await RoomService.getLastAddedRoom(id);
        if (lastRoom && lastRoom.data) {
          const lastRoomNumber = parseInt(lastRoom.data);
          if (!isNaN(lastRoomNumber)) {
            const nextRoomNumber = lastRoomNumber + 1;
            setRoomData((prevState) => ({
              ...prevState,
              roomNumber: nextRoomNumber.toString(),
            }));
          }
        }
      } catch (error) {
        console.error("Error fetching last room number:", error);
      }
    };
    fetchLastRoomNumber();
  }, []);

  useEffect(() => {
    const updateBaseFare = () => {
      setRoomData((prevState) => ({
        ...prevState,
        baseFare: calculateBaseFare(
          prevState.roomType,
          prevState.bedType,
          prevState.acStatus
        ),
      }));
    };
    updateBaseFare();
  }, [roomData.roomType, roomData.bedType, roomData.acStatus]);

  const calculateBaseFare = (roomType, bedType, acStatus) => {
    let baseFare = 0;
    switch (roomType) {
      case "STANDARD":
        switch (bedType) {
          case "SINGLE":
            baseFare = 2000;
            break;
          case "DOUBLE":
            baseFare = 2500;
            break;
          case "KING":
            baseFare = 3000;
            break;
          default:
            baseFare = 2000;
        }
        break;
      case "DELUXE":
        switch (bedType) {
          case "DOUBLE":
            baseFare = 4500;
            break;
          case "KING":
            baseFare = 5500;
            break;
          default:
            baseFare = 4500;
        }
        break;
      case "SUITE":
        baseFare = 7300;
        setRoomData((prevState) => ({
          ...prevState,
          bedType: "KING",
          acStatus: true,
        })); 
        break;
      default:
        baseFare = 7300;
    }
   
    if (acStatus) {
      baseFare += 200;
    }
    return baseFare;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const existingRoom = await RoomService.getRoomByRoomNumberAndHotelId(
        roomData.roomNumber,
        roomData.hotelId
      );
      if (existingRoom) {
        setAlertMessage(
          `Error: Room ${roomData.roomNumber} already exists for this hotel.`
        );
        setShowAlert(true);
      } else {
        const response = await RoomService.addRoom({
          ...roomData,
          hotelId: id,
        });
        if (response.status === 201) {
          setAlertMessage(
            `The room has been added successfully! The room number is ${response.data.roomNumber}`
          );
          setShowAlert(true);
          // Fetch the last added room number and increment it by 1 for the next room
          const lastRoomNumber = parseInt(response.data.roomNumber);
          setRoomData((prevState) => ({
            ...prevState,
            roomNumber: (lastRoomNumber + 1).toString(),
          }));
          // Reset form fields or navigate to another page
        } else {
          setAlertMessage(`Error adding room: ${response.statusText}`);
          setShowAlert(true);
        }
      }
    } catch (error) {
      if (error.response && error.response.status === 400) {
        // Handle constraint violation error
        setAlertMessage(
          "Error: Could not add room. Please check the provided data."
        );
        setShowAlert(true);
      } else if (error.response && error.response.status === 404) {
        // Room not found, attempt to add the room again
        try {
          const response = await RoomService.addRoom({
            ...roomData,
            hotelId: id,
          });
          if (response.status === 201) {
            setAlertMessage(
              `The room has been added successfully! The room number is ${response.data.roomNumber}`
            );
            setShowAlert(true);
            // Fetch the last added room number and increment it by 1 for the next room
            const lastRoomNumber = parseInt(response.data.roomNumber);
            setRoomData((prevState) => ({
              ...prevState,
              roomNumber: (lastRoomNumber + 1).toString(),
            }));
            // Reset form fields or navigate to another page
          } else {
            setAlertMessage(`Error adding room: ${response.statusText}`);
            setShowAlert(true);
          }
        } catch (error) {
          setAlertMessage(`Error adding room: ${error.message}`);
          setShowAlert(true);
        }
      } else {
        setAlertMessage(`Error: ${error.message}`);
        setShowAlert(true);
      }
    }
  };

  return (
    <div className="room-container">
      <header className="header bg-dark text-white py-4 d-flex justify-content-between align-items-center">
        <div className="d-flex align-items-center">
          <Link
            className="navbar-brand d-flex align-items-center"
            to="/owner-dashboard"
          >
            <img
              src={chsLogo}
              alt="Company Logo"
              height="40"
              className="me-2"
            />
            <span className="fw-bold">Cozy Heaven Stay</span>
          </Link>
        </div>
      </header>
      <div className="row mt-4">
        <div
          className="col-lg-6"
          style={{
            backgroundImage: `url(https://cdn.pixabay.com/photo/2016/10/18/09/02/hotel-1749602_1280.jpg)`,
            minHeight: cardHeight,
            backgroundPosition: "15px center ",
          }}
        ></div>

        <div className=" room-container col-lg-6">
          <Form onSubmit={handleSubmit} className="mt-4">
            <Form.Group>
              <Form.Label>Room Number:</Form.Label>
              <Form.Control
                type="text"
                value={roomData.roomNumber}
                onChange={(e) =>
                  setRoomData((prevState) => ({
                    ...prevState,
                    roomNumber: e.target.value,
                  }))
                }
              />
            </Form.Group>
            <Form.Group>
              <Form.Label>Room Type:</Form.Label>
              <Form.Control
                as="select"
                value={roomData.roomType || ""}
                onChange={(e) =>
                  setRoomData((prevState) => ({
                    ...prevState,
                    roomType: e.target.value,
                    baseFare: calculateBaseFare(
                      e.target.value,
                      prevState.bedType,
                      prevState.acStatus
                    ),
                  }))
                }
              >
                <option value={null}>Select One</option>
                <option value="STANDARD">Standard</option>
                <option value="DELUXE">Deluxe</option>
                <option value="SUITE">Suite</option>
              </Form.Control>
            </Form.Group>
            <Form.Group>
              <Form.Label>Bed Type:</Form.Label>
              <Form.Control
                as="select"
                value={roomData.bedType || ""}
                onChange={(e) =>
                  setRoomData((prevState) => ({
                    ...prevState,
                    bedType: e.target.value,
                    baseFare: calculateBaseFare(
                      prevState.roomType,
                      e.target.value,
                      prevState.acStatus
                    ),
                  }))
                }
              >
                <option value={null}>Select One</option>
                {roomData.roomType === "STANDARD" && (
                  <option value="SINGLE">Single Bed</option>
                )}
                {(roomData.roomType === "STANDARD" ||
                  roomData.roomType === "DELUXE") && (
                  <option value="DOUBLE">Double Bed</option>
                )}
                {(roomData.roomType === "STANDARD" ||
                  roomData.roomType === "DELUXE" ||
                  roomData.roomType === "SUITE") && (
                  <option value="KING">King Size Bed</option>
                )}
              </Form.Control>
            </Form.Group>
            <Form.Group>
              <Form.Label>Max Occupancy:</Form.Label>
              <Form.Control
                as="select"
                value={roomData.maxOccupancy || ""}
                onChange={(e) =>
                  setRoomData((prevState) => ({
                    ...prevState,
                    maxOccupancy: parseInt(e.target.value),
                  }))
                }
              >
                {[...Array(4)].map((_, index) => (
                  <option key={index} value={index + 1}>
                    {index + 1}
                  </option>
                ))}
              </Form.Control>
            </Form.Group>
            <Form.Group>
              <Form.Label>AC Status:</Form.Label>
              <Form.Check
                type="switch"
                id="acSwitch"
                label="AC"
                checked={roomData.acStatus}
                onChange={(e) =>
                  setRoomData((prevState) => ({
                    ...prevState,
                    acStatus: e.target.checked,
                    baseFare: calculateBaseFare(
                      prevState.roomType,
                      prevState.bedType,
                      e.target.checked
                    ),
                  }))
                }
                disabled={roomData.roomType === "SUITE"} // Disable AC switch if room type is Suite
              />
            </Form.Group>
            <Form.Group>
              <Form.Label>Base Fare:</Form.Label>
              <Form.Control
                type="number"
                step="0.01"
                value={roomData.baseFare || ""}
                onChange={(e) =>
                  setRoomData((prevState) => ({
                    ...prevState,
                    baseFare: parseFloat(e.target.value),
                  }))
                }
              />
            </Form.Group>
            <Button type="submit" variant="primary" className="mt-4">
              <FontAwesomeIcon icon={faPlusCircle} className="mr-2" />
              Add Room
            </Button>
            <Button
              type="button"
              variant="secondary"
              className="mt-4"
              onClick={() => navigate("/owner-dashboard")}
            >
              <FontAwesomeIcon icon={faTimesCircle} className="mr-2" />
              Cancel
            </Button>
          </Form>
          {showAlert && (
            <Alert
              variant="danger"
              onClose={() => setShowAlert(false)}
              dismissible
            >
              <FontAwesomeIcon icon={faInfoCircle} className="mr-2" />
              {alertMessage}
            </Alert>
          )}
        </div>
      </div>
      <footer
        className="login-footer mt-4"
        style={{
          backgroundColor: "#343a40",
          color: "#ffffff",
          padding: "20px 0",
          marginTop: "auto",
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

export default AddRoom;
