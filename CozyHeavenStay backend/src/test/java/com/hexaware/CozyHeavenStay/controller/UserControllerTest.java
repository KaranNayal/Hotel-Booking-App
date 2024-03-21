package com.hexaware.CozyHeavenStay.controller;

import com.hexaware.CozyHeavenStay.dto.*;
import com.hexaware.CozyHeavenStay.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetUserById() {
        UserDTO userDTO = new UserDTO();
        Long userId = 1L;
        when(userService.getUserById(userId)).thenReturn(userDTO);

        UserDTO result = userController.getUserById(userId);

        assertEquals(userDTO, result);
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    void testGetAllUsers() {
        List<UserDTO> users = Collections.singletonList(new UserDTO());
        when(userService.getAllUsers()).thenReturn(users);

        List<UserDTO> result = userController.getAllUsers();

        assertEquals(users, result);
        verify(userService, times(1)).getAllUsers();
    }


    @Test
    void testDeleteUser() {
        Long userId = 1L;

        userController.deleteUser(userId);

        verify(userService, times(1)).deleteUser(userId);
    }

//    @Test
//    void testGetUserByEmail() {
//        UserDTO userDTO = new UserDTO();
//        String email = "example@example.com";
//        when(userService.getUserByEmail(email)).thenReturn(userDTO);
//
//        UserDTO result = userController.getUserByEmail(email);
//
//        assertEquals(userDTO, result);
//        verify(userService, times(1)).getUserByEmail(email);
//    }
//
//    @Test
//    void testGetUsersByUserName() {
//        List<UserDTO> users = Collections.singletonList(new UserDTO());
//        String userName = "example";
//        when(userService.getUsersByUserName(userName)).thenReturn(users);
//
//        List<UserDTO> result = userController.getUsersByUserName(userName);
//
//        assertEquals(users, result);
//        verify(userService, times(1)).getUsersByUserName(userName);
//    }

    @Test
    void testGetUsersByGender() {
        List<UserDTO> users = Collections.singletonList(new UserDTO());
        String gender = "Male";
        when(userService.getUsersByGender(gender)).thenReturn(users);

        List<UserDTO> result = userController.getUsersByGender(gender);

        assertEquals(users, result);
        verify(userService, times(1)).getUsersByGender(gender);
    }

    @Test
    void testGetUsersByAddress() {
        List<UserDTO> users = Collections.singletonList(new UserDTO());
        String address = "Example Address";
        when(userService.getUsersByAddress(address)).thenReturn(users);

        List<UserDTO> result = userController.getUsersByAddress(address);

        assertEquals(users, result);
        verify(userService, times(1)).getUsersByAddress(address);
    }

    @Test
    void testGetUsersByContactNo() {
        List<UserDTO> users = Collections.singletonList(new UserDTO());
        String contactNo = "1234567890";
        when(userService.getUsersByContactNo(contactNo)).thenReturn(users);

        List<UserDTO> result = userController.getUsersByContactNo(contactNo);

        assertEquals(users, result);
        verify(userService, times(1)).getUsersByContactNo(contactNo);
    }

    @Test
    void testGetUsersByUserNameContaining() {
        List<UserDTO> users = Collections.singletonList(new UserDTO());
        String keyword = "example";
        when(userService.getUsersByUserNameContaining(keyword)).thenReturn(users);

        List<UserDTO> result = userController.getUsersByUserNameContaining(keyword);

        assertEquals(users, result);
        verify(userService, times(1)).getUsersByUserNameContaining(keyword);
    }

    @Test
    void testSearchHotels() {
        SearchRequestDTO searchRequestDTO = new SearchRequestDTO();
        List<HotelDTO> hotels = Collections.singletonList(new HotelDTO());
        when(userService.searchHotels(searchRequestDTO)).thenReturn(hotels);

        List<HotelDTO> result = userController.searchHotels(searchRequestDTO);

        assertEquals(hotels, result);
        verify(userService, times(1)).searchHotels(searchRequestDTO);
    }

//    @Test
//    void testMakeReservation() {
//        ReservationRequestDTO reservationRequestDTO = new ReservationRequestDTO();
//        ReservationDTO reservationDTO = new ReservationDTO();
//        when(userService.makeReservation(reservationRequestDTO)).thenReturn(reservationDTO);
//
//        ReservationDTO result = userController.makeReservation(reservationRequestDTO);
//
//        assertEquals(reservationDTO, result);
//        verify(userService, times(1)).makeReservation(reservationRequestDTO);
//    }

    @Test
    void testCancelReservation() {
        Long reservationId = 1L;

        userController.cancelReservation(reservationId);

        verify(userService, times(1)).cancelReservation(reservationId);
    }
//
//    @Test
//    void testGetReservationHistory() {
//        Long userId = 1L;
//        List<ReservationDTO> reservations = Collections.singletonList(new ReservationDTO());
//        when(userService.getReservationHistory(userId)).thenReturn(reservations);
//
//        List<ReservationDTO> result = userController.getReservationHistory(userId);
//
//        assertEquals(reservations, result);
//        verify(userService, times(1)).getReservationHistory(userId);
//    }
}