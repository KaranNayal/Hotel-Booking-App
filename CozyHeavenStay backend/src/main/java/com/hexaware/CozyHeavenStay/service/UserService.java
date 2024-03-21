package com.hexaware.CozyHeavenStay.service;

import com.hexaware.CozyHeavenStay.dto.HotelDTO;
import com.hexaware.CozyHeavenStay.dto.ReservationDTO;
import com.hexaware.CozyHeavenStay.dto.ReservationRequestDTO;
import com.hexaware.CozyHeavenStay.dto.SearchRequestDTO;
import com.hexaware.CozyHeavenStay.dto.UserDTO;
import com.hexaware.CozyHeavenStay.entities.Reservation;
import com.hexaware.CozyHeavenStay.entities.User;

import java.util.List;

public interface UserService {

    UserDTO getUserById(Long userId);

    List<UserDTO> getAllUsers();

    UserDTO saveUser(UserDTO userDTO);
    
    UserDTO updateUser(Long userId, UserDTO userDTO);
    
    void deleteUser(Long userId);

    List<UserDTO> getUsersByGender(String gender);

    List<UserDTO> getUsersByAddress(String address);

    List<UserDTO> getUsersByContactNo(String contactNo);

    List<UserDTO> getUsersByUserNameContaining(String keyword);

    List<HotelDTO> searchHotels(SearchRequestDTO searchRequest);

    //ReservationDTO makeReservation(ReservationRequestDTO reservationRequestDTO);

    void cancelReservation(Long reservationId);

    //List<ReservationDTO> getReservationHistory(Long userId);
	
	Long findUserIdByUsername(String username);

	User findUserByUsername(String username);

	UserDTO getUserByUsername(String userName);

	


    
}
