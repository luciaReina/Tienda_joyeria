//package com.app.controller;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpResponse;
//import java.util.List;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.paypal.core.PayPalHttpClient;
//import com.paypal.orders.AmountWithBreakdown;
//import com.paypal.orders.ApplicationContext;
//import com.paypal.orders.LinkDescription;
//import com.paypal.orders.Order;
//import com.paypal.orders.OrderRequest;
//import com.paypal.orders.OrdersCreateRequest;
//import com.paypal.orders.PurchaseUnitRequest;
//
//import jakarta.servlet.http.HttpServletResponse;
//
//@RestController
//@RequestMapping("/api/payments")
//public class PaymentController {
//
//    private final PayPalHttpClient payPalClient;
//    
//
//    public PaymentController(PayPalHttpClient payPalClient) {
//        this.payPalClient = payPalClient;
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<Void> createPayment(HttpServletResponse response) throws IOException {
//        OrderRequest orderRequest = new OrderRequest();
//        orderRequest.checkoutPaymentIntent("CAPTURE");
//
//        ApplicationContext applicationContext = new ApplicationContext()
//                .returnUrl("http://localhost:8080/html/index.html")
//                .cancelUrl("http://localhost:8080/carrito/");
//
//        orderRequest.applicationContext(applicationContext);
//
//        List<PurchaseUnitRequest> purchaseUnits = List.of(
//            new PurchaseUnitRequest().amountWithBreakdown(
//            		new AmountWithBreakdown().currencyCode("EUR").value("10.00")
//            )                       
//        );
//
//        orderRequest.purchaseUnits(purchaseUnits);
//
//        OrdersCreateRequest request = new OrdersCreateRequest().requestBody(orderRequest);
//        @SuppressWarnings("unchecked")
//		HttpResponse<Order> responsePaypal = (HttpResponse<Order>) payPalClient.execute(request);
//
//        for (LinkDescription link : ((com.paypal.http.HttpResponse<Order>) responsePaypal).result().links()) {
//            if ("approve".equals(link.rel())) {
//                return ResponseEntity.status(302).location(URI.create(link.href())).build();
//            }
//        }
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }
//}

