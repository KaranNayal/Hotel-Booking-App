import React, { useState, useEffect } from 'react';
import { Button } from 'react-bootstrap';
import { Link, useParams, useNavigate } from 'react-router-dom';
import HotelService from '../services/HotelService';
import AuthService from '../services/AuthService'; // Import your authentication service
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {faPhone, faEnvelope } from "@fortawesome/free-solid-svg-icons"; // Import missing icons
import chsLogo from "./chs.jpeg";
import {
    faFacebook,
    faInstagram,
    faTwitter,
  } from "@fortawesome/free-brands-svg-icons";
  
export const AddHotel = () => {
    const [hotelName, setHotelName] = useState('');
    const [location, setLocation] = useState('');
    const [amenities, setAmenities] = useState('');
    const [address, setAddress] = useState('');
    const [city, setCity] = useState('');
    const [state, setState] = useState('');
    const [country, setCountry] = useState('');
    const [rating, setRating] = useState('');
    const [imageUrl, setImageUrl] = useState('');
    const [ownerId, setOwnerId] = useState('');
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        const updateLocation = () => {
            setLocation(`${address}, ${city}, ${state}, ${country}`);
        };
        updateLocation();
    }, [address, city, state, country]);

    useEffect(() => {
        const fetchOwnerId = async () => {
            try {
                const response = await AuthService.fetchOwnerId();
                const ownerId = response.data.ownerId;
                setOwnerId(ownerId);
                localStorage.setItem('ownerId', ownerId);
                console.log("Logged in as: " + ownerId);
            } catch (error) {
                console.error('Error fetching owner ID:', error);
                if (error.response && error.response.status === 401) {
                    navigate('/login');
                }
            }
        };
        fetchOwnerId();
    }, []);

    const changeTitle = () => {
        if (id) {
            return <h2 className="text-center">Update Hotel</h2>;
        } else {
            return <h2 className="text-center">Add Hotel</h2>;
        }
    };

    const saveOrUpdateHotel = (e) => {
        e.preventDefault();
        const locationString = `${address}, ${city}, ${state}, ${country}`;
        const hotelDTO = { 
            hotelName, 
            location: locationString, 
            amenities, 
            address, 
            city, 
            state, 
            country, 
            rating,
            imageUrl
        };
        const ownerDTO = {
            userName: ownerId,
            ownerId: ownerId
        };
        const requestDTO = {
            hotelDTO,
            ownerDTO
        };
        if (id) {
            HotelService.updateHotel(id, hotelDTO)
                .then((response) => {
                    console.log('Hotel updated successfully:', response.data);
                    navigate('/hotel-dashboard');
                    alert('Hotel updated successfully');
                })
                .catch((error) => {
                    console.error('Error updating hotel:', error);
                });
        } else {
            HotelService.addHotel(requestDTO)
                .then((response) => {
                    console.log('Hotel added successfully:', response.data);
                    navigate('/owner-dashboard');
                    alert('Hotel added successfully');
                })
                .catch((error) => {
                    console.error('Error adding hotel:', error);
                });
        }
    };

    const handleRatingChange = (e) => {
        const value = parseInt(e.target.value, 10);
        if (value >= 1 && value <= 5) {
            setRating(value);
        }
    };

    return (
        <div>
            <nav className="navbar navbar-light bg-dark" style={{ fontFamily: "cursive", color: "#fff" }}>
                <div className="container-fluid">
                    <Link className="navbar-brand d-flex align-items-center" to="/owner-dashboard">
                        <img src={chsLogo} alt="Company Logo" height="40" className="me-2" />
                        <span className="fw-bold" style={{ color: "#ffffff" }}>Cozy Heaven Stay</span>
                    </Link>
                </div>
            </nav>
            <div className="container mt-4">
                <div className="row justify-content-center">
                    <div className="col-md-6">
                        <div className="card" style={{ background: 'linear-gradient(135deg, #f0f0f0, #ffffff)' }}>
                            <div className="card-body">
                                {changeTitle()}
                                <form>
                                    <div className="mb-3">
                                        <label className="form-label">Hotel Name</label>
                                        <input
                                            type="text"
                                            placeholder="Enter hotel name"
                                            name="hotelName"
                                            value={hotelName}
                                            className="form-control"
                                            onChange={(e) => setHotelName(e.target.value)}
                                        />
                                     </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">Amenities</label>
                                    <input
                                        type="text"
                                        placeholder="Enter amenities"
                                        name="amenities"
                                        value={amenities}
                                        className="form-control"
                                        onChange={(e) => setAmenities(e.target.value)}
                                    />
                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">Address</label>
                                    <input
                                        type="text"
                                        placeholder="Enter address"
                                        name="address"
                                        value={address}
                                        className="form-control"
                                        onChange={(e) => setAddress(e.target.value)}
                                    />
                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">City</label>
                                    <input
                                        type="text"
                                        placeholder="Enter city"
                                        name="city"
                                        value={city}
                                        className="form-control"
                                        onChange={(e) => setCity(e.target.value)}
                                    />
                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">State</label>
                                    <input
                                        type="text"
                                        placeholder="Enter state"
                                        name="state"
                                        value={state}
                                        className="form-control"
                                        onChange={(e) => setState(e.target.value)}
                                    />
                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">Country</label>
                                    <input
                                        type="text"
                                        placeholder="Enter country"
                                        name="country"
                                        value={country}
                                        className="form-control"
                                        onChange={(e) => setCountry(e.target.value)}
                                    />
                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">Location</label>
                                    <input
                                        type="text"
                                        placeholder="Enter location"
                                        name="location"
                                        value={location}
                                        className="form-control"
                                        disabled // Prevent editing
                                    />
                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">Rating</label>
                                    <input
                                        type="number"
                                        placeholder="Enter rating"
                                        name="rating"
                                        value={rating}
                                        className="form-control"
                                        onChange={handleRatingChange}
                                        min={1}
                                        max={5}
                                    />
                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">Image URL</label>
                                    <input
                                        type="text"
                                        placeholder="Enter image URL"
                                        name="imageUrl"
                                        value={imageUrl}
                                        className="form-control"
                                        onChange={(e) => setImageUrl(e.target.value)}
                                    />
                                </div>
                                    <Button onClick={(e) => saveOrUpdateHotel(e)} variant="success" className="float-start">
                                        Save Hotel
                                    </Button>
                                    <Link to="/owner-dashboard" className="btn btn-danger float-end">
                                        Cancel
                                    </Link>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            {/* Footer Section */}
            <footer className="login-footer mt-4" style={{ backgroundColor: "#343a40", color: "#ffffff", padding: "20px 0" }}>
                <div className="container">
                    <div className="row">
                        <div className="col-md-4 text-center">
                            <div style={{ marginBottom: "20px" }}>
                                <h5>About Us</h5>
                                <p>We are a company committed to providing the best accommodation services to our customers.</p>
                            </div>
                        </div>
                        <div className="col-md-4 text-center">
                            <div style={{ marginBottom: "20px" }}>
                                <h5>Contact Us</h5>
                                <p><FontAwesomeIcon icon={faPhone} /> +1 234 567 890</p>
                                <p><FontAwesomeIcon icon={faEnvelope} /> info@example.com</p>
                            </div>
                        </div>
                        <div className="col-md-4 text-center">
                            <div style={{ marginBottom: "20px" }}>
                                <h5>Follow Us</h5>
                                <p>
                                    <a href="https://www.facebook.com/example" target="_blank" rel="noopener noreferrer">
                                        <FontAwesomeIcon icon={faFacebook} />
                                    </a>
                                    &nbsp;&nbsp;&nbsp;
                                    <a href="https://www.twitter.com/example" target="_blank" rel="noopener noreferrer">
                                        <FontAwesomeIcon icon={faTwitter} />
                                    </a>
                                    &nbsp;&nbsp;&nbsp;
                                    <a href="https://www.instagram.com/example" target="_blank" rel="noopener noreferrer">
                                        <FontAwesomeIcon icon={faInstagram} />
                                    </a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="text-center mt-3" style={{ borderTop: "1px solid #fff", paddingTop: "10px" }}>
                    <p>© 2024 Cozy Heaven Stay. All rights reserved.</p>
                </div>
            </footer>
        </div>
    );
};

export default AddHotel;
