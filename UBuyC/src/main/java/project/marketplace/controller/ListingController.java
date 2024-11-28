package project.marketplace.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.marketplace.models.Listing;
import project.marketplace.daos.ListingSearch;

@Controller
public class ListingController {

    private final ListingSearch listingSearch;

    public ListingController(ListingSearch listingSearch) {
        this.listingSearch = listingSearch;
    }

    @GetMapping("/search")
    public String searchListings(@RequestParam(name = "query", required = false, defaultValue = "") String query, Model model) {
        System.out.println("suckywucky");
        List<String> listings = this.listingSearch.searchListings(query);
        
        System.out.println(query);
        System.out.println(listings);
        // Add the listings to the model
        //model.addAttribute("listings", listings);

        return "index";  // Return the view (index.html in this case)
    }
}
