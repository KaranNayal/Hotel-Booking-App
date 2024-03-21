import React from 'react';
import AddUser from './components/AddUser';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { Error } from './components/Error';
import Login from './components/Login';
import UserDashboard from './components/UserDashboard';
import BookRoom from './components/BookRoom';
import OwnerDashboard from './components/OwnerDashboard';
import AddHotel from './components/AddHotel';
// import ListHotel from './components/ListHotels';
import ListUser from './components/ListUser';
import ListRoom from './components/ListRoom';
import HomePage from './components/HomePage';
import AboutUsPage from './components/AboutUsPage';
import EditProfile from './components/EditProfile';
import ListHotelByOwner from './components/ListHotelByOwner';   
   import HotelDetail from './components/HotelDetail';
//import AddRoom from './components/AddRoom';
import AdminDashboard from './components/AdminDashboard';

import AddOwner from './components/AddOwner';
import AddAdmin from './components/AddAdmin';
import EditOwnerProfile from './components/EditOwnerProfile';
import ListOwner from './components/ListOwner';
import ListAdmin from './components/ListAdmin';
import AllHotel from './components/AllHotel';
import AddRoom from './components/AddRoom';
import AllHotelWithBooking from './components/AllHotelWithBooking';
import UserReservations from './components/UserReservations';
import PasswordResetForm from './components/PasswordResetForm';

function App() {
  return (
    <div className="App" >
      <BrowserRouter>
        <div className="container-fluid ">
          <Routes>
          <Route path="/addowner" element={<AddOwner/>}/>
        <Route path="/addadmin" element={<AddAdmin/>}/>
        <Route path="/editownerprofile" element={<EditOwnerProfile/>}/>
            <Route path="/" element={<HomePage />} />
            <Route path="/aboutus" element={<AboutUsPage />} />
            {/* <Route path="/allhotels" element={<AllHotels />} /> */}
            <Route path="/roomadd" element={<AddRoom/>}/>
            {/* <Route path="/reservation-form" element={<ReservationForm/>} /> */}
            {/* <Route path="/edit-hotel/:hotelId" element={<EditHotel/>} /> */}
                        <Route path="/adduser" element={<AddUser />} />
            <Route path="/update/:id" element={<AddUser />} />
            <Route path="/edit-profile" element={<EditProfile />} />
            <Route path="/login" element={<Login />} />
            <Route path="/user-dashboard" element={<UserDashboard />} />
            <Route path="/admin-dashboard" element={<AdminDashboard />} />
            <Route path="/book-room" element={<BookRoom />} />
            <Route path="/owner-dashboard" element={<OwnerDashboard />} />
            <Route path="/addhotel" element={<AddHotel />} />
            {/* <Route path="/listallhotels" element={<ListHotel />} /> */}
            <Route path="/listallusers" element={<ListUser />} />
            {/* <Route path="/listallhotels" element={<ListHotel/>}/> */}
            <Route path="/homepage" element={<HomePage/>}/> 
        <Route path="/adduser" element={<AddUser/>}/>
        <Route path="/update/:id" element={< AddUser/>}/>
        <Route path="/login" element={< Login/>}/>
        <Route path="/user-dashboard" element={<UserDashboard/>}/>
        <Route path="/book-room" element={<BookRoom/>}/>
        <Route path="/owner-dashboard" element={<OwnerDashboard/>}/>
        <Route path="/addhotel" element={<AddHotel/>}/>
        {/* <Route path="/listallhotels" element={<ListHotel/>}/> */}
        <Route path="/listallusers" element={<ListUser/>}/>
        <Route path="/listallrooms" element={<ListRoom/>}/>
        <Route path="/updatehotel/:id" element={< AddHotel/>}/>
        <Route path="/updateuser/:id" element={< AddUser/>}/>
        <Route path="/listallowners" element={<ListOwner/>}/>
        <Route path="/listalladmins" element={<ListAdmin/>}/>
        {/* <Route path="/myhotels" element={<HotelListByOwner/>}/> */}
        <Route path="/hotel/:id" element={<HotelDetail/>}/>
        <Route path="/hotel/:id/add-room" element={<AddRoom/>}/>
        <Route path="/aboutus" element={<AboutUsPage/>}/>
        <Route path="/edit-profile" element={<EditProfile/>}/>
        <Route path="/updateroom/:id" element={< AddRoom/>}/>
        <Route path="/addowner" element={<AddOwner/>}/>
        <Route path="/addadmin" element={<AddAdmin/>}/>
        <Route path="/allhotels" element={<AllHotel/>}/>
        {/* <Route path="/hotel/:id" component={HotelDetail} /> */}
        {/* <Route path="/admin-dashboard" element={<AdminDashboard/>}/> */}
        <Route path="/listallusers" element={<ListUser/>}/>
        <Route path="/listallrooms" element={<ListRoom/>}/>
        <Route path="/updatehotel/:id" element={< AddHotel/>}/>
        <Route path="/updateuser/:id" element={< AddUser/>}/>
        <Route path="/listallowners" element={<ListOwner/>}/>
            <Route path="/updatehotel/:id" element={<AddHotel />} />
            <Route path="/updateuser/:id" element={<AddUser />} />
            <Route path="/listallrooms" element={<ListRoom />} />
            <Route path="/myhotels" element={<ListHotelByOwner />} />
            {/* <Route path="/updateuser/:id" element={< AddUser/>}/> */}
        <Route path="/listallowners" element={<ListOwner/>}/>
        <Route path="/listalladmins" element={<ListAdmin/>}/>
            {/* <Route path="/hotel/:id" element={<HotelDetail />} /> */}
            {/* <Route path="/hotel/:id/add-room" element={<AddRoom />} /> */}
            <Route path="/book-hotel" element={<AllHotelWithBooking/>}/>
            <Route path="/book-room/:id" element={<BookRoom/>}/>
            <Route path="/view-reservations" element={<UserReservations/>}/>
            <Route path="/reset" element={<PasswordResetForm/>} />
            <Route path="*" element={<Error />} />
          </Routes>
        </div>
      </BrowserRouter>
    </div>
  );
}

export default App;
