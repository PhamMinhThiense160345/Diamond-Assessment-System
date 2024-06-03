package com.swp.DiamondAssesment;

import com.swp.DiamondAssesment.model.Payment;
import com.swp.DiamondAssesment.model.Role;
import com.swp.DiamondAssesment.model.Service;
import com.swp.DiamondAssesment.model.User;
import com.swp.DiamondAssesment.repository.paymentRepository;
import com.swp.DiamondAssesment.repository.roleRepository;
import com.swp.DiamondAssesment.repository.serviceRepository;
import com.swp.DiamondAssesment.repository.userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class DiamondAssesmentApplication {
    private final userRepository userRepository;
    private final roleRepository roleRepository;
    private final serviceRepository serviceRepository;
    private final paymentRepository paymentRepository;

    public static void main(String[] args) {
        SpringApplication.run(DiamondAssesmentApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                List<Role> roles = new ArrayList<>();
                Role role1 = Role.builder()
                        .RoleName("User")
                        .build();
                Role role2 = Role.builder()
                        .RoleName("Consulting staff")
                        .build();
                Role role3 = Role.builder()
                        .RoleName("Admin")
                        .build();
                Role role4 = Role.builder()
                        .RoleName("Manager")
                        .build();
                Role role5 = Role.builder()
                        .RoleName("Assessment staff")
                        .build();
                roles.add(role1);
                roles.add(role2);
                roles.add(role3);
                roles.add(role4);
                roles.add(role5);
                roleRepository.saveAll(roles);

                List<User> users = new ArrayList<>();
                User user1 = User.builder()
                        .name("RankillerDY")
                        .password("123")
                        .email("abc@gmail.com")
                        .phone("0326354391")
                        .address("123/321 Abc district")
                        .CCCD("0883018516")
                        .status(true)
                        .role_id(roleRepository.findById(1).orElse(null))
                        .build();
                User user2 = User.builder()
                        .name("FrosterDY")
                        .password("123")
                        .email("abc@gmail.com")
                        .phone("0326354391")
                        .address("123/321 Abc district")
                        .CCCD("0883018516")
                        .status(true)
                        .role_id(roleRepository.findById(5).orElse(null))
                        .build();
                users.add(user1);
                users.add(user2);
                userRepository.saveAll(users);

                List<Service> services = new ArrayList<>();
                Service service1 = Service.builder()
                        .serviceName("Consulting Service")
                        .build();
                Service service2 = Service.builder()
                        .serviceName("Admin Service")
                        .build();
                services.add(service1);
                services.add(service2);
                serviceRepository.saveAll(services);

                List<Payment> payments = new ArrayList<>();
                Payment payment1 = Payment.builder()
                        .TSDate(new Date()) // Set the transaction date to now
                        .serviceType("Consulting Service")
                        .totalAmount(500) // Example amount
                        .status(true)
                        .user_id(userRepository.findById(1).orElse(null)) // Associate with the fetched user
                        .build();
                Payment payment2 = Payment.builder()
                        .TSDate(new Date()) // Set the transaction date to now
                        .serviceType("Admin Service")
                        .totalAmount(500) // Example amount
                        .status(true)
                        .user_id(userRepository.findById(2).orElse(null)) // Associate with the fetched user
                        .build();
                payments.add(payment1);
                payments.add(payment2);
                paymentRepository.saveAll(payments);
            }
        };
    }
}
