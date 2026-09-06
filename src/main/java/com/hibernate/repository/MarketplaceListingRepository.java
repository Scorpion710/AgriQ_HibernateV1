package com.hibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibernate.entity.MarketplaceListing;

@Repository
public interface MarketplaceListingRepository extends JpaRepository<MarketplaceListing, Long> {

    List<MarketplaceListing> findByFarmerIdOrderByIdDesc(Long farmerId);

    List<MarketplaceListing> findByStatusOrderByIdDesc(String status);
}
