package cn.edu.bupt.ch10_1.controller;

import cn.edu.bupt.ch10_1.dao.CustomerRepository;
import cn.edu.bupt.ch10_1.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class LoginController {

    int total = 0;

    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @RequestMapping("/login")
    public String index(Model model, HttpSession session) {
        return "login";
    }

    @RequestMapping("/add")
    public String add() {
        return "details";
    }

    @PostMapping("/additem")
    public String additem(@RequestBody User user, HttpSession session) {
        user.setId(Integer.toString(total));
        total++;

        Customer customer = new Customer();
        customer.setName(user.getName());
        customer.setPhone(user.getPhone());
        customer.setMail(user.getMail());
        customer.setAddress(user.getAddress());
        customer.setQq(user.getQq());

        customer.setIdentity(String.valueOf(total));
        customerRepository.save(customer);
        //Iterable<Customer> list=customerRepository.findAll();
        return "index";
    }

    @GetMapping("/return")
    public String ret(Model model, String username, String password, HttpSession session) {
        System.out.println("RETURN total:" + total);
        List<Customer> customerList = (List<Customer>) customerRepository.findAll();

        if (customerList != null) {
            model.addAttribute("users", customerList);
        }
        return "index";

    }

    @ResponseBody
    @PostMapping("/checkphone")
    public check_phone check(@RequestBody HashMap<String, String> map) {
        String result=map.get("phone");
        System.out.println(result);
        List<Customer> customerList = (List<Customer>) customerRepository.findAll();
        int is_dup=0;
        for (int i = 0; i < customerList.size(); i++) {
            if(customerList.get(i).getPhone().equals(result)){
                is_dup=1;
                break;
            }
        }
        check_phone obj=new check_phone(String.valueOf(is_dup));
        return obj;
    }

    @PostMapping("/user")
    public String ok(Model model, String username, String password, HttpSession session) {
        System.out.println("返回值是>>>>>>" + username + "---->" + password);
        System.out.println("total:" + total);

        List<User> list = getUser(session);
        if (list != null) {
            model.addAttribute("users", list);
        }

        if (username.equals("123456") && password.equals("111111")) {
            session.setAttribute("USER", "1");
            return "index";
        } else {
            return "login";
        }
    }

    @ResponseBody
    @GetMapping("/delete")
    public String Produce(String id, HttpSession session) {
        long l = Long.parseLong(id);
        List<Customer> customerList = customerRepository.findByid(l);
        customerRepository.delete(customerList.get(0));
        System.out.println("delete" + id);
        session.removeAttribute(id);
        return null;
    }

    @GetMapping("/edit")
    public String edit(Model model, String id, HttpSession session) {
        System.out.println("edit" + id);
        long l = Long.parseLong(id);
        List<Customer> customerList = customerRepository.findByid(l);
        customerRepository.delete(customerList.get(0));

        session.removeAttribute(id);
        model.addAttribute("xingming", customerList.get(0).getName());
        model.addAttribute("dianhua", customerList.get(0).getPhone());
        model.addAttribute("youxiang", customerList.get(0).getMail());
        model.addAttribute("zhuzhi", customerList.get(0).getAddress());
        model.addAttribute("qqhao", customerList.get(0).getQq());
        model.addAttribute("idhao", customerList.get(0).getId());
        return "details";
    }

    public List<User> getUser(HttpSession session) {
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < total; i++) {
            User temp = (User) session.getAttribute(Integer.toString(i));
            while (temp == null && i < total) {
                i++;
                temp = (User) session.getAttribute(Integer.toString(i));
            }
            if (temp != null) {
                System.out.println(temp.getId() + " " + temp.getName() + " " + temp.getPhone() + " " + temp.getMail() + " " + temp.getAddress() + " " + temp.getQq());
                list.add(temp);
            }
        }
        return list;
    }


}
