package com.example.getreadyauction.repository;

import com.example.getreadyauction.entity.Auction;
import com.example.getreadyauction.entity.Bid;
import com.example.getreadyauction.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
    Page<Auction> findAllByCategory(Pageable pageable, String category);
    Page<Auction> findAllByTitleContaining(Pageable pageable, String title);

    Page<Auction> findAllByUser(Pageable pageable, Users user); //내 경매 전체 조회
    Page<Auction> findByBidListUser(Pageable pageable, Users user); //내 입찰 전체 조회

    Page<Auction>findAllByOrderByViewsDesc(Pageable pageable); //view기준 내림차순

    Page<Auction>findAllByOrderByDeadlineDesc(Pageable pageable); //deadline 기준 내림차순

    Page<Auction> findAllByOrderByBidSizeDesc(Pageable pageable); //입찰이 많이 된 기준 내림차순

}
