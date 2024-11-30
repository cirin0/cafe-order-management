package com.cafemanage.cafeordermanagement.web;

import com.cafemanage.cafeordermanagement.dto.CustomerDto;
import com.cafemanage.cafeordermanagement.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerWebController {

  private final CustomerService customerService;

  @GetMapping
  public String listCustomers(Model model){
    model.addAttribute("customers", customerService.getAllCustomers());
    return "customers/list";
  }

  @GetMapping("/register")
  public String showRegisterForm(Model model) {
    model.addAttribute("customer", new CustomerDto());
    return "customers/register";
  }

  @PostMapping("/register")
  public String registerCustomer(@ModelAttribute CustomerDto customerDto) {
    customerService.addCustomer(customerDto);
    return "redirect:/customers";
  }

  @GetMapping("/{name}/edit")
  public String showEditForm(@PathVariable String name, Model model) {
    CustomerDto customer = customerService.getCustomerByName(name);
    model.addAttribute("customer", customer);
    return "customers/edit";
  }

  @PostMapping("/{name}/edit")
  public String updateCustomer(@PathVariable String name, @ModelAttribute CustomerDto customerDto) {
    customerService.updateCustomer(name, customerDto);
    return "redirect:/customers";
  }


  @GetMapping("/{id}/delete")
  public String deleteCustomer(@PathVariable Long id) {
    customerService.deleteCustomer(id);
    return "redirect:/customers";
  }
}
