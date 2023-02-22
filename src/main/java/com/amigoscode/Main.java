package com.amigoscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main (CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
    }
    /*@GetMapping("/greet")
    public GreetResponse greet(){
        return new GreetResponse(
                "Hello",
                List.of("java", "Php"),
                new Person("Alex", 28, 30_000));
    }

    record  Person(String name, int age, double saving){}
    record GreetResponse(
            String greet,
            List<String>favProgrammingLanguages,
            Person person
    ){}*/

    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    record NewCustomerResquest( String name,
                                String email,
                                Integer age){

    }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerResquest resquest){
        Customer customer = new Customer();
        customer.setName(resquest.name());
        customer.setEmail(resquest.email());
        customer.setAge(resquest.age());
        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    /*class GreetResponse{
        private final String greet;


        public GreetResponse(String greet) {
            this.greet = greet;
        }

        public String getGreet() {
            return greet;
        }

        @Override
        public String toString() {
            return "GreetResponse{" +
                    "greet='" + greet + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GreetResponse that = (GreetResponse) o;
            return Objects.equals(greet, that.greet);
        }

        @Override
        public int hashCode() {
            return Objects.hash(greet);
        }
    }*/
}
