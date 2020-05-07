package com.example.myapplication.Api;

import com.example.myapplication.Model.PaymentItem;
import com.example.myapplication.Model.ShoppingCart;
import com.example.myapplication.Model.ShoppingCartGroup;
import com.example.myapplication.Model.VendorMainDashboard;
import com.example.myapplication.Model.VendorMainOnline;
import com.example.myapplication.Model.VendorProduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    // online vendors
    @GET("serviceMainMenu/getOnlineVendors.php")
    Call<List<VendorMainOnline>> getOnlineVendors(
        @Query("user_vendor_type") String user_vendor_type
    );

    // get all specific vendor products
    @GET("serviceMainMenu/getVendorServices.php")
    Call<VendorMainDashboard> getSpecificVendor(
            @Query("vendor_id") String vendor_id
    );

    // get all detail from specific product
    @GET("serviceMainMenu/getSpecificProduct.php")
    Call<VendorProduct> getSpecificProduct(
            @Query("product_id") String product_id,
            @Query("id_user") String id_user
    );

    // get all detail from specific product
    @GET("shoppingCart/getAllShoppingCart.php")
    Call<List<ShoppingCartGroup>> getAllShoppingCart(
            @Query("id_user") String id_user
    );

    // add selected product to product cart
    @FormUrlEncoded
    @POST("shoppingCart/addNewShoppingCart.php")
    Call<ShoppingCart> addToCart(
            @Field("id_user") String id_user,
            @Field("id_service_product") String id_service_product,
            @Field("shopcart_qty") int shopcart_qty,
            @Field("shopcart_price") int shopcart_price,
            @Field("shopcart_note") String shopcart_note
    );


    @GET("payment/checkPayment.php")
    Call<List<PaymentItem>> getPaymentItem(
            @Query("user") String user
    );

}
