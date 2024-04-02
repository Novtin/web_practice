package application.controllers;

import application.entity.Client;
import application.entity.Product;
import application.services.ClientService;
import application.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class MainController {
    private final ProductService productService;
    private final ClientService clientService;

    public MainController(ProductService productService, ClientService clientService) {
        this.productService = productService;
        this.clientService = clientService;
    }

    @GetMapping("/menu")
    public String getMenu(){
        return "test";
    }

    @GetMapping("/cart")
    public String getCart(){
        return "cart";
    }

    @GetMapping("/addProduct")
    public String addProduct(){
        return "add";
    }

    @GetMapping("/api")
    public String getDocumentation(){
        return "doc";
    }

    @GetMapping("/edit/{editId}")
    public String editProduct(@PathVariable Integer editId){
        return "product";
    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("productId") int productId, HttpSession session){
        List<Product> cartItems = (List<Product>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        Optional<Product> productFromDB =  productService.findById(productId);
        if (productFromDB.isPresent()){
            Product product = productFromDB.get();
            product.setAvailability(product.getAvailability() - 1);
            productService.update(product);
            cartItems.add(product);
        }
        Integer totalAmount = cartItems.stream().mapToInt(Product::getPrice).sum();
        session.setAttribute("cartItems", cartItems);
        session.setAttribute("totalAmount", totalAmount);
        return "redirect:/menu";
    }

    @PostMapping("/delFromCart")
    public String delFromCart(@RequestParam("productId") int productId, HttpSession session){
        List<Product> cartItems = (List<Product>) session.getAttribute("cartItems");
        Optional<Product> productFromDB =  productService.findById(productId);
        if (productFromDB.isPresent()){
            Product product = productFromDB.get();
            cartItems.remove(product);
            product.setAvailability(product.getAvailability() + 1);
            productService.update(product);
        }
        session.setAttribute("cartItems", cartItems);
        return "redirect:/cart";
    }

    @GetMapping("/login")
    public String loginGet(Model model){
        model.addAttribute("client", new Client());
        return "login";
    }

    @GetMapping("/registration")
    public String registrationGet(Model model){
        model.addAttribute("client", new Client());
        return "registration";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute("client") @Validated Client client,
                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/login?error";
        }
        if (!clientService.loginClient(client.getEmail(), client.getPassword())){
            return "redirect:/login?error";
        }
        return "redirect:/menu";
    }

    @PostMapping("/registration")
    public String registrationPost(@ModelAttribute("client") @Validated Client client,
                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/registration?error";
        }
        if (!clientService.saveGamer(client)){
            return "redirect:/registration?error";
        }
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";
    }

    @GetMapping("/chat")
    public String chatPage(Model model, Principal principal) {
        model.addAttribute("clients", clientService.findAllExceptEmail(principal.getName()));
        model.addAttribute("curClient", clientService.findClientByEmail(principal.getName()));
        return "chat";
    }
}
