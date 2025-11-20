package poly.edu.java6.core;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @Column(name = "cartCode", length = 10)
    private String cartCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('ACTIVE','INACTIVE')")
    private CartStatus status = CartStatus.INACTIVE; // ACTIVE: đang dùng, INACTIVE: đã checkout/cũ

    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    // Mối quan hệ 1-n: Một Cart có nhiều Cart_Items
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items;

    enum CartStatus { ACTIVE, INACTIVE }
}
