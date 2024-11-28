// package project.marketplace.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import project.marketplace.models.Listing;
// import project.marketplace.services.ListingService;

// @Controller
// public class ListingController {

//     private final ListingService listingService;

//     @Autowired
//     public ListingController(ListingService listingService) {
//         this.listingService = listingService;
//     }

//     @GetMapping("/search")
//     public String searchListings(@RequestParam(name = "query", required = false, defaultValue = "") String query, Model model) {
//         ListingService ls = new ListingService();

//         List<Listing> listings = ls.searchListings(query);
        
//         System.out.println(query);
//         System.out.println(listings);
//         // Add the listings to the model
//         model.addAttribute("listings", listings);

//         return "index";  // Return the view (index.html in this case)
//     }
// }
