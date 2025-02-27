package com.example.getreadyauction.dto.auction;

import com.example.getreadyauction.entity.Auction;
import com.example.getreadyauction.entity.Bid;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class AuctionAllResponseDto {
    private Long id;
    private String title;
    private String category;
    private String username;
    private String content;
    private String successBid;
    private String deadline;
    private LocalDateTime modifiedAt;
    private List<Bid> allBidList;
    private List<Bid> topBidList;
    private boolean isDone;
    private int minPrice;
    private int currentPrice;
    private int views;

    public AuctionAllResponseDto(Auction auction, List<Bid> topBidList){
        this.id = auction.getId();
        this.title = auction.getTitle();
        this.category = auction.getCategory();
        this.username = auction.getUser().getUsername();
        this.content = auction.getContent();
        this.deadline = auction.getDeadline();
        this.modifiedAt = auction.getModifiedAt();
        this.allBidList = auction.getBidList();
        this.topBidList = topBidList;
        this.isDone = auction.isDone();
        this.minPrice = auction.getMinPrice();
        this.currentPrice = auction.getCurrentPrice();
        this.views = auction.getViews();
        this.successBid = auction.getSuccessBid();
    }
}
