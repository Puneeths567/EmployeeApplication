package com.directory.EmployeeApplication.config;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Optional: Configure custom mappings or converters
        // Example: modelMapper.addMappings(new PropertyMap<Source, Destination>() {
        //     @Override
        //     protected void configure() {
        //         map().setSomeProperty(source.getSomeProperty());
        //     }
        // });

        return modelMapper;
    }
}
