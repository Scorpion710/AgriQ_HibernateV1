package com.hibernate.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.entity.Consumer;
import com.hibernate.entity.Farmer;
import com.hibernate.entity.MarketplaceListing;
import com.hibernate.entity.MarketplaceOrder;
import com.hibernate.repository.ConsumerRepository;
import com.hibernate.repository.FarmerRepository;
import com.hibernate.repository.MarketplaceListingRepository;
import com.hibernate.repository.MarketplaceOrderRepository;

@Service
public class MarketplaceService {

    private final MarketplaceListingRepository listingRepository;
    private final MarketplaceOrderRepository orderRepository;
    private final FarmerRepository farmerRepository;
    private final ConsumerRepository consumerRepository;

    public MarketplaceService(MarketplaceListingRepository listingRepository,
                              MarketplaceOrderRepository orderRepository,
                              FarmerRepository farmerRepository,
                              ConsumerRepository consumerRepository) {
        this.listingRepository = listingRepository;
        this.orderRepository = orderRepository;
        this.farmerRepository = farmerRepository;
        this.consumerRepository = consumerRepository;
    }

    public MarketplaceListing createListing(MarketplaceListing listing) {
        if (listing.getFarmerId() == null) {
            throw new RuntimeException("Farmer ID is required.");
        }
        if (listing.getCropName() == null || listing.getCropName().trim().isBlank()) {
            throw new RuntimeException("Crop name is required.");
        }
        if (listing.getQuantityKg() == null || listing.getQuantityKg().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Quantity must be greater than zero.");
        }
        if (listing.getPricePerKg() == null || listing.getPricePerKg().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Price per kg must be greater than zero.");
        }

        Farmer farmer = farmerRepository.findById(listing.getFarmerId()).orElse(null);
        if (farmer != null) {
            if (listing.getFarmerName() == null || listing.getFarmerName().isBlank()) {
                listing.setFarmerName(farmer.getName());
            }
            if (listing.getFarmerMobile() == null || listing.getFarmerMobile().isBlank()) {
                listing.setFarmerMobile(farmer.getMobileNumber());
            }
            if ((listing.getLocation() == null || listing.getLocation().isBlank()) && farmer.getVillage() != null) {
                listing.setLocation(farmer.getVillage());
            }
        }

        listing.setCropName(listing.getCropName().trim());
        if (listing.getStatus() == null || listing.getStatus().isBlank()) {
            listing.setStatus("AVAILABLE");
        }
        if (listing.getCreatedAt() == null || listing.getCreatedAt().isBlank()) {
            listing.setCreatedAt(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        }

        return listingRepository.save(listing);
    }

    public MarketplaceListing updateListing(Long listingId, Long farmerId, MarketplaceListing updated) {
        MarketplaceListing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Listing not found with ID: " + listingId));

        if (farmerId != null && !listing.getFarmerId().equals(farmerId)) {
            throw new RuntimeException("You are not authorized to edit this listing.");
        }

        if (updated.getCropName() != null && !updated.getCropName().trim().isBlank()) {
            listing.setCropName(updated.getCropName().trim());
        }
        if (updated.getVarietyGrade() != null) {
            listing.setVarietyGrade(updated.getVarietyGrade().trim());
        }
        if (updated.getQuantityKg() != null && updated.getQuantityKg().compareTo(BigDecimal.ZERO) > 0) {
            listing.setQuantityKg(updated.getQuantityKg());
        }
        if (updated.getPricePerKg() != null && updated.getPricePerKg().compareTo(BigDecimal.ZERO) > 0) {
            listing.setPricePerKg(updated.getPricePerKg());
        }
        if (updated.getMinOrderQuantityKg() != null) {
            listing.setMinOrderQuantityKg(updated.getMinOrderQuantityKg());
        }
        if (updated.getExpectedAvailabilityDate() != null) {
            listing.setExpectedAvailabilityDate(updated.getExpectedAvailabilityDate().trim());
        }
        if (updated.getLocation() != null) {
            listing.setLocation(updated.getLocation().trim());
        }
        if (updated.getDescription() != null) {
            listing.setDescription(updated.getDescription().trim());
        }
        if (updated.getImageUrl() != null && !updated.getImageUrl().isBlank()) {
            listing.setImageUrl(updated.getImageUrl());
        }
        if (updated.getStatus() != null && !updated.getStatus().isBlank()) {
            listing.setStatus(updated.getStatus().trim().toUpperCase());
        }

        return listingRepository.save(listing);
    }

    public void deleteListing(Long listingId, Long farmerId) {
        MarketplaceListing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Listing not found with ID: " + listingId));

        if (farmerId != null && !listing.getFarmerId().equals(farmerId)) {
            throw new RuntimeException("You are not authorized to delete this listing.");
        }

        listingRepository.delete(listing);
    }

    public List<MarketplaceListing> getFarmerListings(Long farmerId) {
        return listingRepository.findByFarmerIdOrderByIdDesc(farmerId);
    }

    public MarketplaceListing getListingById(Long id) {
        return listingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Listing not found with ID: " + id));
    }

    public List<MarketplaceListing> getActiveListings(String query, String location, BigDecimal maxPrice, BigDecimal minQty) {
        List<MarketplaceListing> available = listingRepository.findByStatusOrderByIdDesc("AVAILABLE");

        return available.stream().filter(l -> {
            if (query != null && !query.trim().isBlank()) {
                String q = query.trim().toLowerCase();
                boolean matchesCrop = l.getCropName() != null && l.getCropName().toLowerCase().contains(q);
                boolean matchesVariety = l.getVarietyGrade() != null && l.getVarietyGrade().toLowerCase().contains(q);
                boolean matchesFarmer = l.getFarmerName() != null && l.getFarmerName().toLowerCase().contains(q);
                if (!matchesCrop && !matchesVariety && !matchesFarmer) return false;
            }
            if (location != null && !location.trim().isBlank()) {
                String loc = location.trim().toLowerCase();
                if (l.getLocation() == null || !l.getLocation().toLowerCase().contains(loc)) {
                    return false;
                }
            }
            if (maxPrice != null && maxPrice.compareTo(BigDecimal.ZERO) > 0) {
                if (l.getPricePerKg() == null || l.getPricePerKg().compareTo(maxPrice) > 0) {
                    return false;
                }
            }
            if (minQty != null && minQty.compareTo(BigDecimal.ZERO) > 0) {
                if (l.getQuantityKg() == null || l.getQuantityKg().compareTo(minQty) < 0) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());
    }

    @Transactional
    public MarketplaceOrder createOrder(MarketplaceOrder order) {
        if (order.getListingId() == null) {
            throw new RuntimeException("Listing ID is required.");
        }
        if (order.getConsumerId() == null) {
            throw new RuntimeException("Consumer ID is required.");
        }
        if (order.getQuantityKg() == null || order.getQuantityKg().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Order quantity must be greater than zero.");
        }

        MarketplaceListing listing = listingRepository.findById(order.getListingId())
                .orElseThrow(() -> new RuntimeException("Listing not found with ID: " + order.getListingId()));

        if (!"AVAILABLE".equalsIgnoreCase(listing.getStatus())) {
            throw new RuntimeException("This produce is currently not available for purchase.");
        }

        if (listing.getMinOrderQuantityKg() != null &&
            order.getQuantityKg().compareTo(listing.getMinOrderQuantityKg()) < 0) {
            throw new RuntimeException("Minimum order quantity is " + listing.getMinOrderQuantityKg() + " kg.");
        }

        if (order.getQuantityKg().compareTo(listing.getQuantityKg()) > 0) {
            throw new RuntimeException("Requested quantity (" + order.getQuantityKg() + " kg) exceeds available stock (" + listing.getQuantityKg() + " kg).");
        }

        Consumer consumer = consumerRepository.findById(order.getConsumerId()).orElse(null);
        if (consumer != null) {
            if (order.getConsumerName() == null || order.getConsumerName().isBlank()) {
                order.setConsumerName(consumer.getName());
            }
            if (order.getConsumerMobile() == null || order.getConsumerMobile().isBlank()) {
                order.setConsumerMobile(consumer.getMobileNumber());
            }
            if ((order.getDeliveryAddress() == null || order.getDeliveryAddress().isBlank()) && consumer.getAddress() != null) {
                String fullAddr = consumer.getAddress();
                if (consumer.getCity() != null && !consumer.getCity().isBlank()) {
                    fullAddr += ", " + consumer.getCity();
                }
                order.setDeliveryAddress(fullAddr);
            }
        }

        order.setFarmerId(listing.getFarmerId());
        order.setFarmerName(listing.getFarmerName());
        order.setCropName(listing.getCropName());
        order.setPricePerKg(listing.getPricePerKg());
        order.setTotalAmount(order.getQuantityKg().multiply(listing.getPricePerKg()));
        order.setOrderStatus("PENDING");
        order.setOrderDate(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));

        // Deduct inventory
        BigDecimal remainingQty = listing.getQuantityKg().subtract(order.getQuantityKg());
        listing.setQuantityKg(remainingQty);
        if (remainingQty.compareTo(BigDecimal.ZERO) <= 0) {
            listing.setStatus("SOLD");
        }
        listingRepository.save(listing);

        return orderRepository.save(order);
    }

    public List<MarketplaceOrder> getConsumerOrders(Long consumerId) {
        return orderRepository.findByConsumerIdOrderByIdDesc(consumerId);
    }

    public List<MarketplaceOrder> getFarmerOrders(Long farmerId) {
        return orderRepository.findByFarmerIdOrderByIdDesc(farmerId);
    }

    @Transactional
    public MarketplaceOrder updateOrderStatus(Long orderId, String newStatus) {
        MarketplaceOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        String oldStatus = order.getOrderStatus();
        String targetStatus = newStatus.trim().toUpperCase();

        if ("CANCELLED".equals(targetStatus) && !"CANCELLED".equals(oldStatus)) {
            // Restore inventory to listing
            MarketplaceListing listing = listingRepository.findById(order.getListingId()).orElse(null);
            if (listing != null) {
                listing.setQuantityKg(listing.getQuantityKg().add(order.getQuantityKg()));
                if ("SOLD".equalsIgnoreCase(listing.getStatus())) {
                    listing.setStatus("AVAILABLE");
                }
                listingRepository.save(listing);
            }
        }

        order.setOrderStatus(targetStatus);
        return orderRepository.save(order);
    }
}
