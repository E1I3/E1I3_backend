package com.e1i3.danum.service;

import com.e1i3.danum.entity.Product;
import com.e1i3.danum.entity.Reservation;
import com.e1i3.danum.entity.User;
import com.e1i3.danum.enumeration.ResvType;
import com.e1i3.danum.enumeration.TradeType;
import com.e1i3.danum.repository.ProductRepository;
import com.e1i3.danum.repository.ReservationRepository;
import com.e1i3.danum.repository.UserRepository;
import com.e1i3.danum.request.UpdateReservationByReserveRequest;
import com.e1i3.danum.request.UpdateTradeInfoRequest;
import com.e1i3.danum.response.ReadReservationByStoreResponse;
import com.e1i3.danum.response.ReadReservationByStoreResponses;
import com.e1i3.danum.response.UpdateReservationByReserveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    public ReadReservationByStoreResponses readReservationByStore(Long storeId){
        List<ReadReservationByStoreResponse> list = reservationRepository.findReservationByStoreId(storeId);

        return ReadReservationByStoreResponses.builder().reservations(list).build();
    }
    public UpdateReservationByReserveResponse updateReservationByReserveId(UpdateReservationByReserveRequest updateReservationByReserveRequest){
        Reservation reservation = reservationRepository.findById(updateReservationByReserveRequest.getReserveId()).orElseThrow(NoSuchElementException::new);
        reservation.setResvType(ResvType.COMPLETE);
        reservationRepository.save(reservation);

        // 나눔일 경우, donateSum 추가
        if (reservation.getProduct().getTradeType() == TradeType.SHARE){
            User user = reservation.getProduct().getStore().getUser();
            user.setDonateSum(user.getDonateSum() + 1);
            userRepository.save(user);
        }

        return UpdateReservationByReserveResponse.builder()
                .reserveId(reservation.getResvId())
                .resvTime(reservation.getResvTime())
                .resvType(reservation.getResvType())
                .productId(reservation.getProduct().getProductId())
                .productName(reservation.getProduct().getProductName())
                .productUrl(reservation.getProduct().getProductUrl())
                .price(reservation.getProduct().getPrice())
                .count(reservation.getProduct().getCount())
                .build();
    }

    public ReadReservationByStoreResponses readReservationByUser(Long userId){
        List<ReadReservationByStoreResponse> list = reservationRepository.findReservationByUserId(userId);

        return ReadReservationByStoreResponses.builder().reservations(list).build();
    }

    public void updateTradeInfo(UpdateTradeInfoRequest updateTradeInfoRequest){
        // 상품이 거래 타입인지 확인
        Product product = productRepository.findById(updateTradeInfoRequest.getProductId()).orElseThrow(NoSuchElementException::new);

        if(product.getTradeType() != TradeType.TRADE)
            throw new IllegalArgumentException("제품 타입이 나눔인 경우 예외 처리");

        // 상품 수량이 충분한지 확인
        if (updateTradeInfoRequest.getCount() > product.getCount())
            throw new IllegalArgumentException("기존 수량보다 요구 수량이 많은 경우 예외 처리");

        // 구매자의 잔액이 충분한지 확인
        User user = userRepository.findById(updateTradeInfoRequest.getUserId()).orElseThrow(NoSuchElementException::new);
        if (product.getPrice() * updateTradeInfoRequest.getCount() > user.getBalance())
            throw new IllegalArgumentException("사용자의 잔액이 충분하지 않은 경우 예외 처리");

        // 실행
        product.setCount(product.getCount() - updateTradeInfoRequest.getCount());
        product = productRepository.save(product);

        user.setBalance(user.getBalance() - product.getPrice() * updateTradeInfoRequest.getCount());
        user = userRepository.save(user);

        User owner = product.getStore().getUser();
        owner.setBalance(owner.getBalance() + updateTradeInfoRequest.getCount() * product.getPrice());
        userRepository.save(owner);

        Reservation reservation = Reservation.builder()
                .resvTime(LocalDateTime.now())
                .count(updateTradeInfoRequest.getCount())
                .user(user)
                .product(product)
                .resvType(ResvType.TRADE)
                .build();

        reservationRepository.save(reservation);
    }
    public void updateShareInfo(UpdateTradeInfoRequest updateTradeInfoRequest){
        // 상품이 나눔 타입인지 확인
        Product product = productRepository.findById(updateTradeInfoRequest.getProductId()).orElseThrow(NoSuchElementException::new);
        if(product.getTradeType() != TradeType.SHARE)
            throw new IllegalArgumentException("제품 타입이 거래인 경우 예외 처리");

        // 상품 수량이 충분한지 확인
        if (updateTradeInfoRequest.getCount() > product.getCount())
            throw new IllegalArgumentException("기존 수량보다 요구 수량이 많은 경우 예외 처리");

        // 구매자의 잔액이 충분한지 확인
        User user = userRepository.findById(updateTradeInfoRequest.getUserId()).orElseThrow(NoSuchElementException::new);

        // 실행
        product.setCount(product.getCount() - updateTradeInfoRequest.getCount());
        product = productRepository.save(product);

        Reservation reservation = Reservation.builder()
                .resvTime(LocalDateTime.now())
                .count(updateTradeInfoRequest.getCount())
                .user(user)
                .product(product)
                .resvType(ResvType.TRADE)
                .build();

        reservationRepository.save(reservation);
    }

}
