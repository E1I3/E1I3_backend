package com.e1i3.danum.service;

import com.e1i3.danum.entity.Product;
import com.e1i3.danum.entity.Reservation;
import com.e1i3.danum.entity.User;
import com.e1i3.danum.enumeration.ResvType;
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
        // 상품 수량이 충분한지 확인
        Product product = productRepository.findById(updateTradeInfoRequest.getProductId()).orElseThrow(NoSuchElementException::new);
        if (updateTradeInfoRequest.getCount() > product.getCount())
            throw new IllegalArgumentException("기존 수량보다 요구 수량이 많은 경우 예외 처리");

        // 구매자의 잔액이 충분한지 확인
        User user = userRepository.findById(updateTradeInfoRequest.getUserId()).orElseThrow(NoSuchElementException::new);
        System.out.println(product.getPrice());
        System.out.println(updateTradeInfoRequest.getCount());
        System.out.println(user.getBalance());
        if (product.getPrice() * updateTradeInfoRequest.getCount() > user.getBalance())
            throw new IllegalArgumentException("사용자의 잔액이 충분하지 않은 경우 예외 처리");

        // 실행
        product.setCount(product.getCount() - updateTradeInfoRequest.getCount());
        product = productRepository.save(product);

        user.setBalance(user.getBalance() - product.getPrice() * updateTradeInfoRequest.getCount());
        user = userRepository.save(user);

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
