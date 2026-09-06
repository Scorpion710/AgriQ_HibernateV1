package com.hibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibernate.entity.MarketplaceOrder;

@Repository
public interface MarketplaceOrderRepository extends JpaRepository<MarketplaceOrder, Long> {

    List<MarketplaceOrder> findByConsumerIdOrderByIdDesc(Long consumerId);

    List<MarketplaceOrder> findByFarmerIdOrderByIdDesc(Long farmerId);

    List<MarketplaceOrder> findByListingIdOrderByIdDesc(Long listingId);
}
