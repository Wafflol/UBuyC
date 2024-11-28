// package project.marketplace.services;

// import java.util.List;
// import java.util.Set;
// import java.util.stream.Collectors;

// import org.springframework.stereotype.Service;

// import project.marketplace.models.Listing;
// import project.marketplace.models.User;

// @Service
// public class ListingService {

//     private List<Listing> allListings;

//     public ListingService() {
//         // Example initialization with dummy listings and user details
//         this.allListings = List.of(
//             new Listing(new User(), "Laptop", "A good laptop", 500.0, "/images/laptop.jpg", Set.of("Electronics", "Computers"), 1),
//             new Listing(new User(), "Chair", "Comfortable office chair", 150.0, "/images/chair.jpg", Set.of("Furniture"), 2),
//             new Listing(new User(), "T-shirt", "Cool T-shirt", 20.0, "/images/tshirt.jpg", Set.of("Clothing"), 3)
//         );
//     }

//     public List<Listing> searchListings(String query) {
//         if (query == null || query.isEmpty()) {
//             return allListings;  // Return all listings if no query
//         }

//         // Search by listing title or description (contains query)
//         return allListings.stream()
//                 .filter(listing -> listing.getTitle().toLowerCase().contains(query.toLowerCase()) || 
//                                    listing.getDescription().toLowerCase().contains(query.toLowerCase()))
//                 .collect(Collectors.toList());
//     }
// }
