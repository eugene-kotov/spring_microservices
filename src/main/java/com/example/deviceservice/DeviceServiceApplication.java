package com.example.deviceservice;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.stream.Stream;

@SpringBootApplication
public class DeviceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(DeviceRepository deviceRepository) {
        return strings -> {
            Stream.of(new Device("iPhone SE 2020"),
                    new Device("MacBook Pro 16"),
                    new Device("iPad Pro 2020"))
                    .forEach(deviceRepository::save);

            deviceRepository.findAll().forEach(System.out::println);
        };
    }
}

interface DeviceRepository extends JpaRepository<Device, Long> {

}

@Data
@Entity
@NoArgsConstructor
class Device {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Device(String name) {
        this.name = name;
    }
}
