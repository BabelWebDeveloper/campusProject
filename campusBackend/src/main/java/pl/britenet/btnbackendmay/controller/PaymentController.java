package pl.britenet.btnbackendmay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.obj.model.Payment;
import pl.britenet.campus.obj.model.Product;
import pl.britenet.campus.service.PaymentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


//    @CrossOrigin(origins = "http://127.0.0.1:5500")
//    @GetMapping
//    public List<Payment> getPayments() {
//        return this.paymentService.retrieveOrders();
//    }

//    @CrossOrigin(origins = "http://127.0.0.1:5500")
//    @GetMapping("/order")
//    public List<Payment> getPayment() {
//        return this.paymentService.retrieveOrders();
//    }

//    @CrossOrigin(origins = "http://127.0.0.1:5500")
//    @GetMapping("/orders")
//    public List<Payment> getPayment(@RequestParam(name = "id") @PathVariable int id) {
//        return this.paymentService.retrieveOrdersByCustomerId(id);
//    }

    @PostMapping
    public void createPayment(@RequestBody Payment payment) {
        this.paymentService.create(payment);
    }

    @PutMapping
    public void updatePayment(@RequestBody Payment payment) {
        this.paymentService.update(payment);
    }

    @DeleteMapping("/{paymentId}")
    public void deletePPayment(@PathVariable int paymentId) {
        this.paymentService.remove(paymentId);
    }

}
