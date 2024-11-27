package com.example.api.inquiry;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InquiryConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
