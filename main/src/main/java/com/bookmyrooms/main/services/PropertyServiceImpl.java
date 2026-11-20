package com.bookmyrooms.main.services;

import com.bookmyrooms.main.dtos.NearbyPropertyDto;
import com.bookmyrooms.main.dtos.PropertyDto;
import com.bookmyrooms.main.dtos.RoomDto;
import com.bookmyrooms.main.entities.PrivacyOption;
import com.bookmyrooms.main.entities.Property;
import com.bookmyrooms.main.entities.PropertyImage;
import com.bookmyrooms.main.entities.Room;
import com.bookmyrooms.main.exceptions.DuplicateException;
import com.bookmyrooms.main.exceptions.ResourceNotFoundException;
import com.bookmyrooms.main.repository.PrivacyOptionRepository;
import com.bookmyrooms.main.repository.PropertyRepository;
import com.bookmyrooms.main.request.PropertyListingRequest;
import jakarta.transaction.Transactional;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.annotation.JsonAppend;

import java.util.List;
import java.util.Optional;
import com.bookmyrooms.main.entities.misc.Location;
import com.bookmyrooms.main.utilities.FileHandler;


@Service
public class PropertyServiceImpl implements PropertyService {

    @Value("${project.propertyImages}")
    private String propertyImagesPath;
    private final GeometryFactory geometryFactory = new GeometryFactory();
    private final PropertyRepository propertyRepository;
    private final ModelMapper modelMapper;
    private final RoomService roomService;
    private final PrivacyOptionRepository privacyOptionRepository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository, ModelMapper modelMapper, RoomService roomService, PrivacyOptionRepository privacyOptionRepository) {
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
        this.roomService = roomService;
        this.privacyOptionRepository = privacyOptionRepository;
    }

    @Override
    public Page<NearbyPropertyDto> getPropertiesInRadius(double lon, double lat, Pageable pageable) {
//        return propertyRepository.findPropertiesWithinRadius(lon, lat, pageable);
        return null;
    }

    // add a new property
    @Override
    public PropertyDto saveProperty(PropertyListingRequest propertyListingRequest, MultipartFile file) {
        Point point = geometryFactory.createPoint(new Coordinate(propertyListingRequest.getLocation().getLongitude(), propertyListingRequest.getLocation().getLatitude()));

        Property property = new Property();
        property.setLocation(point);
        property.setTitle(propertyListingRequest.getTitle());
        property.setDescription(propertyListingRequest.getDescription());
        property.setPropertyType(propertyListingRequest.getPropertyStructure());
        property.setAddress(propertyListingRequest.getAddress());

        PrivacyOption privacyOption = privacyOptionRepository.findByType(
                propertyListingRequest.getPrivacyType()
        ).orElse(null);
        property.setPrivacyOption(privacyOption);

        System.out.println("Privacy option: "+ property.getPrivacyOption().getType());
        // save porperty
//        Property savedProperty = this.propertyRepository.save(property);

        // save image
        String fileName = FileHandler.uploadImage(propertyImagesPath, file);
        PropertyImage img = new PropertyImage(fileName);
        property.setPropertyImage(img);

        Property savedProperty = this.propertyRepository.save(property);

        return toPropertyDto(savedProperty);
    }


    @Override
    public Page<PropertyDto> getProperties(Pageable pageable) {
        Page<Property> properties = this.propertyRepository.findAll(pageable);

        Page<PropertyDto> propertyDtos = properties.map(this::toPropertyDto);
        return propertyDtos;
    }

    @Override
    public PropertyDto getPropertyById(Long propertyId) {
        Property property = this.getById(propertyId);
        return toPropertyDto(property);
    }


    @Override
    public boolean deleteProperty(Long propertyId) {
        if (this.propertyRepository.existsByPropertyId(propertyId)) {
            this.propertyRepository.deleteById(propertyId);
            return true;
        }

        throw new ResourceNotFoundException("property", "id", propertyId.toString());
    }


    // add room
    @Override
    @Transactional
    public RoomDto addRoom(Long propertyId, RoomDto roomDto) {
        if (this.roomService.existsByPropertyPropertyIdAndRoomNumber(propertyId, roomDto.getRoomNumber())) {
            throw new DuplicateException("Room", "Room Number", roomDto.getRoomNumber().toString());
        }
        Room room = modelMapper.map(roomDto, Room.class);
        Property property = this.getById(propertyId);
        property.addRoom(room);

        this.propertyRepository.save(property);
        return modelMapper.map(room, RoomDto.class);
    }


    // method to convert Property to PropertyDto
    private PropertyDto toPropertyDto(Property property){
        PropertyDto dto = new PropertyDto(
                property.getPropertyId(),
                property.getTitle(),
                property.getDescription(),
                property.getAddress(),
                property.getPrivacyOption(),
                property.isVerified()
        );

        if(property.getPropertyImage() != null){
            dto.setImageName(property.getPropertyImage().getImageName());
        }

        // if the location is not null then only try to convert point to longitude and latitude
        if(property.getLocation() != null){
            Location location = new Location(
                    property.getLocation().getX(),
                    property.getLocation().getY()
            );
            dto.setLocation(location);
        }

        return dto;
    }

    private Property getById(Long propertyId) {
        Optional<Property> optional = this.propertyRepository.findById(propertyId);
        Property property = optional.isPresent() ? optional.get() : null;
        return property;
    }


}
