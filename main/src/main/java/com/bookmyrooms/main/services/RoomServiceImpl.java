package com.bookmyrooms.main.services;

import com.bookmyrooms.main.dtos.RoomDto;
import com.bookmyrooms.main.entities.properties.Property;
import com.bookmyrooms.main.entities.rooms.Amenities;
import com.bookmyrooms.main.entities.rooms.Room;
import com.bookmyrooms.main.entities.rooms.RoomImage;
import com.bookmyrooms.main.exceptions.ResourceNotFoundException;
import com.bookmyrooms.main.repository.AmenitiesRepository;
import com.bookmyrooms.main.repository.PropertyRepository;
import com.bookmyrooms.main.repository.RoomRepository;
import com.bookmyrooms.main.request.AddRoomRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import com.bookmyrooms.main.utilities.FileHandler;


@Service
public class RoomServiceImpl implements RoomService{
    @Value("${project.roomImages}")
    private String uploadPath;
    private final ModelMapper modelMapper;
    private final PropertyRepository propertyRepository;
    private final RoomRepository roomRepository;
    private final AmenitiesRepository amenitiesRepository;

    @Autowired
    public RoomServiceImpl(ModelMapper modelMapper, PropertyRepository propertyRepository, RoomRepository roomRepository, AmenitiesRepository amenitiesRepository) {
        this.modelMapper = modelMapper;
        this.propertyRepository = propertyRepository;
        this.roomRepository = roomRepository;
        this.amenitiesRepository = amenitiesRepository;
    }


    @Override
    public RoomDto addRoom(AddRoomRequest addRoomRequest, List<MultipartFile> files, Long propertyId) {
        // get the property
        Optional<Property> optional = this.propertyRepository.findById(propertyId);
        Property property = optional.orElseThrow(() -> new ResourceNotFoundException("Property", "id", String.valueOf(propertyId)));

        // check if that room number already exists







        // create a list of amenities
        List<Amenities> amenities = addRoomRequest.getAmenities().stream()
                .map((a) -> {
                    Optional<Amenities> optional1 = this.amenitiesRepository.findByName(a.toLowerCase());
                    Amenities amm = optional1.orElseThrow(() -> new ResourceNotFoundException("Ammenity", "name",a));
                    return amm;
                })
                .toList();

//        What is likely happening in your RoomServiceImpl.addRoom method:
//
//        You fetch the Amenities from the database (so they are "Persistent").
//
//                You create a new Room object (which is "Transient" / not yet saved).
//
//                You set the amenities on the room: room.setAmenities(amenities).
//
//                You call roomRepository.save(room).
//
//                Because you set up a Bidirectional @ManyToMany relationship earlier, the Amenities object now has a list of rooms that includes your new, unsaved Room. When Hibernate tries to flush the transaction, it looks at the Amenities entity, sees the unsaved Room, and crashes because it doesn't know how to save it.
        // create a room
        Room room = new Room(
                addRoomRequest.getRoomNumber(),
                addRoomRequest.getAreaInSqFt(),
                addRoomRequest.getRoomType(),
                addRoomRequest.getPricing().getBasePrice(),
                addRoomRequest.getPricing().getWeekendAdjustment(),
                addRoomRequest.getPricing().getCleaningFee(),
                addRoomRequest.getCapacity().getGuests(),
                addRoomRequest.getCapacity().getBedrooms(),
                addRoomRequest.getCapacity().getBeds(),
                addRoomRequest.getCapacity().getBathrooms(),
                true
        );

        // upload images
        for(MultipartFile file: files){
            String fileName = FileHandler.uploadImage(uploadPath, file);
            RoomImage roomImage = new RoomImage(fileName);

            // add image to room
            room.addImage(roomImage);
        }

        // add amenities
        room.setAmenities(amenities);

        room.setProperty(property);

        this.roomRepository.save(room);

        return this.toRoomDto(room);
    }

    @Override
    @Transactional
    public Page<RoomDto> getAllRooms(Pageable pageable) {
//        Page<Room> rooms = this.roomRepository.findAll(pageable);
//        Page<RoomDto> roomDtos = rooms.map(
//                room -> this.modelMapper.map(room, RoomDto.class)
//        );
//        return roomDtos;

//        return this.roomRepository.getAllRooms(pageable);
        return null;
    }

    @Override
    public boolean existsByPropertyPropertyIdAndRoomNumber(Long propertyId, Long roomId) {
        return this.roomRepository.existsByPropertyPropertyIdAndRoomNumber(propertyId, roomId);
    }


    // to room dto
    public RoomDto toRoomDto(Room room){
        RoomDto roomDto = modelMapper.map(room, RoomDto.class);

        // get a list of image names
        List<String> images = room.getImages()
                .stream()
                .map((i) -> i.getName())
                .toList();
        roomDto.setImageNames(images);

        return roomDto;
    }
}
