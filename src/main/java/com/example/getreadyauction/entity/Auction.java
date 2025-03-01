package com.example.getreadyauction.entity;

import com.example.getreadyauction.dto.auction.AuctionRequestDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Auction extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int minPrice;

    @Column(nullable = false)
    private boolean isDone;

    @Column(nullable = false)
    private int views;

    @Column(nullable = false)
    private int currentPrice;

    @Column(nullable = false)
    private String deadline;

    @Column(nullable = false)
    private int bidSize;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @JsonManagedReference
    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("createdAt desc")
    private List<Bid> bidList;

    private String successBid;

    @Builder
    public Auction(AuctionRequestDto auctionRequestDto, Users user) {
        this.title = auctionRequestDto.getTitle();
        this.category = auctionRequestDto.getCategory();
        this.content = auctionRequestDto.getContent();
        this.minPrice = auctionRequestDto.getMinPrice();
        this.currentPrice = auctionRequestDto.getMinPrice();
        this.deadline = auctionRequestDto.getDeadline();
        this.isDone = false;
        this.views = 0;
        this.bidSize = 0;
        this.user = user;
        this.successBid = "낙찰된 사용자가 없습니다.";
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setIsDone(LocalDateTime now) {
        LocalDateTime parsedDeadLine = LocalDateTime.parse(this.deadline, DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
        this.isDone = now.isAfter(parsedDeadLine);
        this.successBid = this.bidList.isEmpty()? this.successBid : this.bidList.get(0).getUser().getUsername();
    }

    public void setBidList(List<Bid> bidList) {
        this.bidList = bidList;
        this.bidSize += 1;
    }

    public void setView() {
        this.views += 1;
    }

    public void edit(AuctionRequestDto auctionRequestDto) { // deadline : 2023-05-09T19:21:23
        this.title = auctionRequestDto.getTitle();
        this.category = auctionRequestDto.getCategory();
        this.content = auctionRequestDto.getContent();
        this.minPrice = auctionRequestDto.getMinPrice();
        this.deadline = auctionRequestDto.getDeadline();
    }

    public void up() {
        this.setCreatedAt(LocalDateTime.now());
    }
}
