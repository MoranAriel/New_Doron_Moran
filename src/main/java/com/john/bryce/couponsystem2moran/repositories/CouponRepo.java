package com.john.bryce.couponsystem2moran.repositories;

import com.john.bryce.couponsystem2moran.entities.Category;
import com.john.bryce.couponsystem2moran.entities.Coupon;
import com.john.bryce.couponsystem2moran.exceptions.CouponSystemException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Long> {

    boolean existsByTitleAndCompanyId(String title, long companyId);

    boolean existsByTitleAndCompanyIdAndIdNot(String title, long companyId, long id);

    boolean existsByIdAndCompanyId(long id, long companyId);

    @Modifying
    @Query(value = "delete from `customer_coupons` where coupons_id in (select id from `coupon` where company_id = ?)", nativeQuery = true)
    void deletePurchasedCoupons(long companyId);

    @Modifying
    @Query(value = "delete from `customer_coupons` where customer_id = ?", nativeQuery = true)
    void deletePurchasedCouponsByCustomerId(long customerId);


    @Modifying
    @Query(value = "INSERT INTO `customer_coupons` VALUES (?, ?)", nativeQuery = true)
    void purchaseCoupon(long customerId, long couponId);

    @Query(value = "SELECT EXISTS (SELECT * FROM customer_coupons WHERE customer_id = ? AND coupons_id = ? ) AS res;", nativeQuery = true)
    int isCouponPurchasedBefore(long customer_id, long coupons_id);


    @Query(value = "select * from `coupon` join `customer_coupons` on coupon.id = customer_coupons.coupons_id where customer_id = ?", nativeQuery = true)
    List<Coupon> findByCustomer(long customerId) throws CouponSystemException;

    @Query(value = "select * from `coupon` join `customer_coupons` on coupon.id = customer_coupons.coupons_id where customer_id = ? and  price <= ?", nativeQuery = true)
    List<Coupon> findByCustomerIdAndPrice(long customerId, double price) throws CouponSystemException;

    @Query(value = "select * from `coupon` join `customer_coupons` on coupon.id = customer_coupons.coupons_id where customer_id = ? and  category = ? ", nativeQuery = true)
    List<Coupon> findByCustomerIdAndCategory(long customerId, String category) throws CouponSystemException;




    void deleteByCompanyId(long companyId);

    @Query(value = "SELECT * FROM coupon WHERE company_id = ?", nativeQuery = true)
    List<Coupon> getCompanyCoupons(long companyId);
//    List<Coupon> findByCompanyId(long companyId); All company coupons
//    Coupon findByCompanyIdAndId(long companyId, long id);



    @Query(value = "SELECT * FROM coupon WHERE company_id = ? AND category = ? ", nativeQuery = true)
    List<Coupon> getCompanyCouponsByCategory(long companyId, String categoryName);
//    List<Coupon> findByCompanyIdAndCategory(long companyId, Category category);

    @Query(value = "SELECT * FROM coupon WHERE company_id = ? AND price <= ? ", nativeQuery = true)
    List<Coupon> getCompanyCouponsByMaxPrice(long companyId, double maxPrice);
    //    List<Coupon> findByCompanyIdAndPriceLessThanEqual(long companyId, double price);

   // List<Coupon> getCustomerCouponsByCategory(long customerId, String name);
  //  List<Coupon> findByCustomerIdAndCategory(long customerId, Category category);


    //    Object findCouponById(long couponID);
}
