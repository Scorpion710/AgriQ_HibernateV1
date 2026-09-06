package com.hibernate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hibernate.entity.Consumer;
import com.hibernate.repository.ConsumerRepository;

@Service
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    public ConsumerService(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    public Consumer registerConsumer(Consumer consumer) {
        if (consumer.getName() == null || consumer.getName().trim().isBlank()) {
            throw new RuntimeException("Consumer name is required.");
        }
        if (consumer.getMobileNumber() == null || !consumer.getMobileNumber().trim().matches("^[0-9]{10}$")) {
            throw new RuntimeException("Please enter a valid 10-digit mobile number.");
        }
        if (consumer.getPin() == null || !consumer.getPin().trim().matches("^[0-9]{4}$")) {
            throw new RuntimeException("PIN must be a 4-digit numeric code.");
        }

        consumer.setName(consumer.getName().trim());
        consumer.setMobileNumber(consumer.getMobileNumber().trim());
        consumer.setPin(consumer.getPin().trim());
        if (consumer.getAddress() != null) consumer.setAddress(consumer.getAddress().trim());
        if (consumer.getCity() != null) consumer.setCity(consumer.getCity().trim());

        if (consumerRepository.findByMobileNumber(consumer.getMobileNumber()).isPresent()) {
            throw new RuntimeException("A consumer account with this mobile number already exists.");
        }

        return consumerRepository.save(consumer);
    }

    public Consumer loginConsumer(String mobileNumber, String pin) {
        if (mobileNumber == null || !mobileNumber.trim().matches("^[0-9]{10}$")) {
            throw new RuntimeException("Please enter a valid 10-digit mobile number.");
        }
        if (pin == null || !pin.trim().matches("^[0-9]{4}$")) {
            throw new RuntimeException("Please enter a 4-digit PIN.");
        }

        Consumer consumer = consumerRepository.findByMobileNumber(mobileNumber.trim())
                .orElseThrow(() -> new RuntimeException("No consumer account found with this mobile number."));

        if (!consumer.getPin().equals(pin.trim())) {
            throw new RuntimeException("Incorrect PIN.");
        }

        return consumer;
    }

    public Consumer getConsumerById(Long id) {
        return consumerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consumer not found with ID: " + id));
    }

    public List<Consumer> getAllConsumers() {
        return consumerRepository.findAll();
    }

    public Consumer updateConsumerProfile(Long id, Consumer updatedData) {
        Consumer consumer = getConsumerById(id);
        if (updatedData.getName() != null && !updatedData.getName().trim().isBlank()) {
            consumer.setName(updatedData.getName().trim());
        }
        if (updatedData.getAddress() != null) {
            consumer.setAddress(updatedData.getAddress().trim());
        }
        if (updatedData.getCity() != null) {
            consumer.setCity(updatedData.getCity().trim());
        }
        return consumerRepository.save(consumer);
    }
}
