package com.hexaware.CozyHeavenStay.service.serviceimpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.CozyHeavenStay.dto.HotelDTO;
import com.hexaware.CozyHeavenStay.dto.HotelOwnerDTO;
import com.hexaware.CozyHeavenStay.dto.ReviewDTO;
import com.hexaware.CozyHeavenStay.dto.RoomDTO;
import com.hexaware.CozyHeavenStay.entities.Hotel;
import com.hexaware.CozyHeavenStay.entities.HotelOwner;
import com.hexaware.CozyHeavenStay.entities.Review;
import com.hexaware.CozyHeavenStay.entities.Room;
import com.hexaware.CozyHeavenStay.entities.Room.RoomType;
import com.hexaware.CozyHeavenStay.repository.HotelRepository;
import com.hexaware.CozyHeavenStay.service.HotelOwnerService;
import com.hexaware.CozyHeavenStay.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
   
    @Autowired
    private final ModelMapper modelMapper;
    private final HotelOwnerService hotelOwnerService;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository, ModelMapper modelMapper, HotelOwnerService hotelOwnerService) {
        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
        this.hotelOwnerService = hotelOwnerService;
    }
    

//    @Override
//    public HotelDTO addHotel(HotelDTO hotelDTO) {
//        // Convert HotelDTO to Hotel entity
//        Hotel hotel = modelMapper.map(hotelDTO, Hotel.class);
//        
//        // Save the hotel entity
//        hotel = hotelRepository.save(hotel);
//        
//        // Convert the saved Hotel entity back to HotelDTO and return
//        return modelMapper.map(hotel, HotelDTO.class);
//    }

    @Override
    public HotelDTO addHotel(HotelDTO hotelDTO) {
        // Get the hotel owner details by ownerId
        HotelOwnerDTO ownerDTO = hotelOwnerService.getHotelOwnerById(hotelDTO.getOwnerId());

        // Map HotelDTO to Hotel entity
        Hotel hotel = modelMapper.map(hotelDTO, Hotel.class);
        
        // Set the hotel owner
        hotel.setHotelOwner(modelMapper.map(ownerDTO, HotelOwner.class));

        // Save hotel entity
        hotel = hotelRepository.save(hotel);

        // Map saved hotel entity back to DTO and return
        return modelMapper.map(hotel, HotelDTO.class);
    }


    @Override
    public HotelDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElse(null);
        return (hotel != null) ? modelMapper.map(hotel, HotelDTO.class) : null;
    }

    @Override
    public List<HotelDTO> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream().map(hotel -> modelMapper.map(hotel, HotelDTO.class)).collect(Collectors.toList());
    }

    @Override
    public HotelDTO updateHotel(Long id, HotelDTO hotelDTO) {
        if (hotelRepository.existsById(id)) {
            Hotel updatedHotel = modelMapper.map(hotelDTO, Hotel.class);
            updatedHotel.setHotelId(id);
            return modelMapper.map(hotelRepository.save(updatedHotel), HotelDTO.class);
        }
        return null; // or throw an exception indicating hotel not found
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public List<RoomDTO> getHotelRooms(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElse(null);
        if (hotel != null) {
            List<Room> rooms = hotel.getRooms();
            return rooms.stream().map(room -> modelMapper.map(room, RoomDTO.class)).collect(Collectors.toList());
        }
        return Collections.emptyList(); // or throw an exception indicating hotel not found
    }

    @Override
    public List<ReviewDTO> getHotelReviews(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElse(null);
        if (hotel != null) {
            List<Review> reviews = hotel.getReviews();
            return reviews.stream().map(review -> modelMapper.map(review, ReviewDTO.class)).collect(Collectors.toList());
        }
        return Collections.emptyList(); // or throw an exception indicating hotel not found
    }

    @Override
    public List<HotelDTO> getHotelsByLocation(String city) {
        List<Hotel> hotels = hotelRepository.findByCity(city);
        return hotels.stream().map(hotel -> modelMapper.map(hotel, HotelDTO.class)).collect(Collectors.toList());
    }


    @Override
    public List<HotelDTO> getHotelsByAmenities(String amenities) {
        List<Hotel> hotels = hotelRepository.findByAmenitiesContaining(amenities);
        return hotels.stream().map(hotel -> modelMapper.map(hotel, HotelDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<HotelDTO> getHotelsWithRoomType(String roomType) {
        // Convert the string representation to RoomType enum
        RoomType type = RoomType.valueOf(roomType.toUpperCase());
        List<Hotel> hotels = hotelRepository.findByRoomsRoomType(type);
        return hotels.stream().map(hotel -> modelMapper.map(hotel, HotelDTO.class)).collect(Collectors.toList());
    }
    
    @Override
    public List<HotelDTO> getHotelsByOwnerId(Long ownerId) {
        List<Hotel> hotels = hotelRepository.findByOwnerId(ownerId);
        return mapToDTOList(hotels);
    }

    // Utility method to convert List<Hotel> to List<HotelDTO>
    private List<HotelDTO> mapToDTOList(List<Hotel> hotels) {
        return hotels.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Utility method to map Hotel to HotelDTO
    private HotelDTO mapToDTO(Hotel hotel) {
        HotelDTO dto = new HotelDTO();
        dto.setHotelId(hotel.getHotelId());
        dto.setHotelName(hotel.getHotelName());
        dto.setAddress(hotel.getAddress());
        dto.setCity(hotel.getCity());
        dto.setState(hotel.getState());
        dto.setCountry(hotel.getCountry());
        dto.setRating(hotel.getRating());
        dto.setAmenities(hotel.getAmenities());
        dto.setOwnerId(hotel.getOwnerId());
        return dto;
    }
}
