package cn.edu.bupt.ch10_1.dao;

import cn.edu.bupt.ch10_1.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByid(Long id);
}
