package com.bookmyrooms.main.configs;

import com.bookmyrooms.main.dtos.PropertyDto;
import com.bookmyrooms.main.entities.Property;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Utils {

    @Bean
    public ModelMapper modelMapper(){

        ModelMapper modelMapper = new ModelMapper();

//        modelMapper.typeMap(Property.class, PropertyDto.class)
//                .addMapping(
//                        s-> s.getLocation().getX(), PropertyDto::setLon
//                )
//                .addMapping(
//                        s-> s.getLocation().getY(), PropertyDto::setLat
//                );

        return modelMapper;
    }


}
