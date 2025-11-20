package poly.edu.java6.core;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_variants", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"productCode", "sizeId"}, name = "uq_product_size")
})
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variantId")
    private Integer variantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productCode", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sizeId", nullable = false)
    private Size size;

    @Column(name = "stock")
    private Integer stock = 0;

    @Column(name = "isActive")
    private Boolean isActive = true;
}
