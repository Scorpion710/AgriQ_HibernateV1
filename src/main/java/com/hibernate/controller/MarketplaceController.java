package com.hibernate.controller;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hibernate.entity.MarketplaceListing;
import com.hibernate.entity.MarketplaceOrder;
import com.hibernate.service.MarketplaceService;

@RestController
@RequestMapping("/api/marketplace")
public class MarketplaceController {

    private final MarketplaceService marketplaceService;

    public MarketplaceController(MarketplaceService marketplaceService) {
        this.marketplaceService = marketplaceService;
    }

    @GetMapping("/listings")
    public List<MarketplaceListing> getActiveListings(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) BigDecimal minQty) {
        return marketplaceService.getActiveListings(search, location, maxPrice, minQty);
    }

    @GetMapping("/listings/{id}")
    public ResponseEntity<?> getListingById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(marketplaceService.getListingById(id));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @GetMapping("/farmer/{farmerId}/listings")
    public List<MarketplaceListing> getFarmerListings(@PathVariable Long farmerId) {
        return marketplaceService.getFarmerListings(farmerId);
    }

    @PostMapping("/listings")
    public ResponseEntity<?> createListing(@RequestBody MarketplaceListing listing) {
        try {
            return ResponseEntity.ok(marketplaceService.createListing(listing));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @PutMapping("/listings/{id}")
    public ResponseEntity<?> updateListing(
            @PathVariable Long id,
            @RequestParam(required = false) Long farmerId,
            @RequestBody MarketplaceListing listing) {
        try {
            return ResponseEntity.ok(marketplaceService.updateListing(id, farmerId, listing));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @DeleteMapping("/listings/{id}")
    public ResponseEntity<?> deleteListing(
            @PathVariable Long id,
            @RequestParam(required = false) Long farmerId) {
        try {
            marketplaceService.deleteListing(id, farmerId);
            return ResponseEntity.ok(Map.of("message", "Listing deleted successfully"));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody MarketplaceOrder order) {
        try {
            return ResponseEntity.ok(marketplaceService.createOrder(order));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @GetMapping("/orders/consumer/{consumerId}")
    public List<MarketplaceOrder> getConsumerOrders(@PathVariable Long consumerId) {
        return marketplaceService.getConsumerOrders(consumerId);
    }

    @GetMapping("/orders/farmer/{farmerId}")
    public List<MarketplaceOrder> getFarmerOrders(@PathVariable Long farmerId) {
        return marketplaceService.getFarmerOrders(farmerId);
    }

    @PutMapping("/orders/{id}/status")
    public ResponseEntity<?> updateOrderStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        try {
            String status = body.get("status");
            if (status == null || status.isBlank()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Status is required."));
            }
            return ResponseEntity.ok(marketplaceService.updateOrderStatus(id, status));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @PostMapping("/upload-image")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Please select a file to upload."));
            }
            String contentType = file.getContentType();
            if (contentType == null || (!contentType.startsWith("image/"))) {
                return ResponseEntity.badRequest().body(Map.of("message", "Only image files (JPG, PNG, WebP) are allowed."));
            }
            if (file.getSize() > 5 * 1024 * 1024) {
                return ResponseEntity.badRequest().body(Map.of("message", "Image size must not exceed 5MB."));
            }
            String base64Data = "data:" + contentType + ";base64," + Base64.getEncoder().encodeToString(file.getBytes());
            return ResponseEntity.ok(Map.of("imageUrl", base64Data));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(Map.of("message", "Failed to upload image: " + ex.getMessage()));
        }
    }
}
